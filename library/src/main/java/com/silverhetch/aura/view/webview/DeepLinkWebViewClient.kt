package com.silverhetch.aura.view.webview

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.silverhetch.aura.R

/**
 * WebViewClient for starting Deeplink/Intent activity.
 */
class DeepLinkWebViewClient(
    private val activity: Activity
) : WebViewClientWrapper(
    IntentHandledWebViewClient {
        try {
            activity.startActivity(Intent.parseUri(it, 0))
        } catch (e: Exception) {
            Toast.makeText(
                activity,
                R.string.App_not_installed,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
)