package com.silverhetch.aura.view.measures;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by larryhsiao on 2017/10/23.
 */

public class SP {
    private final float sp;
    private final float px;

    public SP(Context context, float sp) {
        this(context.getResources().getDisplayMetrics(), sp);
    }

    public SP(DisplayMetrics displayMetrics, float sp) {
        this.sp = sp;
        this.px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                sp,
                displayMetrics
        );

    }

    public float sp() {
        return sp;
    }

    public float px() {
        return px;
    }
}
