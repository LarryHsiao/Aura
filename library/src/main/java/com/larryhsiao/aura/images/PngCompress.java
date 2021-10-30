package com.larryhsiao.aura.images;

import android.graphics.BitmapFactory;
import com.larryhsiao.clotho.Action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static android.graphics.Bitmap.CompressFormat.PNG;
import static android.graphics.BitmapFactory.decodeFile;

public class PngCompress implements Action {
    private final File ori;
    private final File dist;
    private final int quality;
    private final int maximumSize;

    public PngCompress(File ori, File dist) {
        this(ori, dist, 80, 1920);
    }

    public PngCompress(File ori, File dist, int quality, int maximumSize) {
        this.ori = ori;
        this.dist = dist;
        this.quality = quality;
        this.maximumSize = maximumSize;
    }

    @Override
    public void fire() {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = new SamplingBySize(ori, maximumSize).value();
            final OutputStream distStream = new FileOutputStream(dist);
            decodeFile(ori.getAbsolutePath(), options).compress(
                PNG,
                quality,
                distStream
            );
            distStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
