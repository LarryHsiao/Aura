package com.silverhetch.aura.view.keyboard;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by mikes on 9/24/2017.
 */

public class SoftKeyboardControlImpl implements SoftKeyboardControl {
    private Activity activity;

    public SoftKeyboardControlImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void hideSoftInputMethod() {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        final InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void showSoftInputMethod(EditText editText) {
        final InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!editText.hasFocus()) {
            editText.requestFocus();
        }
        inputManager.showSoftInput(editText, 0);
    }
}
