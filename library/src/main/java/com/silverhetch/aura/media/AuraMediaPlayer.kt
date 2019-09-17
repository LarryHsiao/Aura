package com.silverhetch.aura.media

import android.graphics.Point
import android.view.SurfaceHolder
import androidx.lifecycle.LiveData

/**
 * Aura style of Media Player
 *
 * @todo #refactor reduce method numbers.
 */
interface AuraMediaPlayer {
    /**
     * Current state of this player
     */
    fun state(): LiveData<State>

    /**
     * Object for control play back
     */
    fun playback(): PlaybackControl

    /**
     * Load the media by uri
     */
    fun load(uri: String)

    /**
     * Attach video onto views
     */
    fun attachDisplay(surfaceHolder: SurfaceHolder)

    /**
     * Detach video from view.
     */
    fun detachDisplay()

    /**
     * Current loaded media video frame width and height.
     */
    fun videoSize(): LiveData<Point>

    /**
     * Release holding resources.
     */
    fun release()
}