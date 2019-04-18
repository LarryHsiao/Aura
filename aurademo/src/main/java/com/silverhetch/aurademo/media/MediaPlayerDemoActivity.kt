package com.silverhetch.aurademo.media

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Point
import android.os.Bundle
import android.os.IBinder
import android.view.SurfaceHolder
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.media.AuraMediaPlayer
import com.silverhetch.aura.media.MediaPlayerService
import com.silverhetch.aurademo.R
import kotlinx.android.synthetic.main.activity_media_player.*

/**
 * Demo activity for media [MediaPlayerService]
 *
 * @todo #2 review this class to determine if this class is finish.
 */
class MediaPlayerDemoActivity : AuraActivity() {
    private lateinit var mediaPlayer: AuraMediaPlayer
    private var pendingPlay = false
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mediaPlayer = (service as MediaPlayerService.Binder).mediaPlayer()
            if (pendingPlay) {
                mediaPlayer.load(intent?.data.toString())
                mediaPlayer.play()
            }
            mediaPlayer.attachDisplay(mediaPlayer_display.holder)
            mediaPlayer.videoSize().observe(this@MediaPlayerDemoActivity, Observer {
                mediaPlayer_display.layoutParams = mediaPlayer_display.layoutParams.apply {
                    width = Point().run {
                        windowManager.defaultDisplay.getSize(this)
                        x
                    }
                    height = ((it.y.toFloat() / it.x.toFloat()) * width).toInt()
                }
            })
            mediaPlayer.buffered().observe(this@MediaPlayerDemoActivity, Observer {
                mediaPlayer_progress.secondaryProgress = ((it / 100f) * mediaPlayer_progress.max).toInt()
            })
            mediaPlayer.progress().observe(this@MediaPlayerDemoActivity, Observer {
                runOnUiThread {
                    mediaPlayer_progress.progress = it
                }
            })
            mediaPlayer.duration().observe(this@MediaPlayerDemoActivity, Observer {
                mediaPlayer_progress.max = it
            })
            mediaPlayer_progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })

            mediaPlayer_play.setOnClickListener { mediaPlayer.play() }
            mediaPlayer_pause.setOnClickListener { mediaPlayer.pause() }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        pendingPlay = savedInstanceState == null && intent?.data != null

        mediaPlayer_display.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {
                startService(Intent(this@MediaPlayerDemoActivity, MediaPlayerService::class.java))
                bindService(
                        Intent(this@MediaPlayerDemoActivity, MediaPlayerService::class.java),
                        connection,
                        Context.BIND_AUTO_CREATE
                )
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        unbindService(connection)
    }


}