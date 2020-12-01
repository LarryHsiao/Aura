package com.silverhetch.aura.view.recyclerview.slider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.larryhsiao.clotho.Action

/**
 * Action to make a RecyclerView do a slider show.
 */
class Slider(private val view: RecyclerView) : Action {
    override fun fire() {
        view.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            addItemDecoration(DotIndicatorDecoration(this))
            PagerSnapHelper().attachToRecyclerView(this)
        }
    }
}