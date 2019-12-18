# Spannable String 
There are some features for the Android TextView, which we can make parts of it clickable, change the color or others.

Aura provides some Sources to build that String object without messing around with SpannableStringBuilder. 

#### Example
Here is a snippet for creating a CharSequence that is clickable and colored.  
```kotlin
ColoredStr(
    ClickableStr(
        ConstSource("Clickable text"),
        Runnable {
            // Do whatever the text is clicked
        }
    ), Color.parseColor("#03A9F4")).value()
```





