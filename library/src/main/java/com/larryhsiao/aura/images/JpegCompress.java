package com.larryhsiao.aura.images;

import android.graphics.BitmapFactory;
import com.larryhsiao.aura.images.exif.CopyExif;
import com.larryhsiao.clotho.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.graphics.Bitmap.CompressFormat.JPEG;
import static android.graphics.BitmapFactory.decodeFile;

/**
 * Action to compress given jpeg and preserve exif data.
 */
public class JpegCompress implements Action {
    private final File ori;
    private final File dist;
    private final int quality;
    private final int maximumSize;

    public JpegCompress(File ori, File dist) {
        this(ori, dist, 80, 1920);
    }

    public JpegCompress(File ori, File dist, int quality, int maximumSize) {
        this.ori = ori;
        this.dist = dist;
        this.quality = quality;
        this.maximumSize = maximumSize;
    }

    @Override
    public void fire() {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            decodeFile(ori.getAbsolutePath(), options);
            final int oriWidth = options.outWidth;
            final int oriHeight = options.outHeight;
            float sampleSize = 1;
            if (oriWidth < oriHeight && oriHeight > maximumSize) {
                sampleSize = (float) oriHeight / (float) maximumSize;
            } else if (oriWidth > oriHeight && oriWidth > maximumSize) {
                sampleSize = (float) oriWidth / (float) maximumSize;
            }
            options.inSampleSize = Math.round(sampleSize);
            final OutputStream distStream = new FileOutputStream(dist);
            decodeFile(
                ori.getAbsolutePath(),
                options
            ).compress(
                JPEG,
                quality,
                distStream
            );
            distStream.close();
            new CopyExif(ori, dist).fire();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
