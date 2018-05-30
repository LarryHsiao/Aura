package com.silverhetch.aura.view.measures;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by larryhsiao on 2017/10/23.
 */

public class ScreenSize {
    private final Point size;

    public ScreenSize(Context context) {
        this.size = new Point();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(size);
    }

    public int width() {
        return size.x;
    }

    public int height() {
        return size.y;
    }
}
