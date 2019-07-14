package com.silverhetch.aura.view.edittext

import android.widget.EditText
import com.silverhetch.clotho.Action

/**
 * Action that make given [EditText] uneditable .
 */
class Uneditable(private val editText: EditText) : Action {
    override fun fire() {
        editText.isFocusable = false
        editText.isCursorVisible = false
        editText.isClickable = false
        editText.isFocusableInTouchMode = false
    }
}