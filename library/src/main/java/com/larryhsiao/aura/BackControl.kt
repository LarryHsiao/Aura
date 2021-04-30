package com.larryhsiao.aura

/**
 * Object that handles the back press event.
 */
interface BackControl {
    /**
     * Deliver back press event.
     */
    fun onBackPress(): Boolean
}