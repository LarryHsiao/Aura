package com.larryhsiao.aura.logs

import com.larryhsiao.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
class AndroidLog : Log {
    override fun info(message: String) {
        android.util.Log.i("General", message)
    }

    override fun debug(s: String) {
        android.util.Log.d("General", s)
    }

    override fun warning(s: String) {
        android.util.Log.w("General", s)
    }

    override fun error(s: String) {
        android.util.Log.e("General", s)
    }
}
