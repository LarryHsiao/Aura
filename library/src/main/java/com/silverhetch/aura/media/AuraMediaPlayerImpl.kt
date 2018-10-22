package com.silverhetch.aura.media

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.util.Size
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.concurrent.TimeUnit

class AuraMediaPlayerImpl(private val context: Context) : AuraMediaPlayer {

    private val progress = MutableLiveData<Int>().apply { value = 0 }
    private val buffered = MutableLiveData<Int>().apply { value = 0 }
    private val videoSize = MutableLiveData<Size>().apply { value = Size(0, 0) }
    private val handler = Handler()
    private val mediaPlayer: MediaPlayer = MediaPlayer()

    override fun load(url: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(context, Uri.parse(url))
        mediaPlayer.setOnVideoSizeChangedListener { mp, width, height ->
            videoSize.value = Size(width, height)
        }
        mediaPlayer.setOnBufferingUpdateListener { mp, percent ->
            buffered.value = percent
        }
        mediaPlayer.prepareAsync()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if (mediaPlayer.duration != 0) {
                    progress.value = (mediaPlayer.currentPosition.toFloat() / mediaPlayer.duration.toFloat() * 100f).toInt()
                }
                handler.postDelayed(this, TimeUnit.SECONDS.toMillis(1))
            }
        }, TimeUnit.SECONDS.toMillis(1))
    }

    override fun play() {
        mediaPlayer.start()
    }

    override fun pause() {
        mediaPlayer.pause()
    }

    override fun seekTo(position: Int) {
        mediaPlayer.seekTo(position)
    }

    override fun progress(): LiveData<Int> {
        return progress
    }

    override fun buffered(): LiveData<Int> {
        return buffered
    }

    override fun size(): LiveData<Size> {
        return videoSize
    }

    override fun attachDisplay(surfaceHolder: SurfaceHolder) {
        mediaPlayer.setDisplay(surfaceHolder)
    }

    override fun detachDisplay() {
        mediaPlayer.setDisplay(null)
    }
}