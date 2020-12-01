package com.silverhetch.aura.view

import android.graphics.Color
import com.larryhsiao.clotho.Source

/**
 * Source to generate RGB color int.
 */
class RandomColors : Source<Int> {
    companion object {
        private val COLORS = intArrayOf(
            Color.BLACK,
            Color.YELLOW,
            Color.GRAY,
            Color.BLUE,
            Color.RED,
            Color.DKGRAY,
            Color.CYAN
        )
    }

    override fun value(): Int {
        return COLORS[Any().hashCode() % COLORS.size]
    }
}