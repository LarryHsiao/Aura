package com.silverhetch.aurademo

import android.Manifest
import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Color
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.media.MicRecord
import com.larryhsiao.clotho.media.PcmToWav
import com.yalantis.waves.util.Horizon
import java.io.*


class AudioRecordingActivity : AuraActivity() {
    private lateinit var horizon: Horizon
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(GLSurfaceView(this).also {
            it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            horizon = Horizon(
                it,
                Color.GRAY,
                44100,
                1,
                16
            )
        })

        requestPermissionsByObj(
            arrayOf(RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        )
    }

    override fun onPermissionGranted() {
        super.onPermissionGranted()

        if (checkSelfPermission(this, RECORD_AUDIO) == PERMISSION_GRANTED) {
            val output = FileOutputStream(
                File(
                    Environment.getExternalStorageDirectory(),
                    "test.pcm")
            )
            MicRecord { bytes, i ->
                horizon.updateView(bytes)
                output.write(bytes, 0, i)
            }.also {
                Thread {
                    it.start()
                }.start()
                Handler().postDelayed({
                    it.stop()
                    output.close()

                    PcmToWav(
                        File(
                            Environment.getExternalStorageDirectory(),
                            "test.pcm"),
                        File(
                            Environment.getExternalStorageDirectory(),
                            "test.wav")
                    ).fire()
                    Toast.makeText(
                        this@AudioRecordingActivity,
                        "Recorded",
                        Toast.LENGTH_SHORT
                    ).show()
                }, 3000)
            }
        }
    }
}