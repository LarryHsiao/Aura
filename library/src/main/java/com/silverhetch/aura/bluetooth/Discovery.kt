package com.silverhetch.aura.bluetooth

import androidx.lifecycle.LiveData

/**
 * Device discovery.
 */
interface Discovery {

    /**
     * Stop discovery processes and release resources.
     */
    fun stop()

    /**
     * Start the enable.
     */
    fun start()

    /**
     * Trigger a search process
     */
    fun search()

    /**
     * Indicate discovering process is running.
     */
    fun running(): Boolean

    /**
     * Current maintained remote devices.
     */
    fun remoteDevice(): LiveData<MutableMap<String, CRemoteDevice>>
}