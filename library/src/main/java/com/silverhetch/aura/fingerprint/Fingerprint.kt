package com.silverhetch.aura.fingerprint

/**
 * Object to represent the fingerprint function. With app stored state for enable state.
 *
 */
interface Fingerprint {
    /**
     * determine if the fingerprint function available
     */
    fun isSupported(): Boolean

    /**
     * True if fingerprint function supported and enabled.
     */
    fun isEnabled(): Boolean

    /**
     * Enable/disable fingerprint function
     */
    fun enable(enable: Boolean)
}