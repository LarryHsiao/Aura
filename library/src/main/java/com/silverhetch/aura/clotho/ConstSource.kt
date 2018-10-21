package com.silverhetch.aura.clotho

import com.silverhetch.clotho.Source

/**
 * Source object keeps the given reference.
 */
class ConstSource<T>(private val reference: T) : Source<T> {
    override fun fetch(): T {
        return reference
    }
}