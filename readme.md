Aura
----

Utilities for Android projects.
Wraps APIs into Object-oriented style of code, or we can say, Aura`s style. Which I know a little.

Utilities for regular Java/Kotlin [Clotho][2]

[![We recommend IntelliJ IDEA](http://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Build Status](https://travis-ci.org/LarryHsiao/Aura.svg?branch=master)](https://travis-ci.org/LarryHsiao/Aura)
[![Sonarqube](https://silverhetch.com:9100/api/project_badges/measure?project=Aura&metric=alert_status)](https://silverhetch.com:9100/dashboard?id=Aura)
[![codebeat badge](https://codebeat.co/badges/0d232226-097e-49a8-84b6-5a5516b33a32)](https://codebeat.co/projects/github-com-larryhsiao-aura-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/61ed243513854fcd88c356e7d27dc311)](https://www.codacy.com/app/LarryHsiao/Aura?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=LarryHsiao/Aura&amp;utm_campaign=Badge_Grade)
[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)


Currently this library has no implementation plans, just develop what I need in my projects to reuse them.

#### Components
##### System
 - [Permission granting](docs/permission_granting.md)
##### UI
 - [AuraProgressBar](docs/aura_progress_bar.md)

## Quick start

###### Gradle

```groovy
dependencies {
    implementation 'com.silverhetch:aura:1.0.5114345'
}
```

###### Demo

Application Module [aurademo][1] have some implementation with Aura for development purpose, which you can fire it up to see the result with this library.


[1]: https://github.com/LarryHsiao/Aura/tree/master/aurnademo
[2]: https://github.com/LarryHsiao/Clotho
[3]: https://github.com/LarryHsiao/Aura/blob/master/library/src/main/java/com/silverhetch/aura/permission/PermissionsImpl.kt
