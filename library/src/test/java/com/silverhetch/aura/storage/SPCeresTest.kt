package com.silverhetch.aura.storage

import android.content.Context
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

/**
 * Ceres tests
 */
@RunWith(RobolectricTestRunner::class)
class SPCeresTest {
    /**
     * Simple function test
     */
    @Test
    fun simple() {
        SPCeres(RuntimeEnvironment.application.getSharedPreferences("test", Context.MODE_PRIVATE)).let {
            it.store("Key", "value")
            Assert.assertEquals("value", it.get("Key"))
        }
    }
}