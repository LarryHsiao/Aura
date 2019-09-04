package com.silverhetch.aura.bundle

import android.os.Bundle
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [com.silverhetch.aura.bundle.BundleString]
 */
@RunWith(RobolectricTestRunner::class)
class BundleStringTest {

    /**
     * Input/Output check
     */
    @Test
    fun simple() {
        assertEquals(
            "Bundle[KEY1=VALUE1, KEY2=VALUE2]",
            BundleString(
                Bundle().apply {
                    putString("KEY1", "VALUE1")
                    putString("KEY2", "VALUE2")
                }
            ).value()
        )
    }
}