package com.silverhetch.util.view.measures;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by larryhsiao on 2017/10/23.
 */

public class ScaleIndependentPixels {
    private final int sp;
    private final int px;

    public ScaleIndependentPixels(Context context, int sp) {
        this.sp = sp;
        this.px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());

    }

    public int sp() {
        return sp;
    }

    public int px() {
        return px;
    }
}
