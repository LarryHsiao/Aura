package com.silverhetch.aurademo.resultpipe

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.silverhetch.aura.AuraFragment
import com.silverhetch.aura.view.fragment.callback.FragmentResult

/**
 * Demo Fragment for sending result to Activity with [AuraFragment.sendResult]
 */
class FragmentResultDemoFragment : AuraFragment() {
    companion object {
        private const val ARG_CODE = "ARG_CODE"

        fun newInstance(code: Int): Fragment {
            return FragmentResultDemoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_CODE, code)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sendResult(
            arguments?.getInt(ARG_CODE, -1) ?: -1,
            Activity.RESULT_OK,
            Intent()
        )
    }
}