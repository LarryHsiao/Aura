package com.silverhetch.aura.media

import android.graphics.Bitmap
import com.larryhsiao.clotho.Source
import java.io.*

/**
 * Source generate a PNG [InputStream] of Bitmap.
 */
class BitmapStream(private val bitmap: Bitmap) : Source<InputStream> {
    override fun value(): InputStream {
        ByteArrayOutputStream().use { outStream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outStream)
            return ByteArrayInputStream(outStream.toByteArray())
        }
    }
}