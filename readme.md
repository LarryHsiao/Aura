Aura
----

Utilities for Android for projects.

Utilities for pure Java/Kotlin [Clotho][2]

[![We recommend IntelliJ IDEA](http://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Build Status](https://travis-ci.org/LarryHsiao/Aura.svg?branch=master)](https://travis-ci.org/LarryHsiao/Aura)
[![Sonarqube](https://silverhetch.com:9100/api/project_badges/measure?project=Aura&metric=alert_status)](https://silverhetch.com:9100/dashboard?id=Aura)
[![codebeat badge](https://codebeat.co/badges/0d232226-097e-49a8-84b6-5a5516b33a32)](https://codebeat.co/projects/github-com-larryhsiao-aura-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/61ed243513854fcd88c356e7d27dc311)](https://www.codacy.com/app/LarryHsiao/Aura?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=LarryHsiao/Aura&amp;utm_campaign=Badge_Grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)


Currently this library has no implementation plans, just develop what I need in my projects to reuse them.

#### Features

 - [Permission granting](#permission-granting)

## Quick start

###### Gradle

```groovy
dependencies {
    implementation 'com.silverhetch:aura:1.0.5114345'
}
```

###### Demo

Application Module [aurademo][1] have some implementation with Aura for development purpose, which you can fire it up to see the result with this library.

### Permission granting <a name="permission-granting"/>

There are two ways of permission granting utility in Aura.

###### AuraFragment/AuraActivity

```kotlin
class PermissionRequestAuraActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionsByObj(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE))
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








[1]: https://github.com/LarryHsiao/Aura/tree/master/aurademo
[2]: https://github.com/LarryHsiao/Clotho
[3]: https://github.com/LarryHsiao/Aura/blob/master/library/src/main/java/com/silverhetch/aura/permission/PermissionsImpl.kt
