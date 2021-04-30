package com.larryhsiao.aura.media

import android.graphics.Bitmap
import android.graphics.Color
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for com.larryhsiao.aura.media.BitmapStream
 */
@RunWith(RobolectricTestRunner::class)
class BitmapStreamTest {
    /**
     * Check if this procedure can be done normally and have some output.
     */
    @Test
    fun simple() {
        assertNotEquals(
            0,
            BitmapStream(
                Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).also {
                    it.eraseColor(Color.RED)
                }
            ).value().readBytes().size
        )
    }
}