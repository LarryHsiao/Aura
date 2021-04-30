package com.larryhsiao.aurademo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.view.activity.Fullscreen

/**
 * Demo [Fullscreen] object.
 */
class FullScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fullscreen(this).value()
    }
}