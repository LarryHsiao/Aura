package com.silverhetch.aurademo.resultpipe

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.view.ViewCompat
import com.silverhetch.aura.AuraActivity

/**
 * Demo activity for ResultPipe
 */
class FragmentResultDemoActivity : AuraActivity(){
    companion object{
        private const val REQUEST_CODE =1000
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootId = ViewCompat.generateViewId()
        setContentView(FrameLayout(this).also{
            it.id = rootId
            it.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT,MATCH_PARENT)
        })

        supportFragmentManager.beginTransaction()
            .replace(rootId, FragmentResultDemoFragment.newInstance(REQUEST_CODE))
            .commit()
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onFragmentResult(requestCode, resultCode, data)

        Toast.makeText(this, "onFragmentResult() invoked code: $REQUEST_CODE ", Toast.LENGTH_SHORT).show()
    }
}
