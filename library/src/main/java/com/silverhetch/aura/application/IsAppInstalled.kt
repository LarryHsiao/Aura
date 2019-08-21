package com.silverhetch.aura.application

import android.content.Context
import android.content.pm.PackageManager
import com.silverhetch.clotho.Source

/**
 * Source to determine the application is installed or not.
 */
class IsAppInstalled(
    private val context: Context,
    private val packageName: String) : Source<Boolean> {
    override fun value(): Boolean {
        return try {
            context.packageManager.getApplicationInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}