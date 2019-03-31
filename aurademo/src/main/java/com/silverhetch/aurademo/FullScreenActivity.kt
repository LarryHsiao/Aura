package com.silverhetch.aurademo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.view.activity.Fullscreen

/**
 * Demo [Fullscreen] object.
 */
class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fullscreen(this).value()
    }
}