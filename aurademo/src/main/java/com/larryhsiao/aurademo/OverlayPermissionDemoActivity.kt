package com.larryhsiao.aurademo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.overlay.OverlayPermission
import com.larryhsiao.aura.overlay.OverlayPermissionImpl

class OverlayPermissionDemoActivity : AuraActivity() {
    private val overlayPermission =
            OverlayPermissionImpl(this, object : OverlayPermission.Callback {
                override fun onSuccess() {
                    Toast.makeText(this@OverlayPermissionDemoActivity, "Granting success", Toast.LENGTH_LONG).show()
                }

                override fun onFailure() {
                    Toast.makeText(this@OverlayPermissionDemoActivity, "Granting failure", Toast.LENGTH_LONG).show()
                }
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overlayPermission.request()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        overlayPermission.handleResult(requestCode)
    }
}