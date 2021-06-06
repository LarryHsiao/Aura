package com.larryhsiao.aurademo

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.sercurity.BioAuth
import com.larryhsiao.aura.sercurity.BioAuth.Callback

/**
 * Demo Activity for [BioAuth]
 */
class BioAuthDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BioAuth(
            this,
            "Title here",
            object : Callback {
                override fun onSuccess() {
                    Toast.makeText(this@BioAuthDemoActivity, "Success", LENGTH_LONG).show()
                }

                override fun onFailed(code: Int, err: String?) {
                    Toast.makeText(this@BioAuthDemoActivity, "Failed: ${err}", LENGTH_LONG).show()
                }
            }
        ).fire()
    }
}