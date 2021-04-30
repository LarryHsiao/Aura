package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.phantom.ColorFragment

/**
 * Demo Activity for [PageControl]
 */
class PageControlDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = View.generateViewId()
        setContentView(FrameLayout(this).also {
            it.id = id
            it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        })
        setupPageControl(id)
        rootPage(ColorFragment())
        nextPage(ColorFragment())
    }
}