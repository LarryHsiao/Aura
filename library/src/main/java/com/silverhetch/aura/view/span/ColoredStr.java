package com.silverhetch.aura.view.span;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import com.larryhsiao.clotho.Source;

/**
 * Source to build colored {@link CharSequence}
 */
public class ColoredStr implements Source<CharSequence> {
    private final Source<CharSequence> value;
    private final int color;

    public ColoredStr(Source<CharSequence> value, int color) {
        this.value = value;
        this.color = color;
    }

    @Override
    public CharSequence value() {
        final SpannableStringBuilder builder = new SpannableStringBuilder(value.value());
        builder.setSpan(new ForegroundColorSpan(color),
            0,
            builder.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
