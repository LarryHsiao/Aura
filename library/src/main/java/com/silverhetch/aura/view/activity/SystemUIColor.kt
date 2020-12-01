package com.silverhetch.aura.view.activity

import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import com.silverhetch.aura.view.activity.navigationbar.NavigationBarColor
import com.silverhetch.aura.view.activity.statusbar.StatusBarColor
import com.larryhsiao.clotho.Action

/**
 * Change system UI color
 * @todo #1 Remove duplicate requesting window flags in NavigationBarColor and StatusBarColor.
 */
class SystemUIColor(
    private val window: Window,
    @ColorInt private val color: Int,
    @FloatRange(from = 0.0, to = 1.0)
    private val threshold: Float = 0.5f
) : Action {
    override fun fire() {
        NavigationBarColor(window, color, threshold).fire()
        StatusBarColor(window, color, threshold).fire()
    }
}