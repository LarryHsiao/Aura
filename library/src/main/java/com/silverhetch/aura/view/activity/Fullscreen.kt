package com.silverhetch.aura.view.activity

import android.app.Activity
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.KITKAT
import android.view.View
import android.view.View.*
import com.silverhetch.clotho.Source

/**
 * Source that adjust given [Activity] window params to fullscreen.
 */
class Fullscreen(private val activity: Activity) : Source<Unit> {
    override fun fetch() {
        var flags = (SYSTEM_UI_FLAG_LAYOUT_STABLE
                or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or SYSTEM_UI_FLAG_FULLSCREEN)
        if (SDK_INT >= KITKAT) {
            flags = flags or (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
        activity.window.decorView.systemUiVisibility = flags
    }
}