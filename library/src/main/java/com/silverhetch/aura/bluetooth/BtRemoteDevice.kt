package com.silverhetch.aura.bluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.silverhetch.clotho.connection.socket.TextBaseConn

/**
 * Discover Bluetooth devices which exchange data with text.
 *
 * Please note that permissions for Bluetooth are required.
 */
@SuppressLint("MissingPermission")
class BtRemoteDevice(private val bluetoothDevice: BluetoothDevice, private val textBaseConn: TextBaseConn) : CRemoteDevice {
    override fun name(): String {
        return bluetoothDevice.name ?: ""
    }

    override fun toString(): String {
        return name() + " " + bluetoothDevice.address
    }


    override fun send(message: String) {
        textBaseConn.send(message)
    }

    override fun address(): String {
        return bluetoothDevice.address
    }
}