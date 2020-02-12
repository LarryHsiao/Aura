package com.silverhetch.aura.view.fragment.callback

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * Deliver fragment result to Parent Fragment, Target Fragment or Activity.
 *
 * The order to find the deliver destination:
 * Target Fragment -> Parent Fragment -> Activity
 * If target/parent Fragment have implement [ResultGate],
 * the result will goes the method [ResultGate.onFragmentResult].
 */
class FragmentResult(private val fragment: Fragment) : ResultPipe {
    override fun sendResult(requestCode: Int, resultCode: Int, data: Intent) {
        fragment.targetFragment?.also { target ->
            val code = if (requestCode == 0) fragment.targetRequestCode else requestCode
            if (target is ResultGate) {
                target.onFragmentResult(code, resultCode, data)
            } else {
                target.onActivityResult(code, resultCode, data)
            }
        } ?: fragment.parentFragment?.also { parent ->
            parent.onActivityResult(requestCode, resultCode, data)
        } ?: fragment.activity?.also { activity ->
            if (activity is ResultGate) {
                activity.onFragmentResult(requestCode, resultCode, data)
            } else {
                throwNotFound()
            }
        } ?: throwNotFound()
    }

    private fun throwNotFound() {
        throw UnsupportedOperationException(
            """
                No available result to send to.
                Possible deliver route:
                - Target Fragment
                - Parent Fragment
                - attached Activity that implements ResultGate
                """.trimIndent())
    }
}