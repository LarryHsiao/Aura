package com.silverhetch.aura.view.webview

import android.annotation.TargetApi
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * WebView client for redirect deeplink/Intent uri when loading pages.
 */
class IntentHandledWebViewClient(
    private val callback: (uri: String) -> Unit
) : WebViewClient() {
    @TargetApi(21)
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        return try {
            shouldOverrideUrlLoading(view, request?.url)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return try {
            shouldOverrideUrlLoading(view, Uri.parse(url))
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    private fun shouldOverrideUrlLoading(view: WebView?, url: Uri?): Boolean {
        if (url != null && (url.scheme?.startsWith("http") != true)) {
            return handleUrlIntent(url)
        }
        return false
    }

    private fun handleUrlIntent(url: Uri?): Boolean {
        return url?.toString()?.let {
            callback(it)
            true
        } ?: false
    }
}