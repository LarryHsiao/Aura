package com.silverhetch.aura.view.span;

import android.text.SpannableStringBuilder;
import com.silverhetch.clotho.Source;

/**
 * Source to build a {@link CharSequence} to append two charSequence without losing
 * the spannable behavior like styling, clickable, etc.
 */
public class AppendedStr implements Source<CharSequence> {
    private final Source<CharSequence> origin;
    private final CharSequence string;

    public AppendedStr(Source<CharSequence> origin, CharSequence string) {
        this.origin = origin;
        this.string = string;
    }

    @Override
    public CharSequence value() {
        final SpannableStringBuilder builder = new SpannableStringBuilder(origin.value());
        builder.append(string);
        return builder;
    }
}
