package com.larryhsiao.aurademo

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aurademo.R
import kotlinx.android.synthetic.main.activity_aura_progressbar.*

/**
 * Demo for the [AuraProgressBar] with animation repeatably.
 */
class AuraProgressBarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aura_progressbar)
        Handler().run {
            postDelayed(
                    object : Runnable {
                        override fun run() {
                            auraProgressBar.updateProgressAnimated((auraProgressBar.progress() + 0.1f) % 1f)
                            if (isDestroyed.not()) {
                                postDelayed(this, 1000)
                            }
                        }
                    },
                    1000
            )
        }
    }
}