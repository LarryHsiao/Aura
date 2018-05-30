package com.silverhetch.aura.view.keyboard;

import android.widget.EditText;

/**
 * Created by larryhsiao on 2017/6/14.
 */

public interface SoftKeyboardControl {
    void hideSoftInputMethod();

    void showSoftInputMethod(EditText editText);
}
