package com.larryhsiao.aura.view.measures;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by larryhsiao on 2017/10/23.
 */

public class SP {
    private final float value;
    private final float px;

    public SP(Context context, float value) {
        this(context.getResources().getDisplayMetrics(), value);
    }

    public SP(DisplayMetrics displayMetrics, float value) {
        this.value = value;
        this.px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                value,
                displayMetrics
        );

    }

    public float sp() {
        return value;
    }

    public float px() {
        return px;
    }
}
