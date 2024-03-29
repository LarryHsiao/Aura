package com.larryhsiao.aurademo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Bitmap.Config.ARGB_8888
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.intent.ChooserIntent
import com.larryhsiao.aura.view.RandomColors
import com.larryhsiao.aura.view.images.BitmapItem
import com.larryhsiao.aura.view.images.CRImage
import com.larryhsiao.aura.view.images.MnemeItem
import com.larryhsiao.aurademo.R
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
        mnemeImage.initImages(list,true)
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
