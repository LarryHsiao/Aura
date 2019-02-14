package com.silverhetch.aura.media

import android.graphics.Point
import android.util.Size
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData

/**
 * Aura style of Media Player
 */
interface AuraMediaPlayer {
    /**
     * Load the media by uri
     */
    fun load(uri: String)

    /**
     * Trigger play
     */
    fun play()

    /**
     * Trigger pause
     */
    fun pause()

    /**
     * Seek to target position.
     */
    fun seekTo(position: Int)

    /**
     * Current loaded media duration in milliseconds.
     */
    fun duration(): LiveData<Int>

    /**
     * Current playing progress in milliseconds.
     */
    fun progress(): LiveData<Int>

    /**
     * Current loaded media streams buffered percentage.
     */
    fun buffered(): LiveData<Int>

    /**
     * Current loaded media video frame width and height.
     */
    fun videoSize(): LiveData<Point>

    /**
     * Attach video onto views
     */
    fun attachDisplay(surfaceHolder: SurfaceHolder)

    /**
     * Detach video from view.
     */
    fun detachDisplay()

    /**
     * Release holding resources.
     */
    fun release()
}