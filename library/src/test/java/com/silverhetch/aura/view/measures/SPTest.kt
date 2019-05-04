package com.silverhetch.aura.view.measures

import android.util.DisplayMetrics
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class SPTest {
    @Test
    @Config(qualifiers = "xhdpi")
    fun simple() {
        Assert.assertEquals(
                25f,
                SP(DisplayMetrics().apply {
                    scaledDensity = 2.5f
                }, 10f).px()
        )

    }

    @Test
    fun originalValue() {
        Assert.assertEquals(
                10f,
                SP(RuntimeEnvironment.application, 10f).sp()
        )
    }
}