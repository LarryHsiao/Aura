package com.silverhetch.aurademo

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.clotho.Source
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
        mnemeImage.initImages(*list.toTypedArray())
        mnemeImage.setCallback { index, isAddingButton ->
            if (isAddingButton){
                Toast.makeText(this, "Add button", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Index: $index", Toast.LENGTH_SHORT).show()
            }
        }
        Handler().apply{
            postDelayed({mnemeImage.addable(true)}, 1000)
            postDelayed({mnemeImage.addable(false)}, 3000)
            postDelayed({mnemeImage.addable(true)}, 5000)
        }
    }
}
