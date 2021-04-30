package com.larryhsiao.aura.intent

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Test for [ChooserIntent]
 */
@RunWith(RobolectricTestRunner::class)
class ChooserIntentTest {

    @Test
    fun extraArraySize() {
        assertEquals(
            1,
            ChooserIntent(
                ApplicationProvider.getApplicationContext(),
                "title",
                Intent(Intent.ACTION_VIEW),
                Intent("ACTION")
            ).value().getParcelableArrayExtra(Intent.EXTRA_INITIAL_INTENTS)?.size
        )
    }

    @Test
    fun extraSize() {
        assertEquals(
            0,
            ChooserIntent(
                ApplicationProvider.getApplicationContext(),
                "title",
                Intent(Intent.ACTION_VIEW)
            ).value().getParcelableArrayExtra(Intent.EXTRA_INITIAL_INTENTS)?.size
        )
    }
}