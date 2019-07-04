### Permission granting <a name="permission-granting"/>

There are two ways of permission granting utility in Aura.

###### AuraFragment/AuraActivity
```kotlin
class PermissionRequestAuraActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsByObj(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }
    
    override fun onPermissionGranted() {
        // Success, If you want to handle the UI promotion yourself
        // you can overrid showPermissionRationale(), onPermissionPermanentlyDecline()
    }
}
```

###### Permissions classes

```kotlin
class SampleActivity : AppCompatActivity(), PermissionCallback {
    private var permissionObj: Permissions = PhantomPermission()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionObj = PermissionsImpl(this, this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
        permissionObj.requestPermissions() /** trigger the permission granting */
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        /** must delegate the system permission result into Permission object */
        permissionObj.handleResult(requestCode, permissions, grantResults)
    }

    override fun onPermissionGranted() {
        /**
         * after granted
         */
    }

    override fun onPermissionPermanentlyDecline(permission: Array<String>) {
        /**
         * User don`t allow the permission permanently. (Checked the "Don`t ask again".)
         * Android system will not promote the permission granting dialog even we invoke [Permissions.requestPermissions].
         */
    }

    override fun showPermissionRationale(permission: Array<String>) {
        /**
         * User decline the permission granting Dialog provided by System.
         * We can show the purpose of the permission here.
         */
    }
}
```
