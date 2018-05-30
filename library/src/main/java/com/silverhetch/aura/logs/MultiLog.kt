package com.silverhetch.aura.logs

import com.silverhetch.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
internal class MultiLog(private vararg val logs: Log) : Log {

    override fun debug(message: String) {
        try {
            for (log in logs) {
                log.debug(message)
            }
        } catch (e: Exception) {
            android.util.Log.e("MultiLog", "Exception cause " + e +
                    "\nOriginal message: " + message)
        }

    }

    override fun warning(message: String) {
        try {
            for (log in logs) {
                log.warning(message)
            }
        } catch (e: Exception) {
            android.util.Log.e("MultiLog", "Exception cause " + e +
                    "\nOriginal message: " + message)
        }

    }

    override fun error(message: String) {
        try {
            for (log in logs) {
                log.error(message)
            }
        } catch (e: Exception) {
            android.util.Log.e("MultiLog", "Exception cause " + e +
                    "\nOriginal message: " + message)
        }

    }

    override fun info(message: String) {
        try {
            for (log in logs) {
                log.info(message)
            }
        } catch (e: Exception) {
            android.util.Log.e("MultiLog", "Exception cause " + e +
                    "\nOriginal message: " + message)
        }

    }
}
