package com.silverhetch.aura.view.activity.navigationbar

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.LOLLIPOP
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import com.silverhetch.clotho.Action
import com.silverhetch.clotho.utility.color.LuminanceByColor

/**
 * Change the navigation bar color.
 */
class NavigationBarColor(
    private val window: Window,
    @ColorInt private val color: Int,
    @FloatRange(from = 0.0, to = 1.0) private val threshold: Float = 0.5f
) : Action {
    override fun fire() {
        if (SDK_INT >= LOLLIPOP) {
            window.navigationBarColor = color
            window.clearFlags(FLAG_TRANSLUCENT_STATUS)
            window.addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.decorView.apply {
                systemUiVisibility = if (LuminanceByColor(color.toLong()).value() > threshold) {
                    systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                } else {
                    systemUiVisibility or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR xor SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                }
            }
        }
    }
}