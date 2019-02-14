package com.silverhetch.aura.storage

import android.content.SharedPreferences
import com.silverhetch.clotho.storage.Ceres

/**
 * [Ceres] implemented with shared preference
 */
class SPCeres(private val preference: SharedPreferences) : Ceres {
    override fun all(): Map<String, String> {
        val result = HashMap<String, String>()
        preference.all.forEach {
            if (it.value is String) {
                result[it.key] = it.value as String
            }
        }
        return result
    }

    override fun get(key: String): String {
        return preference.getString(key, "")!!
    }

    override fun store(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }
}