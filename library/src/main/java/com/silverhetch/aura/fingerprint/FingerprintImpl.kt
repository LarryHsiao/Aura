package com.silverhetch.aura.fingerprint

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.silverhetch.clotho.storage.Ceres

/**
 * Implementation of [Fingerprint]
 */
@SuppressLint("MissingPermission") // Permission should declared at app.
class FingerprintImpl(private val manager: FingerprintManagerCompat, private val storage: Ceres) : Fingerprint {
    companion object {
        private const val KEY_FINGERPRINT_ENABLE = "KEY_FINGERPRINT_ENABLE"
    }

    override fun isSupported(): Boolean {
        return SDK_INT >= M && manager.isHardwareDetected
    }

    override fun isEnabled(): Boolean {
        return manager.hasEnrolledFingerprints() &&
            isSupported() &&
            storage.get(KEY_FINGERPRINT_ENABLE).toLowerCase() == "true"
    }

    override fun enable(enable: Boolean) {
        storage.store(KEY_FINGERPRINT_ENABLE, enable.toString().toLowerCase())
    }
}