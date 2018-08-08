package com.silverhetch.aurademo

import android.Manifest
import android.os.Bundle
import com.silverhetch.aura.AuraActivity

class PermissionRequestAuraActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsByObj(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
}