package com.larryhsiao.aurademo

import android.app.Activity
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import com.larryhsiao.aura.view.webview.DeepLinkWebViewClient

/**
 * Activity for demo webView client that starts activity for intent/deeplink.
 */
class DeepLinkClientDemoActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            WebView(this).apply {
                layoutParams = ViewGroup.LayoutParams(
                    MATCH_PARENT,
                    MATCH_PARENT
                )
                webViewClient = DeepLinkWebViewClient(this@DeepLinkClientDemoActivity)
                settings.javaScriptEnabled = true
                settings.databaseEnabled = true
                settings.domStorageEnabled = true
                loadUrl("https://larryhsiao.com:13000/deepLinkpages.html")
            }
        )
    }
}