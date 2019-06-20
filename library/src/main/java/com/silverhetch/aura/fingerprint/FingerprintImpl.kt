package com.silverhetch.aura.fingerprint

import android.content.Context
import android.os.Build
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.silverhetch.clotho.storage.Ceres

/**
 * Implementation of [Fingerprint]
 */
class FingerprintImpl(private val manager: FingerprintManagerCompat, private val storage: Ceres) : Fingerprint {
    companion object {
        private const val KEY_FINGERPRINT_ENABLE = "KEY_FINGERPRINT_ENABLE"
    }

    override fun isSupported(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && manager.isHardwareDetected
    }

    override fun isEnabled(): Boolean {
        return manager.hasEnrolledFingerprints()
            && isSupported()
            && storage.get(KEY_FINGERPRINT_ENABLE).toLowerCase() == "true"
    }

    override fun enable(enable: Boolean) {
        storage.store(KEY_FINGERPRINT_ENABLE, enable.toString().toLowerCase())
    }
}