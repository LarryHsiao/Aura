package com.silverhetch.aurademo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.*
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.silverhetch.aura.intent.ChooserIntent
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.aura.view.images.BitmapItem
import com.silverhetch.aura.view.images.CRImage
import com.silverhetch.aura.view.images.MnemeItem
import com.silverhetch.clotho.Source
import com.silverhetch.clotho.source.ConstSource
import kotlinx.android.synthetic.main.activity_mneme_image_grid.*

class MnemeImageGridDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mneme_image_grid)

        val list = ArrayList<MnemeItem>()
        for (i in 0..2) {
            list.add(BitmapItem(
                this,
                Bitmap.createBitmap(1, 1, ARGB_8888).also {
                    it.eraseColor(RandomColors().value())
                }
            ))
        }
        mnemeImage.initImages(list)
        mnemeImage.setCallback { item, isAddingButton ->
            if (isAddingButton) {
                Toast.makeText(
                    this,
                    "Current size: ${mnemeImage.sources().size}.",
                    Toast.LENGTH_SHORT
                ).show()
                startActivityForResult(
                    ChooserIntent(
                        this,
                        "",
                        Intent(Intent.ACTION_GET_CONTENT).also { it.type = "image/*" }
                    ).value(),
                    1000
                )
            } else {
                Toast.makeText(this, "Item: ${item.id()}", Toast.LENGTH_SHORT).show()
            }
        }
        Handler().apply {
            postDelayed({ mnemeImage.addable(true) }, 1000)
            postDelayed({ mnemeImage.addable(false) }, 3000)
            postDelayed({ mnemeImage.addable(true) }, 5000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1000) {
            data?.data?.also { imageUri ->
                mnemeImage.addImage(CRImage(this, imageUri))
            }
        }
    }
}
