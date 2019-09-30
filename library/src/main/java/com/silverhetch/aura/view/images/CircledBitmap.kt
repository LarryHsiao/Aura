package com.silverhetch.aura.view.images

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.silverhetch.clotho.Source

/**
 * Source to build a Drawable that scale the given Bitmap to circled Bitmap.
 *
 * Simply implemented with [RoundedBitmapDrawableFactory] which is in androidx
 */
class CircledBitmap(
    private val resources: Resources,
    private val bitmap: Bitmap
) : Source<Drawable> {
    override fun value(): Drawable {
        return RoundedBitmapDrawableFactory.create(
            resources,
            bitmap
        ).let { drawable ->
            drawable.isCircular = true
            drawable
        }
    }
}