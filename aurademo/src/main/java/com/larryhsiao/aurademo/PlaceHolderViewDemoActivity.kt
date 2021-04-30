package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.view.PlaceHolderView
import com.larryhsiao.aurademo.R

class PlaceHolderViewDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(PlaceHolderView(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            loadUp(R.layout.activity_place_holder) {
                for (i in 0 until (it as ViewGroup).childCount) {
                    (it.getChildAt(i) as TextView).text = "loaded item $i"
                }
            }
        })
    }
}