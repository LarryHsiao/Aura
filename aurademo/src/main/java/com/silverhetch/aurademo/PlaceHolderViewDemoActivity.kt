package com.silverhetch.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.view.PlaceHolderView

class PlaceHolderViewDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val placeHolder = PlaceHolderView(this).apply {
            this.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        }
        setContentView(placeHolder)

        placeHolder.loadUp(R.layout.activity_place_holder)
    }
}