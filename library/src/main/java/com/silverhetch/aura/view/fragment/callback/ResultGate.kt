package com.silverhetch.aura.view.fragment.callback

import android.content.Intent
import android.app.Activity
import androidx.fragment.app.Fragment
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.AuraFragment

/**
 * A convenient interface for deliver callback from [Fragment].
 *
 * Implement this interface at Activity, parent Fragment or target Fragment in
 * order to catch the Fragment result send by [ResultPipe] any compatible implementation.
 *
 * Note: [AuraActivity] and [AuraFragment] have default implementation for convenient use.
 *
 * @see AuraActivity
 * @see AuraFragment
 */
interface ResultGate {
    /**
     * The Android Official-like method to catch the result from fragments.
     *
     * @see Activity.onActivityResult
     * @see Fragment.onActivityResult
     */
    fun onFragmentResult(requestCode: Int, resultCode: Int, data: Intent)
}