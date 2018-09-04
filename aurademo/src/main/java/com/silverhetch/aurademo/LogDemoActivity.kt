package com.silverhetch.aurademo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_log_demo.*
import java.util.*
import kotlin.collections.ArrayList

class LogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)


        with(Data()) {
            addObserver(logDemo_logView)
            logDemo_newLine.setOnClickListener {
                append("New line " + UUID.randomUUID().toString().substring(0, 5))
            }
        }
    }

    class Data : Observable() {
        private val list = ArrayList<String>()

        fun append(newLine: String) {
            list.add(newLine)
            setChanged()
            notifyObservers(newLine)
        }
    }
}