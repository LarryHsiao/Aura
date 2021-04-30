package com.larryhsiao.aura.logs

import com.larryhsiao.clotho.Source
import java.text.SimpleDateFormat

import java.util.Locale.getDefault

/**
 * Created by mikes on 4/24/2018.
 */
internal class LogString(private val message: String) : Source<String> {
    override fun value(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss :", getDefault())
        return dateFormat.format(System.currentTimeMillis()) + message + "\r\n"
    }
}
