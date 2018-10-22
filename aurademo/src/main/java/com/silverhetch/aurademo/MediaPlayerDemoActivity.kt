package com.silverhetch.aurademo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.lifecycle.Observer
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.media.MediaPlayerService
import kotlinx.android.synthetic.main.activity_media_player.*

class MediaPlayerDemoActivity : AuraActivity(), ServiceConnection {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)
    }

    override fun onResume() {
        super.onResume()
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
        val mediaPlayer = (service as MediaPlayerService.Binder).mediaPlayer()
        mediaPlayer.load("https://www.radiantmediaplayer.com/media/bbb-360p.mp4")
        mediaPlayer.attachDisplay(mediaPlayer_display.holder)
        mediaPlayer.buffered().observe(this, Observer {
            mediaPlayer_progress.secondaryProgress = it
        })
        mediaPlayer.progress().observe(this, Observer {
            runOnUiThread {
                mediaPlayer_progress.progress = it
            }
        })

        mediaPlayer_play.setOnClickListener { mediaPlayer.play() }
        mediaPlayer_pause.setOnClickListener { mediaPlayer.pause() }
    }

}