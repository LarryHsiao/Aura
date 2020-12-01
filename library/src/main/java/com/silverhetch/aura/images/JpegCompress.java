package com.silverhetch.aura.images;

import com.silverhetch.aura.images.exif.CopyExif;
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

    public JpegCompress(File ori, File dist) {
        this(ori, dist, 80);
    }

    public JpegCompress(File ori, File dist, int quality) {
        this.ori = ori;
        this.dist = dist;
        this.quality = quality;
    }

    @Override
    public void fire() {
        try {
            OutputStream distStream = new FileOutputStream(dist);
            decodeFile(ori.getAbsolutePath()).compress(
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
