package com.silverhetch.aura.application

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Tests for [com.silverhetch.aura.application.IsAppInstalled]
 */
@RunWith(RobolectricTestRunner::class)
class IsAppInstalledTest {

    /**
     * Test the result should be false in local unit-test
     */
    @Test
    fun defaultNotFound() {
        assertFalse(
            IsAppInstalled(
                ApplicationProvider.getApplicationContext(),
                "com.facebook.katana"
            ).value()
        )
    }
}