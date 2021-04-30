package com.larryhsiao.aurademo;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.larryhsiao.aura.AuraActivity;
import com.larryhsiao.aura.view.span.AppendedStr;
import com.larryhsiao.aura.view.span.ClickableStr;
import com.larryhsiao.aura.view.span.ColoredStr;
import com.larryhsiao.clotho.source.ConstSource;

/**
 * Activity to demo clickable textView Action
 */
public class ClickableSpanActivity extends AuraActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final TextView textView = new TextView(this);
        textView.setText(new AppendedStr(
            new ColoredStr(
                new ClickableStr(
                    new ConstSource<CharSequence>("this is sample <a href=\"your_link\">Link Title</a>"),
                    new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("THis is clicked S");
                        }
                    }),
                Color.CYAN
            ), "abc").value());
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        setContentView(textView);
    }
}
