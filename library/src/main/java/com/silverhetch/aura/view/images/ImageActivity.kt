package com.silverhetch.aura.view.images

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.silverhetch.aura.R
import com.squareup.picasso.Picasso

/**
 * Activity simply fetch and show a image by Url
 */
class ImageActivity : AppCompatActivity() {
    companion object {
        private const val ARG_URL = "ARG_URL"
        fun newIntent(context: Context, url: String): Intent {
            return Intent(context, ImageActivity::class.java).apply {
                putExtra(ARG_URL, url)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        Picasso.get().load(intent.getStringExtra(ARG_URL)).placeholder(
                CircularProgressDrawable(this).apply {
                    setStyle(CircularProgressDrawable.DEFAULT)
                }
        ).into(findViewById<ImageView>(R.id.image))
    }
}