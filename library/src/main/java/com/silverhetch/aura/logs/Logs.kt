package com.silverhetch.aura.logs

import com.silverhetch.clotho.log.Log

/**
 * Created by mikes on 4/24/2018.
 */
interface Logs {
    val isEnable: Boolean
    fun toggle()
    fun instance(): Log
}
