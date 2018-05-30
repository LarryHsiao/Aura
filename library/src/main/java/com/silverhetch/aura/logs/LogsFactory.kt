package com.silverhetch.aura.logs

import android.content.Context
import android.content.SharedPreferences

import android.content.Context.MODE_PRIVATE

/**
 * Created by mikes on 4/24/2018.
 */
class LogsFactory(private val context: Context) {

    fun instance(): Logs {
        val preferences = context.getSharedPreferences("debug-log", MODE_PRIVATE)
        return LogsImpl(preferences.getBoolean("enabled", false), preferences)
    }
}
