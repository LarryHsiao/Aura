package com.larryhsiao.aura.media

import android.Manifest.permission.RECORD_AUDIO
import android.media.AudioFormat.CHANNEL_IN_MONO
import android.media.AudioFormat.ENCODING_PCM_16BIT
import android.media.AudioRecord
import android.media.MediaRecorder.AudioSource.MIC
import androidx.annotation.RequiresPermission

/**
 * Object records audio received by phone`s microphone.
 *
 * This class use guaranteed work parameters for [AudioRecord].
 */
class MicRecord(private val onRecorded: (buffer: ByteArray, read: Int) -> Unit) : Record {
    private val bufferSize by lazy {
        AudioRecord.getMinBufferSize(
            44100,
            CHANNEL_IN_MONO,
            ENCODING_PCM_16BIT
        ) * 2
    }
    private lateinit var recorder: AudioRecord
    private var recording: Boolean = false

    @RequiresPermission(allOf = [RECORD_AUDIO])
    override fun start() {
        if (recording) {
            return
        }
        recording = true
        recorder = AudioRecord(
            MIC,
            44100,
            CHANNEL_IN_MONO,
            ENCODING_PCM_16BIT,
            bufferSize
        )
        recorder.startRecording()
        val buffer = ByteArray(bufferSize)
        var read = 0
        while (recording) {
            read = recorder.read(buffer, 0, buffer.size)
            if (read >= 0) {
                onRecorded(buffer, read)
            } else {
                recording = false
            }
        }
    }

    override fun stop() {
        recording = false
        recorder.stop()
        recorder.release()
    }

    override fun running(): Boolean {
        return recording
    }
}