package com.silverhetch.aura.view.measures;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by mikes on 10/23/2017.
 */

public class DensityIndependentPixels {
    private final int dp;
    private final int px;

    public DensityIndependentPixels(Context context, int dp) {
        this.dp = dp;
        this.px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());

    }

    public int sp() {
        return dp;
    }

    public int px() {
        return px;
    }
}
