package com.silverhetch.aurademo

import android.os.Bundle
import android.widget.Toast
import com.silverhetch.aura.fingerprint.BioAuth
import com.silverhetch.aura.AuraActivity

/**
 * Demo Activity for [BioAuth]
 */
class BioAuthDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BioAuth(
            this
        ) { errCode, err ->
            Toast.makeText(
                this,
                "Failed: ${err}",
                Toast.LENGTH_LONG
            ).show()
        }.fire()
    }
}