package com.silverhetch.aura.logs

import com.larryhsiao.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
class EmptyLog : Log {
    override fun info(message: String) {
        // just leave it empty
    }

    override fun debug(s: String) {
        // just leave it empty
    }

    override fun warning(s: String) {
        // just leave it empty
    }

    override fun error(s: String) {
        // just leave it empty
    }
}
