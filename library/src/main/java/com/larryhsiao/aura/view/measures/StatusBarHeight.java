package com.larryhsiao.aura.view.measures;

import android.app.Activity;
import android.graphics.Rect;
import android.view.Window;

/**
 * Created by mikes on 10/29/2017.
 */

public class StatusBarHeight {
    private final Activity activity;

    public StatusBarHeight(Activity activity) {
        this.activity = activity;
    }

    public int value() {
        Rect rectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return  rectangle.top;
    }
}
