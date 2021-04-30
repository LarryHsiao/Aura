package com.larryhsiao.aura.view.calendar;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.larryhsiao.clotho.date.JdkWeekdays;
import com.larryhsiao.clotho.date.Weekday;
import com.larryhsiao.clotho.date.Weekdays;
import com.larryhsiao.aura.view.measures.DP;

import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * View for toggling weekdays.
 */
public class WeekDayToggle extends LinearLayout {
    private final Weekdays weekdays = new JdkWeekdays();

    public WeekDayToggle(Context context) {
        super(context);
        init();
    }

    public WeekDayToggle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeekDayToggle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(LOLLIPOP)
    public WeekDayToggle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
        for (Weekday day : weekdays.days()) {
            final TextView dayText = new TextView(getContext());
            final DP padding = new DP(getContext(), 8);
            final LayoutParams layoutParam = new LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            dayText.setLayoutParams(layoutParam);
            dayText.setPadding(
                ((int) padding.px()),
                ((int) padding.px()),
                ((int) padding.px()),
                ((int) padding.px())
            );
            dayText.setText(day.name());
            dayText.setTag(false);
            dayText.setAlpha(0.4f);
            dayText.setTextAppearance(getContext(), android.R.style.TextAppearance_Material_Title);
            dayText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean checked = ((boolean) v.getTag());
                    dayText.setTag(!checked);
                    if (checked) {
                        dayText.setAlpha(0.4f);
                    } else {
                        dayText.setAlpha(1f);
                    }
                }
            });
            addView(dayText);
        }
        if (isInEditMode()) {
            loadToggleState(0b1000101);
        }
    }

    /**
     * Set up teh toggle states.
     */
    public void loadToggleState(int state) {
        for (int i = 0; i < getChildCount(); i++) {
            final View dayText = getChildAt(i);
            if (((state >> (6 - i)) & 0b0000001) == 1) {
                dayText.setTag(true);
                dayText.setAlpha(1f);
            } else {
                dayText.setTag(false);
                dayText.setAlpha(0.4f);
            }
        }
    }

    /**
     * ex:
     * - Monday enables only: 0x1000000
     *
     * @return Toggle states. 1 true otherwise 0.
     */
    public int getToggleState() {
        int result = 0b0000000;
        for (int i = 0; i < getChildCount(); i++) {
            boolean enabled = ((boolean) getChildAt(i).getTag());
            if (enabled) {
                result = result | (1 << i);
            }
        }
        return result;
    }
}
