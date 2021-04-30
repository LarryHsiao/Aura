package com.larryhsiao.aurademo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.aura.view.alert.Alert
import com.larryhsiao.aura.view.fragment.callback.ResultGate

class AlertDemoActivity :AppCompatActivity(), ResultGate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Alert.newInstance(1000,"This is sample")
            .show(supportFragmentManager, null)
    }

    override fun onFragmentResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Callback", Toast.LENGTH_LONG).show()
        }
    }
}