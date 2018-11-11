package com.silverhetch.aura.bluetooth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Phantom class of Discovery
 */
class PhantomDiscovery : Discovery {
    override fun stop() {
        // ignore
    }

    override fun search() {
        // ignore
    }

    override fun start() {
        // ignore
    }

    override fun running(): Boolean {
        return false
    }

    override fun remoteDevice(): LiveData<MutableMap<String, CRemoteDevice>> {
        return MutableLiveData<MutableMap<String, CRemoteDevice>>()
    }
}