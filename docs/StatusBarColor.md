Status bar color
---

This utility changes the status bar`s color and set the icon color theme to dark or light by luminance(default threshold 0.5).


```kotlin
StatusBarColor(window, Color.parseColor("#ffffff"),/*Optional*/ 0.5 ).value() // light
StatusBarColor(window, Color.parseColor("#000000")).value() // dark
```
