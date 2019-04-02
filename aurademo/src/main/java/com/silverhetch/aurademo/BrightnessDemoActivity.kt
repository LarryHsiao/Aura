package com.silverhetch.aurademo

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.view.activity.brightness.InAppBrightness
import com.silverhetch.aura.view.measures.DensityIndependentPixels

/**
 * Demo for []
 */
class BrightnessDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val brightness = InAppBrightness(this)
        setContentView(LinearLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            orientation = VERTICAL
            addView(SeekBar(context).apply {
                layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT).apply {
                    marginStart = DensityIndependentPixels(context, 16).px()
                    marginEnd = DensityIndependentPixels(context, 16).px()
                }
                max = 100
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (progress>0) {
                            brightness.set(progress / 100f)
                        }else{
                            brightness.set(-1f)
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }
                })
            })
        })
    }
}