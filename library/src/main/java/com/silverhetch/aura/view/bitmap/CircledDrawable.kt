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
 * Source to build a Drawable that scale the given Bitmap to circled Bitmap that
 * enlarge the original [Bitmap] with padding to not cut the corners of original one.
 *
 * Circle implemented with [RoundedBitmapDrawableFactory] which is in androidx.
 */
class CircledDrawable(
    private val resources: Resources,
    private val bitmap: Bitmap
) : Source<Drawable> {
    override fun value(): Drawable {
        val enlargedSize = sqrt(if (bitmap.width > bitmap.height) {
            bitmap.width
        } else {
            bitmap.height
        }.toDouble().pow(2.0) * 2).toInt()
        return RoundedBitmapDrawableFactory.create(
            resources,
            MergedImage(
                ConstSource(Bitmap.createBitmap(
                    enlargedSize,
                    enlargedSize,
                    ARGB_8888
                ).apply {
                    setHasAlpha(true)
                    eraseColor(Color.RED)
                }),
                ConstSource(bitmap),
                (enlargedSize - bitmap.width) / 2,
                (enlargedSize - bitmap.height) / 2
            ).value()
        ).let { drawable ->
            drawable.isCircular = true
            drawable
        }
    }
}