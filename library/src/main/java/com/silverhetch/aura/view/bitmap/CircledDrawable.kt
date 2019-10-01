package com.silverhetch.aura.view.bitmap

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.silverhetch.clotho.Source
import com.silverhetch.clotho.source.ConstSource
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Source to build a Drawable that crops the given bitmap to circle one.
 *
 * Circle implemented with [RoundedBitmapDrawableFactory] which is in androidx.
 */
class CircledDrawable(
    private val resources: Resources,
    private val bitmap: Source<Bitmap>
) : Source<Drawable> {
    override fun value(): Drawable {
        return RoundedBitmapDrawableFactory.create(
            resources,
            bitmap.value()
        ).let { drawable ->
            drawable.isCircular = true
            drawable
        }
    }
}