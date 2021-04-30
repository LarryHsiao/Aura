package com.larryhsiao.aura.view.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.larryhsiao.clotho.Source;

import static android.graphics.Bitmap.createScaledBitmap;

/**
 * Created by larryhsiao on 2017/10/24.
 */

public class ResizedImage implements Source<Bitmap>{
    private final Source<Bitmap> original;
    private final int maximumWidth;

    public ResizedImage(Source<Bitmap> original, int maximumWidth) {
        this.original = original;
        this.maximumWidth = maximumWidth;
    }

    @Override
    public Bitmap value() {
        Bitmap bitmap = null;
        try {
            bitmap = original.value();
            final float originalWidth = bitmap.getWidth();
            final float originalHeight = bitmap.getHeight();

            final float destWidth = maximumWidth;
            final float destHeight = destWidth * (originalHeight / originalWidth);
            return createScaledBitmap(bitmap, (int) destWidth, (int) destHeight, false);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }

    }
}
