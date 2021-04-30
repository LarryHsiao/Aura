package com.larryhsiao.aurademo

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import android.widget.ImageView
import com.larryhsiao.aura.view.dialog.FullScreenDialogFragment
import com.larryhsiao.aura.view.measures.DP

/**
 * A sample bottom dialog implemented with [FullScreenDialogFragment].
 */
class SampleFullScreenDialogFragment : FullScreenDialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FrameLayout(inflater.context).apply {
            addView(
                    FrameLayout(inflater.context).apply {
                        layoutParams = LayoutParams(MATCH_PARENT, DP(requireContext(), 200f).px().toInt(), BOTTOM)
                        setBackgroundColor(Color.WHITE)
                        ImageView(inflater.context).apply {
                            layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT, TOP or END)
                            setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
                            setOnClickListener {
                                dismiss()
                            }
                        }.apply { addView(this) }

                    }
            )
        }
    }
}