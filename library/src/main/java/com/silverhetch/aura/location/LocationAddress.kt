package com.silverhetch.aura.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import com.larryhsiao.clotho.Source
import java.io.IOException
import java.lang.IllegalArgumentException
import java.util.*

/**
 * Source to build address from Location.
 */
class LocationAddress(
    private val context: Context,
    private val location: Location
) : Source<Address> {
    override fun value(): Address {
        return try {
            Geocoder(context)
                .getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                ).takeIf { it.size > 0 }?.get(0) ?: Address(Locale.getDefault())
        } catch (ioException: IOException) { // network failure
            Address(Locale.getDefault())
        } catch (argException: IllegalArgumentException) {
            // Wrong latitude or longitude value.
            Address(Locale.getDefault())
        }
    }
}