package com.silverhetch.aurademo

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.view.activity.TransparentSource

/**
 * Demo activity to apply transparent theme.
 */
class TransparentDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TransparentSource(this).value()

        setContentView(FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(ImageView(this@TransparentDemoActivity).apply {
                layoutParams = FrameLayout.LayoutParams(250, 250)
                setImageBitmap(Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                    .also { it.eraseColor(Color.CYAN) }
                )
            })
        })
    }
}