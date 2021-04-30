package com.larryhsiao.aura.images.exif;

import androidx.exifinterface.media.ExifInterface;
import com.larryhsiao.clotho.Source;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static androidx.exifinterface.media.ExifInterface.*;

/**
 * Source to build common exif tags with androidx package.
 */
public class ExifTags implements Source<List<String>> {
    /**
     * Tags changed by image width, height, size, etc.
     * We exclude these tags which will be rewrite when image content changed.
     */
    private static final Set<String> dynamicTags;

    static {
        dynamicTags = new HashSet<>();
        dynamicTags.add(TAG_IMAGE_WIDTH);
        dynamicTags.add(TAG_IMAGE_LENGTH);
        dynamicTags.add(TAG_JPEG_INTERCHANGE_FORMAT);
        dynamicTags.add(TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
    }

    @Override
    public List<String> value() {
        List<String> result = new ArrayList<>();
        for (Field field : ExifInterface.class.getFields()) {
            try {
                if (field.getName().startsWith("TAG_")) {
                    Object exifName = field.get(null);
                    if (exifName instanceof String && !dynamicTags.contains(exifName)) {
                        result.add(exifName.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
