package com.silverhetch.aura.uri

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for [com.silverhetch.aura.uri.UriMimeType]
 */
@RunWith(RobolectricTestRunner::class)
class UriMimeTypeTest {
    /**
     * Simple mime type checking
     */
    @Test
    fun simple() {
        UriMimeType(
            ApplicationProvider.getApplicationContext(),
            "abc://abc.bcd/abc.png"
        ).value()

        assertTrue(true)
    }

    /**
     * Empty string if input not a uri
     */
    @Test
    fun invalidedUri() {
        assertEquals(
            "",
            UriMimeType(
                ApplicationProvider.getApplicationContext(),
                "abc.bcd/abc.png"
            ).value()
        )
    }
}