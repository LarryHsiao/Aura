package com.larryhsiao.aurademo.fabcontrol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.larryhsiao.aura.AuraFragment
import com.larryhsiao.aura.view.fab.FabBehavior
import com.larryhsiao.aurademo.R

/**
 * Demo fragment for demo Fab.
 */
class FabFragment : AuraFragment(), FabBehavior {
    override fun icon(): Int {
        return android.R.drawable.stat_sys_warning
    }

    override fun onClick() {
        makeText(context, "This is a Fab click event attached in ${toString()}", LENGTH_LONG).show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = FrameLayout(inflater.context)
        val button = Button(context)
        button.text = "Next Fragment"
        button.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        button.setOnClickListener {
            with(requireActivity().supportFragmentManager.beginTransaction()) {
                replace(R.id.fabDemo_fragmentContainer, FabFragment())
                addToBackStack(null)
                commit()
            }
        }
        root.addView(button)
        return root
    }

    override fun onPause() {
        super.onPause()
        detachFab()
    }

    override fun onResume() {
        super.onResume()
        attachFab(this)
    }
}