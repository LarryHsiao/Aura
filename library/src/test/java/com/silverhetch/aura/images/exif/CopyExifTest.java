package com.silverhetch.aura.images.exif;

import android.graphics.BitmapFactory;
import androidx.exifinterface.media.ExifInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static android.graphics.Bitmap.CompressFormat.JPEG;

/**
 * Unit-test for the class {@link CopyExif}
 */
@RunWith(RobolectricTestRunner.class)
public class CopyExifTest {

    /***
     * Check the target have same attribute.
     */
    @Test
    public void exif() throws IOException, URISyntaxException {
        final File origin = new File(
            getClass().getClassLoader().getResource(
                "exif_sample_geo_001.jpg"
            ).toURI()
        );
        final File target = Files.createTempFile("copy_exif-", ".jpg").toFile();
        BitmapFactory.decodeFile(origin.getAbsolutePath()).compress(
            JPEG, 80, new FileOutputStream(target)
        );
        new CopyExif(new ExifTags(), origin, target).fire();

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
}