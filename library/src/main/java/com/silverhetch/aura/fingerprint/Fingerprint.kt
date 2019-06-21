package com.silverhetch.aura.fingerprint

/**
 * Object to represent the fingerprint function. For determine if the fingerprint available and record the enable state.
 */
interface Fingerprint {
    /**
     * determine if the fingerprint function available
     */
    fun isSupported(): Boolean

    /**
     * True if fingerprint function supported and enabled.
     * This will always false if device not supported.
     */
    fun isEnabled(): Boolean

    /**
     * Enable/disable fingerprint function.
     * This will never change the isEnable() state if device not supported.
     */
    fun enable(enable: Boolean)
}