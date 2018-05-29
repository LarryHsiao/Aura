package com.silverhetch.util.phantom

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Larry.H on 4/2/2018.
 */
class ColorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = View(activity)
        val index = Any().hashCode() % COLORS.size
        view.setBackgroundColor(COLORS[index])
        return view
    }

    companion object {
        private val COLORS = intArrayOf(Color.BLACK, Color.YELLOW, Color.GRAY, Color.BLUE, Color.RED, Color.DKGRAY, Color.CYAN)
    }
}
