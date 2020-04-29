package com.silverhetch.aura.images.exif;

import androidx.exifinterface.media.ExifInterface;
import com.silverhetch.clotho.Source;

/**
 * Source to fetch Exif attribute.
 */
public class ExifAttribute implements Source<String> {
    private final Source<ExifInterface> source;
    private final String tag;

    public ExifAttribute(Source<ExifInterface> source, String tag) {
        this.source = source;
        this.tag = tag;
    }

    @Override
    public String value() {
        return source.value().getAttribute(tag);
    }
}
