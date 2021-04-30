package com.larryhsiao.aura.fingerprint

import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import org.robolectric.annotation.Implements

@Implements(FingerprintManagerCompat::class)
class ShadowFingerprintManagerCompat {
    var hasEnroll = false
    var hasHardware = false

    fun hasEnrolledFingerprints(): Boolean {
        return hasEnroll
    }

    fun isHardwareDetected(): Boolean {
        return hasHardware
    }
}