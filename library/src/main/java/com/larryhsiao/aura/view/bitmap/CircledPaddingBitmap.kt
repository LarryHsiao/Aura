package com.larryhsiao.aura.view.bitmap

import android.graphics.Bitmap
import android.graphics.Color
import com.larryhsiao.clotho.Source
import com.larryhsiao.clotho.source.ConstSource
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Source that build a [Bitmap] enlarges the given [Bitmap] to make it not cropped
 * the original [Bitmap] when doing a circular crop.
 */
class CircledPaddingBitmap(private val bitmap: Bitmap) : Source<Bitmap> {
    override fun value(): Bitmap {
        val enlargedSize = sqrt(if (bitmap.width > bitmap.height) {
            bitmap.width
        } else {
            bitmap.height
        }.toDouble().pow(2.0) * 2).toInt()
        return MergedImage(
            ConstSource(Bitmap.createBitmap(
                enlargedSize,
                enlargedSize,
                Bitmap.Config.ARGB_8888
            ).apply {
                setHasAlpha(true)
                eraseColor(Color.RED)
            }),
            ConstSource(bitmap),
            (enlargedSize - bitmap.width) / 2,
            (enlargedSize - bitmap.height) / 2
        ).value()
    }
}