package com.larryhsiao.aura.images;

import androidx.exifinterface.media.ExifInterface;
import com.larryhsiao.aura.images.exif.ExifTags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 * Unit-test for the class {@link JpegCompress}
 */
@RunWith(RobolectricTestRunner.class)
public class JpegCompressTest {

    /**
     * Check exif preserved.
     */
    @Test
    public void exifPreserved() throws IOException, URISyntaxException {
        final File origin = new File(
            getClass().getClassLoader().getResource(
                "exif_sample_geo_001.jpg"
            ).toURI()
        );
        final File target = Files.createTempFile("jpeg-compress-", ".jpg").toFile();
        new JpegCompress(origin, target).fire();

        ExifInterface ori = new ExifInterface(origin.getAbsolutePath());
        ExifInterface dst = new ExifInterface(target.getAbsolutePath());
        for (String tag : new ExifTags().value()) {
            Assert.assertEquals(
                "Tag: "+tag + " not equal",
                ori.getAttribute(tag),
                dst.getAttribute(tag)
            );
        }
    }

    /**
     * Check exif preserved.
     */
    @Test
    public void sizeReduced() throws IOException, URISyntaxException {
        final File origin = new File(
            getClass().getClassLoader().getResource(
                "exif_sample_geo_001.jpg"
            ).toURI()
        );
        final File target = Files.createTempFile("jpeg-compress-", ".jpg").toFile();
        new JpegCompress(origin, target).fire();
        Assert.assertTrue(origin.length() > target.length());
    }
}