package com.silverhetch.aura.bluetooth

/**
 * Remote device
 */
interface CRemoteDevice {
    /**
     * Device name
     */
    fun name(): String

    /**
     * Address of remote device.
     */
    fun address(): String

    /**
     * Send given string data.
     */
    fun send(message: String)
}