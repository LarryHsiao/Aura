package com.larryhsiao.aura.view.fragment.callback

import android.content.Intent
import android.app.Activity
import androidx.fragment.app.Fragment
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.AuraFragment

/**
 * The result sender for Fragments to deliver execute
 * results to Target Fragment, parent Fragment or Activity which have implement
 * [ResultGate].
 *
 * Note: [AuraActivity] and [AuraFragment] have default implementation for convenient use.
 *
 * @see AuraActivity
 * @see AuraFragment
 */
interface ResultPipe {
    /**
     * Send the given result to [ResultGate]
     *
     * @see Activity.onActivityResult
     * @see Fragment.onActivityResult
     */
    fun sendResult(requestCode: Int, resultCode: Int, data: Intent)
}