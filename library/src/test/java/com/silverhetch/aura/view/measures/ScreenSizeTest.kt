package com.silverhetch.aura.view.measures

import android.content.Context
import android.view.WindowManager
import androidx.core.util.Preconditions
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class ScreenSizeTest {
    @Test
    fun simple() {
        val windowManager = RuntimeEnvironment.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val shadowDisplay = Shadows.shadowOf(Preconditions.checkNotNull(windowManager).getDefaultDisplay())
        shadowDisplay.setWidth(100)
        shadowDisplay.setHeight(100)

        ScreenSize(RuntimeEnvironment.application).run {
            Assert.assertEquals(100, width())
            Assert.assertEquals(100, height())
        }
    }
}