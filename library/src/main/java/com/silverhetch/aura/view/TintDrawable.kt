package com.silverhetch.aura.view

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import com.larryhsiao.clotho.Source

/**
 * Source to generate drawable that tint with given color.
 */
class TintDrawable(
    private val drawable: Drawable,
    @ColorInt private val color: Int
) :
    Source<Drawable> {
    override fun value(): Drawable {
        return DrawableCompat.wrap(drawable).mutate().apply {
            DrawableCompat.setTint(this, color)
        }
    }
}