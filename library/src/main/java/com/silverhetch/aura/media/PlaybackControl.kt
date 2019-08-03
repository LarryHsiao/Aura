package com.silverhetch.aura.media

/**
 * Object to control the player`s playback
 */
interface PlaybackControl {

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
}