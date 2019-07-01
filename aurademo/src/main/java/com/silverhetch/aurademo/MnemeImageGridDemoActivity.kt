package com.silverhetch.aurademo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.silverhetch.aura.intent.ChooserIntent
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.clotho.Source
import com.silverhetch.clotho.source.ConstSource
import kotlinx.android.synthetic.main.activity_mneme_image_grid.*

class MnemeImageGridDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mneme_image_grid)

        val list = ArrayList<Source<Drawable>>()
        for (i in 0..2) {
            list.add(object : Source<Drawable> {
                override fun value(): Drawable {
                    return BitmapDrawable(resources, Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).also {
                        it.eraseColor(RandomColors().value())
                    })
                }
            })
        }
        mnemeImage.initImages(list)
        mnemeImage.setCallback { index, isAddingButton ->
            if (isAddingButton) {
                Toast.makeText(
                    this,
                    "Current size: ${mnemeImage.sources().size}.",
                    Toast.LENGTH_SHORT
                ).show()
                startActivityForResult(
                    ChooserIntent(
                        "",
                        Intent(Intent.ACTION_GET_CONTENT).also { it.type = "image/*" }
                    ).value(),
                    1000
                )
            } else {
                Toast.makeText(this, "Index: $index", Toast.LENGTH_SHORT).show()
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
            mnemeImage.addImage(ConstSource(
                BitmapDrawable(
                    resources,
                    BitmapFactory.decodeStream(
                        contentResolver.openInputStream(
                            data!!.data!!
                        )
                    )
                )
            ))
        }
    }
}
