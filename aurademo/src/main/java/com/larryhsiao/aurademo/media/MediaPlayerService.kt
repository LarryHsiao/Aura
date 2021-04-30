package com.larryhsiao.aurademo.media

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.larryhsiao.aura.media.AuraMediaPlayer
import com.larryhsiao.aura.media.AuraMediaPlayerImpl

/**
 * Media player service for general purpose media playback.
 */
class MediaPlayerService : Service() {
    private val binder = Binder()
    private lateinit var mediaPlayer: AuraMediaPlayer

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = AuraMediaPlayerImpl(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    inner class Binder : android.os.Binder() {
        fun mediaPlayer(): AuraMediaPlayer {
            return mediaPlayer
        }
    }
}

