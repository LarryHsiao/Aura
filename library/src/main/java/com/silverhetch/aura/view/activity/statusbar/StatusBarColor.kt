package com.silverhetch.aura.view.activity.statusbar

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import com.silverhetch.clotho.Source
import com.silverhetch.clotho.utility.color.LuminanceByColor

/**
 * Source to change the status bar.
 *
 * Note that this only takes effect above Android M(6.0)
 */
class StatusBarColor(private val window: Window, @ColorInt private val color: Int) : Source<Unit> {
    override fun value() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.statusBarColor = color
            window.decorView.systemUiVisibility = if (LuminanceByColor(color.toLong()).value() > 0.5) {
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // first or gate to make sure the light status is enable
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}