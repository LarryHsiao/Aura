package com.silverhetch.aura.view.bitmap

import android.graphics.Bitmap
import android.graphics.Rect
import com.silverhetch.clotho.Source

/**
 * Crop a [Bitmap] with given coordinator.
 */
class CroppedImage(private val source: Source<Bitmap>, private val rect: Rect) : Source<Bitmap> {
    override fun value(): Bitmap {
        val bitmap = source.value()
        return Bitmap.createBitmap(
                bitmap,
                rect.left,
                rect.top,
                if(rect.left + rect.width() <= bitmap.width) {
                    rect.width()
                }else{
                    bitmap.width - rect.left
                },
                if (rect.top + rect.height() <= bitmap.height) {
                    rect.height()
                }else{
                    bitmap.height - rect.top
                }
        )
    }
}