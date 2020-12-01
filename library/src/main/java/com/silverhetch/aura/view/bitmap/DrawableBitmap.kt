package com.silverhetch.aura.view.bitmap

import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Bitmap.Config.RGB_565
import android.graphics.Canvas
import android.graphics.PixelFormat.OPAQUE
import android.graphics.drawable.Drawable
import com.larryhsiao.clotho.Source

/**
 * Source to generate [Bitmap] from drawable implemented with canvas.
 */
class DrawableBitmap(private val drawable: Drawable) : Source<Bitmap> {
    override fun value(): Bitmap {
        val w = drawable.intrinsicWidth
        val h = drawable.intrinsicHeight
        val bitmap = Bitmap.createBitmap(w, h, if (drawable.opacity != OPAQUE) {
            ARGB_8888
        } else {
            RGB_565
        })
        Canvas(bitmap).also {
            drawable.setBounds(0, 0, w, h)
            drawable.draw(it)
        }
        return bitmap
    }
}