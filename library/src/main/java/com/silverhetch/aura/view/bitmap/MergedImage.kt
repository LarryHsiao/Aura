package com.silverhetch.aura.view.bitmap

import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Canvas
import com.silverhetch.clotho.Source

/**
 * Merge two [Bitmap] into one.
 *
 * @param x coordinator that the overlay drawn.
 * @param y coordinator that the overlay drawn.
 */
class MergedImage(
    private val original: Source<Bitmap>,
    private val overlay: Source<Bitmap>,
    private val x: Int,
    private val y: Int
) : Source<Bitmap> {
    override fun value(): Bitmap {
        val result = original.value().copy(ARGB_8888, true)
        Canvas(result).apply {
            drawBitmap(overlay.value(), x.toFloat(), y.toFloat(), null)
        }
        return result
    }
}