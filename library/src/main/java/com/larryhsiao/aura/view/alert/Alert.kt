package com.larryhsiao.aura.view.alert

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.larryhsiao.aura.R
import com.larryhsiao.aura.view.dialog.FullScreenDialogFragment
import com.larryhsiao.aura.view.fragment.callback.FragmentResult

/**
 * Dialog in Aura style.
 */
class Alert : FullScreenDialogFragment() {
    companion object {
        private const val KEY_MSG = "KEY_MSG"
        private const val KEY_CODE = "KEY_CODE"
        fun newInstance(code: Int, msg: String): DialogFragment {
            return Alert().apply {
                arguments = Bundle().apply {
                    putString(KEY_MSG, msg)
                    putInt(KEY_CODE, code)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.alert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.alert_title)
            .text = arguments?.getString(KEY_MSG, "") ?: ""
        view.findViewById<View>(R.id.alert_confirm_button).setOnClickListener {
            FragmentResult(this).sendResult(
                arguments?.getInt(KEY_CODE, 0) ?: 0,
                RESULT_OK,
                Intent()
            )
            dismiss()
        }
    }
}