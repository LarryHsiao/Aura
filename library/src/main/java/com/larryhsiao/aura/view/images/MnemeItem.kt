package com.larryhsiao.aura.view.images

import android.graphics.drawable.Drawable

/**
 * Item that used in [MnemeImageGrid]
 */
interface MnemeItem {
    /**
     * Id for [MnemeImageGrid] to recognize if the iamge is the same
     */
    fun id(): String

    /**
     * Generate [Drawable] for imageView
     */
    fun icon(): Drawable
}