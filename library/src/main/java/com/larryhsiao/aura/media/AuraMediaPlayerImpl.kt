package com.larryhsiao.aura.media

import android.content.Context
import android.graphics.Point
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.TimeUnit.SECONDS

/**
 * Implementation of [AuraMediaPlayer]
 */
class AuraMediaPlayerImpl(private val context: Context) : AuraMediaPlayer, PlaybackControl {
    private val state = MutableLiveData<State>().apply {
        value = ConstState()
    }
    private var buffered: Int = 0
    private var completed: Boolean = false // Note: this state will be publish only one time if true
    private var videoSize = MutableLiveData<Point>().apply { Point(0, 0) }
    private val handler = Handler()
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private var attemptPlay = false
    private lateinit var dataSource: Uri
    private val progressRunnable = object : Runnable {
        override fun run() {
            state.value = ConstState(
                mediaPlayer.isPlaying || attemptPlay,
                if (mediaPlayer.duration != 0) mediaPlayer.duration else 0,
                buffered,
                if (mediaPlayer.currentPosition != 0) mediaPlayer.currentPosition else 0,
                completed
            )
            completed = false
            handler.postDelayed(this, SECONDS.toMillis(1))
        }
    }

    override fun load(uri: String) {
        dataSource = Uri.parse(uri)
        mediaPlayer.stop()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(context, dataSource)
        mediaPlayer.setOnVideoSizeChangedListener { _, width, height -> videoSize.value = Point(width, height) }
        mediaPlayer.setOnBufferingUpdateListener { _, percent -> buffered = percent }
        mediaPlayer.setOnPreparedListener {
            if (attemptPlay) {
                mediaPlayer.start()
            }
            mediaPlayer.setOnCompletionListener { completed = true }
        }
        mediaPlayer.prepareAsync()
        handler.postDelayed(progressRunnable, SECONDS.toMillis(1))
    }

    override fun play() {
        attemptPlay = true
        mediaPlayer.start()
    }

    override fun pause() {
        attemptPlay = false
        mediaPlayer.pause()
    }

    override fun seekTo(position: Int) {
        mediaPlayer.seekTo(position)
    }

    override fun state(): LiveData<State> {
        return state
    }

    override fun attachDisplay(surfaceHolder: SurfaceHolder) {
        mediaPlayer.setDisplay(surfaceHolder)
        if (!mediaPlayer.isPlaying && ::dataSource.isInitialized) {
            // To draw current frame on surface
            mediaPlayer.seekTo(mediaPlayer.currentPosition)
        }
    }

    override fun detachDisplay() {
        mediaPlayer.setDisplay(null)
    }

    override fun release() {
        attemptPlay = false
        handler.removeCallbacks(progressRunnable)
        mediaPlayer.release()
    }

    override fun videoSize(): LiveData<Point> {
        return videoSize
    }

    override fun playback(): PlaybackControl {
        return this
    }
}