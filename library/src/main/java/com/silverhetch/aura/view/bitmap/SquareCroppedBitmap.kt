package com.silverhetch.aura.view.bitmap

import android.graphics.Bitmap
import com.silverhetch.clotho.Source

/**
 * Source to build a square [Bitmap] with given Bitmap which the output cropped from.
 */
class SquareCroppedBitmap(private val origin: Source<Bitmap>) : Source<Bitmap> {
    override fun value(): Bitmap {
        val originBmp = origin.value()
        return if (originBmp.width <= originBmp.height) {
            Bitmap.createBitmap(
                originBmp,
                0,
                ((originBmp.height / 2f) - (originBmp.width / 2f)).toInt(),
                originBmp.width,
                originBmp.width
            )
        } else {
            Bitmap.createBitmap(
                originBmp,
                ((originBmp.width / 2f) - (originBmp.height / 2f)).toInt(),
                0,
                originBmp.height,
                originBmp.height
            )
        }
    }
}