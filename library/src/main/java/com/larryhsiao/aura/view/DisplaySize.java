package com.larryhsiao.aura.view;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import com.larryhsiao.clotho.Source;

import java.util.AbstractMap;
import java.util.Map;

/**
 * Source to fetch display size of activity, return default if fetching failed.
 * <p>
 * Entry of Size: X, Y
 */
public class DisplaySize implements Source<Map.Entry<Integer, Integer>> {
    private final Activity activity;
    private final Map.Entry<Integer, Integer> defaultSize;

    public DisplaySize(
        Activity activity,
        Map.Entry<Integer, Integer> defaultSize) {
        this.activity = activity;
        this.defaultSize = defaultSize;
    }

    @Override
    public Map.Entry<Integer, Integer> value() {
        try {
            final Point metrics = new Point();
            final Display display =
                activity.getWindowManager().getDefaultDisplay();
            display.getSize(metrics);
            return new AbstractMap.SimpleImmutableEntry<>(metrics.x, metrics.y);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultSize;
        }
    }
}
