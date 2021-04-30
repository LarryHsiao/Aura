package com.larryhsiao.aura.view.fragment.callback

import android.content.Intent
import androidx.fragment.app.Fragment
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for [FragmentResult]
 */
@RunWith(RobolectricTestRunner::class)
class FragmentResultTest {

    /**
     * Exception when no destination to deliver.
     */
    @Test
    fun exception() {
        try {
            FragmentResult(Fragment()).sendResult(0, 0, Intent())
            fail()
        } catch (e: UnsupportedOperationException) {
            assertNotNull(e.message)
        }
    }
}