package com.silverhetch.aura.location

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.*
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY

/**
 * Convenient Service that monitor the location.
 */
class LocationService : Service() {
    companion object {
        private const val ARG_INTERVAL = "ARG_INTERVAL"
        private const val DEFAULT_INTERVAL = 30000L
    }

    private var interval = DEFAULT_INTERVAL
    private val binder = Binder()
    private val locationLive = MutableLiveData<Location>()
    private val thread = HandlerThread("LocationThread")
    private lateinit var threadHandler: Handler
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)
            locationResult?.let {
                locationLive.postValue(it.lastLocation)
            }
        }
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {
        interval = intent?.getLongExtra(
            ARG_INTERVAL,
            DEFAULT_INTERVAL
        ) ?: DEFAULT_INTERVAL
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        thread.start()
        threadHandler = Handler(thread.looper)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.requestLocationUpdates(
            LocationRequest.create()
                .setInterval(interval)
                .setPriority(PRIORITY_HIGH_ACCURACY),
            locationCallback,
            thread.looper
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        thread.quit()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    /**
     * Binder of this Service
     */
    inner class Binder : android.os.Binder() {
        /**
         * Live data of monitoring location
         */
        fun location(): LiveData<Location> {
            return locationLive
        }
    }
}