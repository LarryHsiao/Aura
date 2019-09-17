package com.silverhetch.aura.view.activity.statusbar

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import com.silverhetch.clotho.Action
import com.silverhetch.clotho.utility.color.LuminanceByColor

/**
 * Source to change the status bar and icon theme by luminance.
 *
 * Note that this only takes effect above Android M(6.0).
 *
 * @param threshold use light theme if the luminance is grater then this value.
 */
class StatusBarColor(
    private val window: Window,
    @ColorInt private val color: Int,
    @FloatRange(from = 0.0, to = 1.0)
    private val threshold: Float = 0.5f
) : Action {
    override fun fire() {
        if (SDK_INT >= M) {
            window.clearFlags(FLAG_TRANSLUCENT_STATUS)
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = color
            window.decorView.systemUiVisibility = if (LuminanceByColor(color.toLong()).value() > threshold) {
                window.decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                // first or gate to make sure the light status is enable
                window.decorView.systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR xor SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}