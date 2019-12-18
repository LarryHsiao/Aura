@todo #new-1 Component Internal Storage
@todo #new-2 Component Transfer file with progress

Aura
----

Some utility objects/classes for Android projects


[![PDD status](http://www.0pdd.com/svg?name=LarryHsiao/Aura)](http://www.0pdd.com/p?name=LarryHsiao/Aura)

[![We recommend IntelliJ IDEA](http://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![](https://larryhsiao.com:9082/app/rest/builds/buildType:Aura_Development/statusIcon.svg)](https://github.com/LarryHsiao/Aura)
[![codebeat badge](https://codebeat.co/badges/0d232226-097e-49a8-84b6-5a5516b33a32)](https://codebeat.co/projects/github-com-larryhsiao-aura-master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/61ed243513854fcd88c356e7d27dc311)](https://www.codacy.com/app/LarryHsiao/Aura?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=LarryHsiao/Aura&amp;utm_campaign=Badge_Grade)
[![Hits-of-Code](https://hitsofcode.com/github/LarryHsiao/Aura)](https://hitsofcode.com/view/github/LarryHsiao/Aura)

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)


Currently this library has no implementation plans, just develop what I need in my projects to reuse them.

#### Components
##### System
 - [Permission granting](docs/permission_granting.md)
 - [ChooserIntent](docs/chooser_intent.md) : System promoted action chooser.
##### UI Component
 - [AuraProgressBar](docs/aura_progress_bar.md)
 - [StatusBarColor](docs/StatusBarColor.md)
 - [SpannableStr](docs/spannable_string.md)

## Quick start

###### Gradle

```groovy
dependencies {
    implementation 'com.silverhetch:aura:{latest_version}'
}
```

###### Demo

Application Module [aurademo][1] have some implementation with Aura for development purpose, which you can fire it up to see the result with this library.


[1]: https://github.com/LarryHsiao/Aura/tree/master/aurnademo
[2]: https://github.com/LarryHsiao/Clotho
[3]: https://github.com/LarryHsiao/Aura/blob/master/library/src/main/java/com/silverhetch/aura/permission/PermissionsImpl.kt
