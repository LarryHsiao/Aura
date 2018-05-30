package com.silverhetch.aura.logs

import com.silverhetch.clotho.Source
import java.text.SimpleDateFormat

import java.util.Locale.getDefault

/**
 * Created by mikes on 4/24/2018.
 */
internal class LogString(private val message: String) : Source<String> {
    override fun fetch(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss :", getDefault())
        return dateFormat.format(System.currentTimeMillis()) + message + "\r\n"
    }
}
