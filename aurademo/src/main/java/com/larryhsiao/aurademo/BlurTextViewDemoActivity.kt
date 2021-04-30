package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.view.textview.BlurTextAction
import com.larryhsiao.aurademo.R

class BlurTextViewDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            text = getString(R.string.debug_long_string)
        }
        setContentView(textView)
        BlurTextAction(textView).fire()
    }
}