package com.larryhsiao.aura.view.recyclerview.slider

import androidx.recyclerview.widget.RecyclerView
import kotlin.math.floor

/**
 * For listening scroll status of [RecyclerView].
 */
internal class ScrollListener(private val indicatorDecoration: DotIndicatorDecoration) : RecyclerView.OnScrollListener() {
    private var middle = 0
    private var scrollX = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        scrollX += dx
        recyclerView.getChildAt(0)?.width?.let {
            val midPos = floor(((scrollX + it / 2f) / it).toDouble()).toInt()
            if (this.middle != midPos) {
                when {
                    this.middle < midPos -> indicatorDecoration.swipeNext()
                    else -> indicatorDecoration.swipePrevious()
                }
            }
            this.middle = midPos
        }
    }
}
