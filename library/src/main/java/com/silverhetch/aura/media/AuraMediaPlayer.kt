package com.silverhetch.aura.media

import android.util.Size
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData

/**
 * Aura style of Media Player
 */
interface AuraMediaPlayer {
    fun load(url: String)
    fun play()
    fun pause()
    fun seekTo(position: Int)
    fun progress(): LiveData<Int>
    fun buffered(): LiveData<Int>
    fun size(): LiveData<Size>
    fun attachDisplay(surfaceHolder: SurfaceHolder)
    fun detachDisplay()
}