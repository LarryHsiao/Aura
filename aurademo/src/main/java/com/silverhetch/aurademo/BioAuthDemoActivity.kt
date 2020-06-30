package com.silverhetch.aurademo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.fingerprint.BioAuth

/**
 * Demo Activity for [BioAuth]
 */
class BioAuthDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BioAuth(
            this,
            success = {
                Toast.makeText(
                    this,
                    "Success",
                    LENGTH_LONG
                ).show()
            }
        ) { errCode, err ->
            Toast.makeText(
                this,
                "Failed: ${err}",
                LENGTH_LONG
            ).show()
        }.fire()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}