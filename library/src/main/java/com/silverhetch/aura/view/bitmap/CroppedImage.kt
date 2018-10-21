package com.silverhetch.aura.view.bitmap

import android.graphics.Bitmap
import android.graphics.Rect
import com.silverhetch.clotho.Source

/**
 * Crop a [Bitmap] with given coordinator.
 */
class CroppedImage(private val source: Source<Bitmap>, private val rect: Rect) : Source<Bitmap> {
    override fun fetch(): Bitmap {
        return Bitmap.createBitmap(
                source.fetch(),
                rect.left,
                rect.top,
                rect.width(),
                rect.height()
        )
    }
}