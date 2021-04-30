package com.larryhsiao.aura.media

/**
 * Represent a Media recorder
 */
interface Record {
    /**
     * Start recording
     */
    fun start()

    /**
     * End recording
     */
    fun stop()

    /**
     * Determine if this instance is till running.
     */
    fun running(): Boolean
}