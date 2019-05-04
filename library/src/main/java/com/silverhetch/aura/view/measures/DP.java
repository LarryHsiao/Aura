package com.silverhetch.aura.view.measures;

import android.content.Context;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.applyDimension;

/**
 * Created by mikes on 10/23/2017.
 */
public class DP {
    private final float dp;
    private final float px;

    public DP(Context context, float dp) {
        this.dp = dp;
        this.px = applyDimension(
                COMPLEX_UNIT_DIP,
                dp,
                context.getResources().getDisplayMetrics()
        );

    }

    public float dp() {
        return dp;
    }

    public float px() {
        return px;
    }
}
