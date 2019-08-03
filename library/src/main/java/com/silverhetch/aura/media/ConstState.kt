package com.silverhetch.aura.media

/**
 * Const of state
 */
class ConstState(
    private val playing: Boolean = false,
    private val duration: Int = 0,
    private val buffered: Int = 0,
    private val progress: Int = 0,
    private val completed: Boolean = false
) : State {
    override fun isPlaying(): Boolean {
        return playing
    }

    override fun duration(): Int {
        return duration
    }

    override fun buffered(): Int {
        return buffered
    }

    override fun progress(): Int {
        return progress
    }

    override fun completed(): Boolean {
        return completed && progress != 0 && duration >= 0
    }
}