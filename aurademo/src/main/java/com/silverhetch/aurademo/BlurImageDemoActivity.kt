package com.silverhetch.aurademo

import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.clotho.ConstSource
import com.silverhetch.aura.view.bitmap.BlurImage
import com.silverhetch.aura.view.bitmap.CroppedImage
import com.silverhetch.aura.view.bitmap.MergedImage
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


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
                        val original = ConstSource((imageView.drawable as BitmapDrawable).bitmap)
                        val result = MergedImage(
                                original,
                                BlurImage(
                                        this@BlurImageDemoActivity,
                                        CroppedImage(
                                                original,
                                                Rect(50, 50, 99999, 9999)
                                        ),
                                        25f
                                ), 50, 50).fetch()
                        imageView.setImageBitmap(result)
                    }

                    override fun onError(e: Exception?) {
                    }
                })
    }
}