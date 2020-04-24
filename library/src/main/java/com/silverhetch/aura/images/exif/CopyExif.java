package com.silverhetch.aura.images.exif;

import androidx.exifinterface.media.ExifInterface;
import com.silverhetch.clotho.Action;
import com.silverhetch.clotho.Source;

import java.io.File;
import java.util.List;

/**
 * Action to copy exif to another file.
 * Useful for a file compressing which the exif data is missing after compressed.
 */
public class CopyExif implements Action {
    private final Source<List<String>> tags;
    private final File ori;
    private final File dist;

    public CopyExif(File ori, File dist) {
        this(new ExifTags(), ori, dist);
    }

    public CopyExif(
        Source<List<String>> tags,
        File ori,
        File dist
    ) {
        this.tags = tags;
        this.ori = ori;
        this.dist = dist;
    }

    @Override
    public void fire() {
        try {
            ExifInterface oriExif = new ExifInterface(ori.getAbsolutePath());
            ExifInterface dstExif = new ExifInterface(dist.getAbsolutePath());
            for (String tagName : tags.value()) {
                dstExif.setAttribute(tagName, oriExif.getAttribute(tagName));
            }
            dstExif.saveAttributes();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
