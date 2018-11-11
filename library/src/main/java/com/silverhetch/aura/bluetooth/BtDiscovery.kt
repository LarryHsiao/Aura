package com.silverhetch.aura.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.silverhetch.clotho.connection.socket.TextBaseConnImpl
import com.silverhetch.clotho.utility.RevertedUUID
import java.util.*
import kotlin.collections.ArrayList

/**
 * Remote Device discovery by Bluetooth.
 *
 * Please note that permissions for Bluetooth are required.
 */
@SuppressLint("MissingPermission")
class BtDiscovery(private val context: Context, private val adapter: BluetoothAdapter, private val uuid: UUID) : Discovery {
    private val remoteDevice = MutableLiveData<MutableMap<String, CRemoteDevice>>().apply {
        value = HashMap()
    }

    override fun start() {
        context.registerReceiver(receiver, IntentFilter().apply {
            addAction(ACTION_FOUND)
            addAction(ACTION_DISCOVERY_STARTED)
            addAction(ACTION_DISCOVERY_FINISHED)
            addAction(ACTION_UUID)
        })
    }

    override fun search() {
        if (adapter.isDiscovering.not()) {
            // #startDiscovery() when previous not complete will cause #startDiscovery() blocks forever
            adapter.startDiscovery()
        }
    }

    override fun stop() {
        context.unregisterReceiver(receiver)
        adapter.cancelDiscovery()
    }

    override fun running(): Boolean {
        return adapter.isDiscovering
    }

    override fun remoteDevice(): LiveData<MutableMap<String, CRemoteDevice>> {
        return remoteDevice
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                ACTION_FOUND -> {
                    val device: BluetoothDevice = intent.getParcelableExtra(EXTRA_DEVICE)
                    device.fetchUuidsWithSdp()
                }
                ACTION_UUID -> {
                    val device: BluetoothDevice = intent.getParcelableExtra(EXTRA_DEVICE)
                    if (device.uuids != null) {
                        device.uuids.forEach {
                            Log.i("Bluetooth","This is uuid: "+ it.uuid)
                            // Android may returns reverted uuid which is custom UUID.
                            if (it.uuid.toString() == uuid.toString() || it.uuid.toString() == RevertedUUID(uuid).fetch().toString()) {
                                val socket = device.createInsecureRfcommSocketToServiceRecord(uuid)
                                socket.connect()
                                remoteDevice.value!![device.address] = BtRemoteDevice(device, TextBaseConnImpl(
                                        socket.inputStream.bufferedReader(),
                                        socket.outputStream.bufferedWriter()) {
                                    "Client response"
                                }.apply {
                                    launch()
                                })
                                remoteDevice.value = remoteDevice.value
                            }
                        }
                    } else {
                        device.fetchUuidsWithSdp()
                    }
                }
            }
        }
    }
}