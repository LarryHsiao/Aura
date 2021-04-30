package com.larryhsiao.aura.media

/**
 * Current playback state of media.
 */
interface State {
    /**
     * Determine if this media player is playing something.
     */
    fun isPlaying(): Boolean

    /**
     * Current loaded media duration in milliseconds.
     */
    fun duration(): Int

    /**
     * Current loaded media streams buffered percentage.
     */
    fun buffered(): Int

    /**
     * Current playing progress in milliseconds.
     */
    fun progress(): Int

    /**
     * Flag indicates that the meida is finished playback at this point.
     */
    fun completed(): Boolean
}