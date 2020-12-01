package com.silverhetch.aura.view.edittext

import android.widget.EditText
import com.larryhsiao.clotho.Action

/**
 * Action that make given [EditText] editable.
 */
class Editable(private val editText: EditText) : Action {
    override fun fire() {
        editText.isFocusable = true
        editText.isCursorVisible = true
        editText.isClickable = true
        editText.isFocusableInTouchMode = true
    }
}