package com.silverhetch.aura.view.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by larryhsiao on 2017/10/16.
 *
 * @todo #10 BitmapUtility: replaced with objects.
 */
public class BitmapUtility {
    public static Bitmap obtainBitmapFromVectorDrawable(Context context, int drawableId) {
        try {
            Drawable drawable = context.getResources().getDrawable(drawableId);
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            // Handle the error
            return null;
        }
    }
}
