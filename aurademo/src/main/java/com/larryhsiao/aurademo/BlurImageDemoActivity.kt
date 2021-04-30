package com.larryhsiao.aurademo

import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.view.bitmap.BlurImage
import com.larryhsiao.aura.view.bitmap.CroppedImage
import com.larryhsiao.aura.view.bitmap.MergedImage
import com.larryhsiao.aura.view.bitmap.ResizedImage
import com.larryhsiao.clotho.source.ConstSource
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Demo activity to show the result of blur image
 */
class BlurImageDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imageView = ImageView(this)
        imageView.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        setContentView(imageView)

        // Blur part of image in Rect(50,50,500,500)
        Picasso.get()
            .load("https://images.freeimages.com/images/large-previews/a0d/autumn-tree-1382832.jpg")
            .into(imageView, object : Callback {
                override fun onSuccess() {
                    if (imageView.drawable !is BitmapDrawable) {
                        return
                    }
                    val original = ConstSource((imageView.drawable as BitmapDrawable).bitmap)
                    val result = ResizedImage(
                        MergedImage(
                        original,
                        BlurImage(
                            this@BlurImageDemoActivity,
                            CroppedImage(
                                original,
                                Rect(50, 50, 99999, 9999)
                            ),
                            25f
                        ), 50, 50),
                        100
                    ).value()
                    imageView.setImageBitmap(result)
                }

                override fun onError(e: Exception?) {
                }
            })
    }
}