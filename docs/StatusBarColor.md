Status bar color
---

This utility changes the status bar`s color and set the icon color theme to dark or light by luminance(threshold 0.5).


```kotlin
StatusBarColor(window, Color.parseColor("#ffffff")).value() // light
StatusBarColor(window, Color.parseColor("#000000")).value() // dark
```
