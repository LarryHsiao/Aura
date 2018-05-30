package com.silverhetch.aura.logs

import com.silverhetch.clotho.log.Log

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter

import android.os.Environment.getExternalStorageDirectory

/**
 * Created by mikes on 4/24/2018.
 */
internal class FileLog : Log {
    override fun debug(s: String) {
        save(s)
    }

    override fun warning(s: String) {
        save(s)
    }

    override fun error(s: String) {
        save(s)
    }

    override fun info(message: String) {
        save(message)
    }

    private fun save(message: String) {
        try {
            val writer = PrintWriter(
                    BufferedWriter(
                            FileWriter(
                                    File(getExternalStorageDirectory(), "General.log"), true)))
            writer.println(LogString(message).value())
            writer.close()
        } catch (ex: IOException) {
            android.util.Log.e("FileLog", "STORING LOG Error cause $ex")
        }

    }
}
