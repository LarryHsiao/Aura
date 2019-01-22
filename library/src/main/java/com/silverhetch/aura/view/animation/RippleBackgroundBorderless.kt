package com.silverhetch.aura.view.animation

import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.annotation.DrawableRes
import com.silverhetch.clotho.Source


/**
 * Ripple background resource id
 */
class RippleBackgroundBorderless(private val context: Context) : Source<Int> {
    @DrawableRes
    override fun fetch(): Int {
        val outValue = TypedValue()
        context.theme.resolveAttribute(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    android.R.attr.selectableItemBackgroundBorderless
                } else {
                    // fallback
                    android.R.attr.selectableItemBackground
                },
                outValue,
                true
        )
        return outValue.resourceId
    }
}