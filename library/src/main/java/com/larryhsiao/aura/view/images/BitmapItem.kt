package com.larryhsiao.aura.view.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * Bitmap instance of [MnemeItem]
 */
class BitmapItem(
    private val context: Context,
    private val bitmap: Bitmap
) : MnemeItem {
    override fun id(): String {
        return bitmap.toString()
    }

    override fun icon(): Drawable {
        return BitmapDrawable(context.resources, bitmap)
    }
}