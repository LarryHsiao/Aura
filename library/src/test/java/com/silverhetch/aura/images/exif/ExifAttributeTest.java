package com.silverhetch.aura.images.exif;

import androidx.exifinterface.media.ExifInterface;
import com.larryhsiao.clotho.source.ConstSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;

import static androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL;

/**
 * Unit-test for the class {@link ExifAttribute}
 */
@RunWith(RobolectricTestRunner.class)
public class ExifAttributeTest {
    /**
     * Get datetime original attribute.
     */
    @Test
    public void datetimeOriginal() throws Exception {
        Assert.assertEquals(
            "2008:10:22 17:00:07",
            new ExifAttribute(
                new ConstSource<>(
                    new ExifInterface(
                        new File(
                            getClass().getClassLoader().getResource(
                                "exif_sample_geo_001.jpg"
                            ).toURI()
                        ).getAbsolutePath()
                    )
                ), TAG_DATETIME_ORIGINAL
            ).value()
        );
    }
}