package com.silverhetch.robolectric

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Edge test for Robolectric framework.
 * (Learning) Test the framework is actually work with the density.
 */
@RunWith(RobolectricTestRunner::class)
class DensityTest {
    @Test
    @Config(qualifiers = "xhdpi")
    fun xhdpi() {
        Assert.assertEquals(
            2f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }

    @Test
    @Config(qualifiers = "hdpi")
    fun hdpi() {
        Assert.assertEquals(
            1.5f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }

    @Test
    @Config(qualifiers = "mdpi")
    fun mdpi() {
        Assert.assertEquals(
            1f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }

    @Test
    @Config(qualifiers = "ldpi")
    fun ldpi() {
        Assert.assertEquals(
            0.75f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }

    @Test
    @Config(qualifiers = "xxhdpi")
    fun xxhdpi() {
        Assert.assertEquals(
            3f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }

    @Test
    @Config(qualifiers = "xxxhdpi")
    fun xxxhdpi() {
        Assert.assertEquals(
            4f,
            RuntimeEnvironment.application.resources.displayMetrics.density
        )
    }
}