package com.silverhetch.aura.logs

import com.silverhetch.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
internal class EmptyLog : Log {
    override fun info(message: String) {
        // just leave it empty
    }

    override fun debug(s: String) {

    }

    override fun warning(s: String) {

    }

    override fun error(s: String) {

    }
}
