package com.silverhetch.aura.view.span;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.annotation.NonNull;
import com.silverhetch.clotho.Source;

/**
 * Source to build clickable string for textView.
 */
public class ClickableStr implements Source<CharSequence> {
    private final Source<CharSequence> value;
    private final Runnable clicked;

    public ClickableStr(Source<CharSequence> value, Runnable clicked) {
        this.value = value;
        this.clicked = clicked;
    }

    @Override
    public CharSequence value() {
        final SpannableStringBuilder builder = new SpannableStringBuilder(value.value());
        builder.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                clicked.run();
            }
        }, 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
