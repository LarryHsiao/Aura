package com.silverhetch.aurademo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Point
import android.os.Bundle
import android.os.IBinder
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.media.AuraMediaPlayer
import com.silverhetch.aura.media.MediaPlayerService
import kotlinx.android.synthetic.main.activity_media_player.*

/**
 * Demo activity for media [MediaPlayerService]
 *
 * @todo #2 review this class to determine if this class is finish.
 */
class MediaPlayerDemoActivity : AuraActivity(), ServiceConnection {
    private lateinit var mediaPlayer: AuraMediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
    }

    override fun onResume() {
        super.onResume()
        startService(Intent(this, MediaPlayerService::class.java))
        bindService(
                Intent(this, MediaPlayerService::class.java),
                this,
                Context.BIND_AUTO_CREATE
        )
    }

    override fun onPause() {
        super.onPause()
        unbindService(this)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        mediaPlayer = (service as MediaPlayerService.Binder).mediaPlayer()
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.load("https://www.radiantmediaplayer.com/media/bbb-360p.mp4")
        }
        mediaPlayer.attachDisplay(mediaPlayer_display.holder)
        mediaPlayer.videoSize().observe(this, Observer {
            mediaPlayer_display.layoutParams = mediaPlayer_display.layoutParams.apply {
                width = Point().run {
                    windowManager.defaultDisplay.getSize(this)
                    x
                }
                height = ((it.y.toFloat() / it.x.toFloat()) * width).toInt()
            }
        })
        mediaPlayer.buffered().observe(this, Observer {
            mediaPlayer_progress.secondaryProgress = ((it / 100f) * mediaPlayer_progress.max).toInt()
        })
        mediaPlayer.progress().observe(this, Observer {
            runOnUiThread {
                mediaPlayer_progress.progress = it
            }
        })
        mediaPlayer.duration().observe(this, Observer {
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