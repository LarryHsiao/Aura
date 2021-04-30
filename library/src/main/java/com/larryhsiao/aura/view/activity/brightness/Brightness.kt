package com.larryhsiao.aura.view.activity.brightness

/**
 * The screen brightness.
 */
interface Brightness {
    /**
     * Current value
     */
    fun value(): Float

    /**
     * Adjust the brightness value.
     */
    fun set(new: Float)
}