package com.silverhetch.aura.logs

import android.content.SharedPreferences

import com.silverhetch.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
internal class LogsImpl(enabled: Boolean, private val preferences: SharedPreferences) : Logs {
    override var isEnable: Boolean = false
        private set

    init {
        this.isEnable = enabled
    }

    override fun toggle() {
        isEnable = !isEnable
        preferences.edit().putBoolean("enabled", isEnable).apply()
    }

    override fun instance(): Log {
        return if (isEnable)
            MultiLog(FileLog(), AndroidLog())
        else
            AndroidLog()
    }
}
