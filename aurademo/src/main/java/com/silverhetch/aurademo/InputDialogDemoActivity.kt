package com.silverhetch.aurademo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.silverhetch.aura.view.dialog.InputDialog

/**
 * Demo activity for input dialog.
 */
class InputDialogDemoActivity : AppCompatActivity(), InputDialog.Callback {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InputDialog.newInstance("Title", 123).show(supportFragmentManager, null)
    }

    override fun onInputDialogResult(requestCode: Int, result: Int, data: Intent) {
        Toast.makeText(this, """
            |code: $requestCode,
            |result: ${if (result == Activity.RESULT_OK) "OK" else "NO"},
            |field: ${data.getStringExtra("INPUT_FIELD")}
        """.trimMargin(), Toast.LENGTH_LONG).show()
        finish()
    }
}
