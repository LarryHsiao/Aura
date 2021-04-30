package com.larryhsiao.aura.view.measures

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.larryhsiao.clotho.Source

/**
 * Utility to grant the screen size from context.
 */
class ScreenSize(context: Context) : Source<Point> {
    private val size: Point by lazy {
        Point().apply {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            display.getSize(this)
            this
        }
    }

    override fun value(): Point {
        return size
    }

    fun width(): Int {
        return size.x
    }

    fun height(): Int {
        return size.y
    }
}
