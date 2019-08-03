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
import com.silverhetch.aurademo.R
import kotlinx.android.synthetic.main.activity_media_player.*

/**
 * Demo activity for media [MediaPlayerService]
 *
 * @todo #2 review this class to determine if this class is finish.
 */
class MediaPlayerDemoActivity : AuraActivity() {
    private val owner = this
    private lateinit var mediaPlayer: AuraMediaPlayer
    private var pendingPlay = false
    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mediaPlayer = (service as MediaPlayerService.Binder).mediaPlayer()
            if (pendingPlay) {
                mediaPlayer.load(intent?.data.toString())
                mediaPlayer.playback().play()
            }
            mediaPlayer.attachDisplay(mediaPlayer_display.holder)
            mediaPlayer.videoSize().observe(owner, Observer {
                mediaPlayer_display.layoutParams = mediaPlayer_display.layoutParams.apply {
                    width = Point().run {
                        windowManager.defaultDisplay.getSize(this)
                        x
                    }
                    height = ((it.y.toFloat() / it.x.toFloat()) * width).toInt()
                }
            })
            mediaPlayer.state().observe(owner, Observer {
                mediaPlayer_progress.secondaryProgress = ((it.buffered() / 100f) * mediaPlayer_progress.max).toInt()
                mediaPlayer_progress.progress = it.progress()
                mediaPlayer_progress.max = it.duration()
                updateButton(it.isPlaying())
                if (it.completed()) {
                    mediaPlayer.load(intent?.data.toString())
                    mediaPlayer.attachDisplay(mediaPlayer_display.holder)
                    mediaPlayer.playback().play()
                }
            })
            mediaPlayer_progress.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    if (fromUser) {
                        mediaPlayer.playback().seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }
            })
        }
    }

    private fun updateButton(playing: Boolean) {
        if (playing) {
            mediaPlayer_play.setBackgroundResource(android.R.drawable.ic_media_pause)
            mediaPlayer_play.setOnClickListener {
                mediaPlayer.playback().pause()
                mediaPlayer_play.setBackgroundResource(android.R.drawable.ic_media_play)
            }
        } else {
            mediaPlayer_play.setBackgroundResource(android.R.drawable.ic_media_play)
            mediaPlayer_play.setOnClickListener {
                mediaPlayer.playback().play()
                mediaPlayer_play.setBackgroundResource(android.R.drawable.ic_media_pause)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        pendingPlay = savedInstanceState == null && intent?.data != null

        mediaPlayer_display.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder?) {}
            override fun surfaceCreated(holder: SurfaceHolder?) {
                startService(Intent(owner, MediaPlayerService::class.java))
                bindService(
                    Intent(owner, MediaPlayerService::class.java),
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