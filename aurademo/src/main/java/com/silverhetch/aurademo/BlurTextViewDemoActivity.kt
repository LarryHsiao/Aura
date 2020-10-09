package com.silverhetch.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.view.textview.BlurTextAction

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