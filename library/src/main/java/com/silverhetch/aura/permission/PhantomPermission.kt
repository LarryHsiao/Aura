package com.silverhetch.aura.permission

class PhantomPermission : Permissions {
    override fun isPermissionGranted(): Boolean {
        return true
    }

    override fun requestPermissions() {
    }

    override fun handleResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    }
}