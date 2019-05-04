package com.silverhetch.aura.view.measures

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class DPTest {
    /**
     * Density is 2.0 in xhdpi
     */
    @Test
    @Config(qualifiers = "xhdpi")
    fun xhdpi() {
        Assert.assertEquals(
                20f,
                DP(RuntimeEnvironment.application, 10f).px()
        )
    }

    /**
     * original value
     */
    @Test
    @Config(qualifiers = "xhdpi")
    fun originalValue() {
        Assert.assertEquals(
                10f,
                DP(RuntimeEnvironment.application, 10f).dp()
        )
    }
}