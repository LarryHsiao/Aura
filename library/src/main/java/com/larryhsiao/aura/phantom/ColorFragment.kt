package com.larryhsiao.aura.phantom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.larryhsiao.aura.view.RandomColors

/**
 * Created by Larry.H on 4/2/2018.
 */
class ColorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View(activity).apply {
            setBackgroundColor(RandomColors().value())
        }
    }
}
