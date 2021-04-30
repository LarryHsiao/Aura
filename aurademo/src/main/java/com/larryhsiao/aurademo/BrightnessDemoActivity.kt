package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.view.activity.brightness.InAppBrightness
import com.larryhsiao.aura.view.measures.DP

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
                    marginStart = DP(context, 16f).px().toInt()
                    marginEnd = DP(context, 16f).px().toInt()
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