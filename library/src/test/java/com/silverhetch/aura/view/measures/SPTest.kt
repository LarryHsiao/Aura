package com.silverhetch.aura.view.measures

import android.util.DisplayMetrics
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Test for [SP]
 */
@RunWith(RobolectricTestRunner::class)
class SPTest {
    /**
     * Check result in xhdpi
     */
    @Test
    @Config(qualifiers = "xhdpi")
    fun value_xhdpi() {
        Assert.assertEquals(
            25f,
            SP(DisplayMetrics().apply {
                scaledDensity = 2.5f
            }, 10f).px()
        )

    }

    /**
     * Check result in mdpi.
     */
    @Test
    fun originalValue() {
        Assert.assertEquals(
            10f,
            SP(RuntimeEnvironment.application, 10f).sp()
        )
    }
}