package com.silverhetch.aura.sercurity

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.silverhetch.clotho.Source

/**
 * Source to determine if the device have pin/password
 */
class IsDeviceSecure(
    private val context: Context
) : Source<Boolean> {
    override fun value(): Boolean {
        val keyguardManager = ContextCompat.getSystemService<KeyguardManager>(
            context,
            KeyguardManager::class.java
        ) ?: return false
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            keyguardManager.isDeviceSecure
        } else {
            keyguardManager.isKeyguardSecure
        }
    }
}