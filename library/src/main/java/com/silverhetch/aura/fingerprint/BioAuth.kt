package com.silverhetch.aura.fingerprint

import android.app.KeyguardManager
import android.content.Context.KEYGUARD_SERVICE
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Build.VERSION_CODES.Q
import android.os.Handler
import android.os.Looper
import androidx.biometric.BiometricConstants.ERROR_CANCELED
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.fragment.app.FragmentActivity
import com.silverhetch.aura.R
import com.silverhetch.clotho.Action
import java.util.concurrent.Executors.newSingleThreadExecutor

/**
 * Bio auth simple wrapper, to make the implementation a little bit easier .
 */
class BioAuth(
    private val activity: FragmentActivity,
    private val success: () -> Unit = {},
    private val failed: (code: Int, error: String) -> Unit
) : Action, BiometricPrompt.AuthenticationCallback() {
    private val mainThread = Handler(Looper.getMainLooper())
    override fun fire() {
        val keyguardManager = activity.getSystemService(KEYGUARD_SERVICE) as KeyguardManager
        val fallbackAuth = if (SDK_INT > M && SDK_INT < Q) {
            // The Q and above's fallback auth don't have success callback
            keyguardManager.isDeviceSecure
        } else {
            false
        }
        val promote = BiometricPrompt(
            activity,
            newSingleThreadExecutor(),
            this
        )
        promote.authenticate(
            PromptInfo.Builder()
                .setTitle(activity.getString(R.string.authorization))
                .setDeviceCredentialAllowed(fallbackAuth)
                .apply {
                    if (!fallbackAuth) {
                        setNegativeButtonText(activity.getString(R.string.cancel))
                    }
                }.build()
        )
    }

    override fun onAuthenticationError(code: Int, err: CharSequence) {
        super.onAuthenticationError(code, err)
        when (code) {
            ERROR_CANCELED -> {
            }
            else -> mainThread.post { failed(code, err.toString()) }
        }
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        super.onAuthenticationSucceeded(result)
        mainThread.post(success)
    }

    override fun onAuthenticationFailed() {
        super.onAuthenticationFailed()
        /*
        Ignore auth failed, the promote UI will inform the failure to user.
        The error will triggered as canceled by user which is the only event user can trigger.
        */
    }
}