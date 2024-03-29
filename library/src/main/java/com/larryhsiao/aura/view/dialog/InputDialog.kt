package com.larryhsiao.aura.view.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_TEXT
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.larryhsiao.aura.view.measures.DP

/**
 * Simple dialog for input one field implemented with [DialogFragment].
 * The result will will be pushed back with Intent that have one String extra "INPUT_FIELD".
 *
 * Note: There is no option to custom layout, implement new [DialogFragment] if custom view required.
 */
class InputDialog : DialogFragment() {
    /**
     * Callback interface for case that this dialog is launched by Activity.
     */
    interface Callback {
        fun onInputDialogResult(requestCode: Int, result: Int, data: Intent)
    }

    companion object {
        private const val ARG_TITLE = "ARG_TITLE"

        /**
         * Argument for passing request code in case launching this dialog from Activity.
         */
        private const val ARG_REQUEST_CODE = "ARG_REQUEST_CODE"

        fun newInstance(title: String, requestCode: Int = 0): InputDialog {
            return InputDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, title)
                    putInt(ARG_REQUEST_CODE, requestCode)
                }
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inputField = EditText(requireContext())
        inputField.inputType = TYPE_CLASS_TEXT
        inputField.setSingleLine()
        inputField.setLines(1)
        inputField.maxLines = 1
        inputField.setImeActionLabel("GO", EditorInfo.IME_ACTION_GO)
        return AlertDialog.Builder(requireContext())
            .setTitle(arguments?.getString(ARG_TITLE, "") ?: "")
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                result(Activity.RESULT_OK, inputField.text.toString())
            }.setNegativeButton(android.R.string.no) { dialog, which ->
                result(Activity.RESULT_CANCELED, "")
            }.create().apply {
                val padding = DP(context, 16f).px().toInt()
                setView(inputField, padding, 0, padding, 0)
            }
    }

    private fun result(result: Int, field: String) {
        when {
            targetFragment != null -> targetFragment?.onActivityResult(
                targetRequestCode,
                result,
                Intent().apply { putExtra("INPUT_FIELD", field) }
            )
            activity is Callback -> (activity as Callback).onInputDialogResult(
                arguments?.getInt(ARG_REQUEST_CODE) ?: 0,
                result,
                Intent().apply { putExtra("INPUT_FIELD", field) }
            )
            else -> throw RuntimeException(
                "Set the target fragment or implements Callback interface on Activity to deliver the callbac"
            )
        }
    }
}