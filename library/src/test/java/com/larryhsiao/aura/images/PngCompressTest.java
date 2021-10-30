package com.larryhsiao.aura.images;

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
public class PngCompressTest {
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
        new PngCompress(origin, target).fire();
        Assert.assertTrue(origin.length() > target.length());
    }
}