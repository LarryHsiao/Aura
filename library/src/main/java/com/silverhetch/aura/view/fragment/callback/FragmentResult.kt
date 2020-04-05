package com.silverhetch.aura.view.fragment.callback

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * Deliver fragment result to Parent Fragment, Target Fragment or Activity.
 *
 * The order to find the delivering destination:
 * Target Fragment -> Parent Fragment -> Activity
 * If target/parent Fragment have implement [ResultGate],
 * the result will go the method [ResultGate.onFragmentResult].
 */
class FragmentResult(private val fragment: Fragment) : ResultPipe {
    override fun sendResult(requestCode: Int, resultCode: Int, data: Intent) {
        fragment.targetFragment?.also { target ->
            val code = if (requestCode == 0) fragment.targetRequestCode else requestCode
            sendFragment(target, code, resultCode, data)
        } ?: fragment.parentFragment?.also { parent ->
            sendFragment(parent, requestCode, resultCode, data)
        } ?: fragment.activity?.also { activity ->
            if (activity is ResultGate) {
                activity.onFragmentResult(requestCode, resultCode, data)
            } else {
                throwNotFound()
            }
        } ?: throwNotFound()
    }

    private fun sendFragment(
        frag: Fragment,
        requestCode: Int,
        resultCode: Int,
        data: Intent
    ) {
        if (frag is ResultGate) {
            frag.onFragmentResult(requestCode, resultCode, data)
        } else {
            frag.onActivityResult(requestCode, resultCode, data)
        }
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