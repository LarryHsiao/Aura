package com.silverhetch.aura.view.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by larryhsiao on 2017/10/24.
 */

public class ResizedImage {
    private final int maximumWidth;

    public ResizedImage(int maximumWidth) {
        this.maximumWidth = maximumWidth;
    }

    public Bitmap image(String filePath) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(filePath);
            final float originalWidth = bitmap.getWidth();
            final float originalHeight = bitmap.getHeight();

            final float destWidth = maximumWidth;
            final float destHeight = destWidth * (originalHeight / originalWidth);
            return Bitmap.createScaledBitmap(bitmap, (int) destWidth, (int) destHeight, false);
        } finally {
            if (bitmap != null) {
                bitmap.recycle();
            }
        }

    }
}
