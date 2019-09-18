package com.silverhetch.aura.view.images

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri

/**
 * Content Resolver [MnemeItem]
 *
 * Prefix CR for 'Content Resolver'.
 */
class CRImage(
    private val context: Context,
    private val url: Uri
) : MnemeItem {
    override fun id(): String {
        return url.toString()
    }

    override fun icon(): Drawable {
        return BitmapDrawable(
            context.resources,
            BitmapFactory.decodeStream(
                context.contentResolver.openInputStream(
                    url
                )
            )
        )
    }
}