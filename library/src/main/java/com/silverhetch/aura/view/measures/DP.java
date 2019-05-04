package com.silverhetch.aura.view.measures;

import android.content.Context;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.applyDimension;

/**
 * Created by mikes on 10/23/2017.
 */
public class DP {
    private final float value;
    private final float px;

    public DP(Context context, float value) {
        this.value = value;
        this.px = applyDimension(
                COMPLEX_UNIT_DIP,
                value,
                context.getResources().getDisplayMetrics()
        );

    }

    public float dp() {
        return value;
    }

    public float px() {
        return px;
    }
}
