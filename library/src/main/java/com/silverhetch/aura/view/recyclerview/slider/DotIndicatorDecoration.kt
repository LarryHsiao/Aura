/*
 * Original version chahine/pageindicator.
 * MIT License:
Copyright (c) 2018 Chahine Mouhamad

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.silverhetch.aura.view.recyclerview.slider

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Color.*
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.silverhetch.aura.view.measures.DP
import kotlin.math.max
import kotlin.math.min

/**
 * Indicator for IG like dot indicator for [RecyclerView] with [RecyclerView.ItemDecoration].*
 */
class DotIndicatorDecoration constructor(
    private val recycler: RecyclerView
) : RecyclerView.ItemDecoration(), DotDimensions.TargetScrollListener {
    companion object {
        private const val DP_6 = 6.toByte()
        private const val DP_5 = 5.toByte()
        private const val DP_4 = 4.toByte()
        private const val DP_3 = 3.toByte()
        private const val DP_2 = 2.toByte()
        private const val DP_1 = 1.toByte()
        private const val DISPLAY_CONT = 10
        private const val ANIMATION_DURATION = 200
    }

    private val paddingBottom: Int
    private lateinit var dotSizes: IntArray
    private lateinit var dotAnimators: Array<ValueAnimator>
    private val defaultPaint = Paint().apply {
        color = GRAY
        isAntiAlias = true
    }
    private val selectedPaint = Paint().apply {
        isAntiAlias = true
        color = BLUE
    }
    private val dotSize: Int
    private val dotSizeMap: Map<Byte, Int>
    private val dotBound: Int
    private val dotSpacing: Int
    private val animDuration: Long
    private var dotDimensions: DotDimensions? = null
    private var scrollAmount: Int = 0
    private var scrollAnimator: ValueAnimator? = null
    private var initialPadding: Int = 0
    private lateinit var scrollListener: RecyclerView.OnScrollListener

    private var count: Int = 0
        set(value) {
            dotDimensions = DotDimensions(
                value,
                dotSize,
                dotSpacing,
                dotBound,
                dotSizeMap,
                this)
            dotSizes = IntArray(value)
            dotDimensions?.let { it.dots.forEachIndexed { index, dot -> dotSizes[index] = it.dotSizeFor(dot) } }
            dotAnimators = Array(value) { ValueAnimator() }
            initialPadding = when (value) {
                in 0..4 -> (value * (dotSize + dotSpacing)) / 2
                else -> (5 * (dotSize + dotSpacing)) / 2
            }
            field = value
            recycler.invalidate()
        }

    init {
        paddingBottom = DP(recycler.context, 16f).px().toInt()
        dotSizeMap = mapOf(
            DP_6 to DP(recycler.context, 6f).px().toInt(),
            DP_5 to DP(recycler.context, 5f).px().toInt(),
            DP_4 to DP(recycler.context, 4.5f).px().toInt(),
            DP_3 to DP(recycler.context, 3f).px().toInt(),
            DP_2 to DP(recycler.context, 2.5f).px().toInt(),
            DP_1 to DP(recycler.context, .5f).px().toInt()
        )
        dotSize = dotSizeMap.values.max() ?: 0
        dotSpacing = DP(recycler.context, 3f).px().toInt()
        dotBound = DP(recycler.context, 40f).px().toInt()

        animDuration = ANIMATION_DURATION.toLong()
        attachTo(recycler)
    }

    override fun onDrawOver(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.onDrawOver(canvas, parent, state)
        var paddingStart = (canvas.width / 2f) - initialPadding
        val (start, end) = getDrawingRange()
        paddingStart += (dotSize + dotSpacing) * start
        (start until end).forEach {
            canvas.drawCircle(
                paddingStart + dotSize / 2f - scrollAmount,
                canvas.height - dotSize.toFloat() - paddingBottom,
                dotSizes[it] / 2f,
                when (dotDimensions?.dots?.get(it)) {
                    DP_6 -> selectedPaint
                    else -> defaultPaint
                })
            paddingStart += dotSize + dotSpacing
        }
    }

    override fun scrollToTarget(target: Int) {
        scrollAnimator?.cancel()
        scrollAnimator = ValueAnimator.ofInt(scrollAmount, target).apply {
            duration = animDuration
            interpolator = interpolator
            addUpdateListener { animation ->
                scrollAmount = animation.animatedValue as Int
                recycler.invalidate()
            }
            start()
        }
    }

    private infix fun attachTo(recyclerView: RecyclerView) {
        if (::scrollListener.isInitialized) {
            recyclerView.removeOnScrollListener(scrollListener)
        }
        count = recyclerView.adapter?.itemCount ?: 0
        scrollListener = ScrollListener(this)
        recyclerView.addOnScrollListener(scrollListener)
        scrollToTarget(0)
    }

    fun swipePrevious() {
        dotDimensions?.selectPrevious()
        animateDots()
    }

    fun swipeNext() {
        dotDimensions?.selectNext()
        animateDots()
    }

    private fun animateDots() {
        dotDimensions?.let {
            val (start, end) = getDrawingRange()
            (start until end).forEach { index ->
                dotAnimators[index].cancel()
                dotAnimators[index] = ValueAnimator.ofInt(dotSizes[index], it.dotSizeFor(it.dots[index]))
                    .apply {
                        duration = animDuration
                        interpolator = interpolator
                        addUpdateListener { animation ->
                            dotSizes[index] = animation.animatedValue as Int
                            recycler.invalidate()
                        }
                    }
                dotAnimators[index].start()
            }
        }
    }

    private fun getDrawingRange(): Pair<Int, Int> {
        return Pair(
            max(
                0,
                (dotDimensions?.selectedIndex ?: 0) - DISPLAY_CONT
            ),
            min(
                dotDimensions?.dots?.size ?: 0,
                (dotDimensions?.selectedIndex ?: 0) + DISPLAY_CONT
            )
        )
    }

}