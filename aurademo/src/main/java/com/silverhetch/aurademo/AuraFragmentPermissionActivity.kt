package com.silverhetch.aurademo

import android.Manifest
import android.os.Bundle
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.AuraFragment

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