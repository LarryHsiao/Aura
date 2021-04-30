package com.larryhsiao.aurademo

import android.Manifest
import android.os.Bundle
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.AuraFragment

class AuraFragmentPermissionActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(supportFragmentManager.beginTransaction()) {
            add(SampleFragment(), null)
            commit()
        }
    }

    class SampleFragment : AuraFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestPermissionsByObj(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        }
    }
}