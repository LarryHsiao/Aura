package com.larryhsiao.aurademo.fabcontrol

import android.os.Bundle
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aurademo.R
import kotlinx.android.synthetic.main.activity_fab_demo.*

class FabControlDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab_demo)
        setupFabControl(fabDemo_fab)

        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.fabDemo_fragmentContainer, FabFragment())
            commit()
        }
    }
}