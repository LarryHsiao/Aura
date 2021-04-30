package com.larryhsiao.aurademo

import android.Manifest
import android.os.Bundle
import com.larryhsiao.aura.AuraActivity

class PermissionRequestAuraActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsByObj(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
}