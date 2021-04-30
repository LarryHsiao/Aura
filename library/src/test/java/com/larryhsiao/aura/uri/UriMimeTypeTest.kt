package com.larryhsiao.aura.uri

import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for [com.larryhsiao.aura.uri.UriMimeType]
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
     * Unknown content type if input not a uri
     */
    @Test
    fun invalidedUri() {
        assertEquals(
            "content/unknown",
            UriMimeType(
                ApplicationProvider.getApplicationContext(),
                "abc.bcd/abc.png"
            ).value()
        )
    }

    @Test
    fun file() {
        assertEquals(
            "image/png",
            UriMimeType(
                ApplicationProvider.getApplicationContext(),
                "file:/abc.bcd/abc.png"
            ).value()
        )
    }
}