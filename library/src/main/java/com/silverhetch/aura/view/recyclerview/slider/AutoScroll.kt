package com.silverhetch.aura.view.recyclerview.slider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.larryhsiao.clotho.Action

/**
 * Action to make a RecyclerView auto scroll.
 */
class AutoScroll(
    private val view: RecyclerView,
    private val delay: Long = 3000,
    private val repeat: Boolean = true
) : Action {
    override fun fire() {
        view.apply {
            postDelayed(object : Runnable {
                override fun run() {
                    val newPos = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition() + 1
                    if (newPos < (adapter?.itemCount ?: 0)) {
                        view.smoothScrollToPosition(newPos)
                        postDelayed(this, delay)
                    } else {
                        if (repeat) {
                            view.scrollToPosition(0)
                            postDelayed(this, delay)
                        }
                    }
                }
            }, delay)
        }
    }
}