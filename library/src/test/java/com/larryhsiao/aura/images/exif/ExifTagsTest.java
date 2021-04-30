package com.larryhsiao.aura.images.exif;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static androidx.exifinterface.media.ExifInterface.*;

/**
 * Unit-test for the class {@link ExifTags}
 */
@RunWith(RobolectricTestRunner.class)
public class ExifTagsTest {

    /**
     * Check tags that is common to us.
     */
    @Test
    public void checkCommonTags() {
        final List<String> tags = new ExifTags().value();
        Assert.assertTrue(tags.contains(TAG_GPS_LONGITUDE));
        Assert.assertTrue(tags.contains(TAG_GPS_LATITUDE));
        Assert.assertTrue(tags.contains(TAG_ORIENTATION));
        Assert.assertTrue(tags.contains(TAG_COMPRESSION));
        Assert.assertTrue(tags.contains(TAG_COPYRIGHT));
        Assert.assertTrue(tags.contains(TAG_BITS_PER_SAMPLE));
        Assert.assertFalse(tags.contains(null));
    }
}