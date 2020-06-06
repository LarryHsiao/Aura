package com.silverhetch.aura.view.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.silverhetch.aura.R;
import com.silverhetch.aura.view.measures.DP;
import org.jetbrains.annotations.NotNull;

import static android.content.res.Resources.getSystem;
import static android.graphics.Paint.Cap.ROUND;
import static android.graphics.Paint.Style.FILL;
import static androidx.recyclerview.widget.RecyclerView.*;
import static java.lang.Math.max;

/**
 * Decoration to show dot indicator.
 */
public class DotsIndicatorDecoration extends ItemDecoration {
    private final int bottomPadding;
    private final int itemPadding;
    private final int radius;
    private final Paint inactivePaint = new Paint();
    private final Paint activePaint = new Paint();

    public DotsIndicatorDecoration(Context context) {
        this(
            context,
            ((int) new DP(context, 6).px()),
            ((int) new DP(context, 6).px()),
            ((int) new DP(context, 48).px()),
            0,
            0
        );
    }

    public DotsIndicatorDecoration(
        Context context,
        int radius,
        int itemPadding,
        int bottomPadding,
        @ColorInt int colorInactive,
        @ColorInt int colorActive
    ) {
        if (colorInactive<=0){
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            colorInactive = typedValue.data;
        }
        if (colorActive <= 0 ){
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.colorAccent, typedValue, true);
            colorActive = typedValue.data;
        }
        float strokeWidth = getSystem().getDisplayMetrics().density * 1;
        this.radius = radius;
        inactivePaint.setStrokeCap(ROUND);
        inactivePaint.setStrokeWidth(strokeWidth);
        inactivePaint.setStyle(FILL);
        inactivePaint.setAntiAlias(true);
        inactivePaint.setColor(colorInactive);
        activePaint.setStrokeCap(ROUND);
        activePaint.setStrokeWidth(strokeWidth);
        activePaint.setStyle(FILL);
        activePaint.setAntiAlias(true);
        activePaint.setColor(colorActive);
        this.itemPadding = itemPadding;
        this.bottomPadding = bottomPadding;
    }

    @Override
    public void onDrawOver(
        @NotNull Canvas c,
        @NotNull RecyclerView parent,
        @NotNull State state
    ) {
        super.onDrawOver(c, parent, state);
        final Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        int itemCount = adapter.getItemCount();
        // center horizontally, calculate width and subtract half from center
        float totalLength = this.radius * 2 * itemCount;
        float paddingBetweenItems = max(0, itemCount - 1) * itemPadding;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2f;
        float indicatorPosY = parent.getHeight() - bottomPadding / 2f;
        drawInactiveDots(c, indicatorStartX, indicatorPosY, itemCount);
        final int activePosition;
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            activePosition = ((GridLayoutManager) parent.getLayoutManager())
                .findFirstVisibleItemPosition();
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            activePosition = ((LinearLayoutManager) parent.getLayoutManager())
                .findFirstVisibleItemPosition();
        } else {
            // not supported layout manager
            return;
        }
        if (activePosition == NO_POSITION) {
            return;
        }
        // find offset of active page if the user is scrolling
        final View activeChild =
            parent.getLayoutManager().findViewByPosition(activePosition);
        if (activeChild == null) {
            return;
        }
        drawActiveDot(c, indicatorStartX, indicatorPosY, activePosition);
    }

    private void drawInactiveDots(
        Canvas c,
        float indicatorStartX,
        float indicatorPosY,
        int itemCount
    ) {
        // width of item indicator including padding
        final float itemWidth = this.radius * 2 + itemPadding;
        float start = indicatorStartX + radius;
        for (int i = 0; i < itemCount; i++) {
            c.drawCircle(start, indicatorPosY, radius, inactivePaint);
            start += itemWidth;
        }
    }

    private void drawActiveDot(
        Canvas c,
        float indicatorStartX,
        float indicatorPosY,
        int highlightPosition
    ) {
        // width of item indicator including padding
        final float itemWidth = this.radius * 2 + itemPadding;
        float highlightStart =
            indicatorStartX + radius + itemWidth * highlightPosition;
        c.drawCircle(highlightStart, indicatorPosY, radius, activePaint);
    }
}