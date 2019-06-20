package com.silverhetch.aura.fingerprint

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Build.VERSION_CODES.*
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.test.core.app.ApplicationProvider
import com.silverhetch.aura.storage.SPCeres
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow

@RunWith(RobolectricTestRunner::class)
@Config(shadows = [ShadowFingerprintManagerCompat::class])
class FingerprintImplTest {
    @Config(sdk = [M])
    @Test
    fun default() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = true
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).isEnabled()
        )
    }

    @Config(sdk = [M])
    @Test
    fun enable() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = true
        }
        assertEquals(
            true,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).apply { enable(true) }.isEnabled()
        )
    }


    @Config(sdk = [LOLLIPOP])
    @Test
    fun enable_unsupportedSdk() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = true
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).apply { enable(true) }.isEnabled()
        )
    }

    @Config(sdk = [M])
    @Test
    fun enable_unsupportedHardware() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = false
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).apply { enable(true) }.isEnabled()
        )
    }

    @Config(sdk = [M])
    @Test
    fun enable_noFingerprint() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = false
            it.hasHardware = true
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).apply { enable(true) }.isEnabled()
        )
    }

    @Config(sdk = [LOLLIPOP])
    @Test
    fun isSupported_unsupportedPlatform() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = true
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).isSupported()
        )
    }


    @Config(sdk = [O])
    @Test
    fun isSupported() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = true
        }
        assertEquals(
            true,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).isSupported()
        )
    }

    @Config(sdk = [O])
    @Test
    fun isSupported_noHardWare() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val manager = FingerprintManagerCompat.from(context)
        Shadow.extract<ShadowFingerprintManagerCompat>(manager).also {
            it.hasEnroll = true
            it.hasHardware = false
        }
        assertEquals(
            false,
            FingerprintImpl(
                manager,
                SPCeres(context.getSharedPreferences(
                    "temp",
                    Context.MODE_PRIVATE
                ))
            ).isSupported()
        )
    }
}