package com.larryhsiao.aura.images;

import android.graphics.BitmapFactory;
import com.larryhsiao.clotho.Source;

import java.io.File;

import static android.graphics.BitmapFactory.decodeFile;

public class SamplingBySize implements Source<Integer> {
    private final File ori;
    private final int maximumSize;

    public SamplingBySize(File ori, int maximumSize) {
        this.ori = ori;
        this.maximumSize = maximumSize;
    }

    @Override
    public Integer value() {
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
        return Math.round(sampleSize);
    }
}
