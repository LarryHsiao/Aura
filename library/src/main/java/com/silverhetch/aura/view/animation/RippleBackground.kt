package com.silverhetch.aura.view.animation

import android.content.Context
import android.util.TypedValue
import androidx.annotation.DrawableRes
import com.larryhsiao.clotho.Source

/**
 * Ripple background resource id
 */
class RippleBackground(private val context: Context) : Source<Int> {
    @DrawableRes
    override fun value(): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        return outValue.resourceId
    }
}