package com.silverhetch.aura.view.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import com.silverhetch.clotho.Source

/**
 * Image source that apply BLur effect to given [Bitmap].
 */
class BlurImage(private val context: Context, private val source: Source<Bitmap>, private val radius: Float = 25f) : Source<Bitmap> {
    override fun value(): Bitmap {
        val image = source.value()
        val inputBitmap = Bitmap.createScaledBitmap(image, image.width, image.height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)
        val rs = RenderScript.create(context)
        val blur = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
        blur.setRadius(radius)
        blur.setInput(tmpIn)
        blur.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)
        return outputBitmap
    }
}