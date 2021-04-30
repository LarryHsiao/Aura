package com.larryhsiao.aura.images.exif;

import androidx.exifinterface.media.ExifInterface;
import com.larryhsiao.clotho.source.ConstSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.io.File;

import static androidx.exifinterface.media.ExifInterface.TAG_DATETIME_ORIGINAL;

/**
 * Unit-test for the class {@link ExifUnixTimeStamp}
 */
@RunWith(RobolectricTestRunner.class)
public class ExifUnixTimeStampTest {
    /**
     * Simple check
     */
    @Test
    public void checkTime() throws Exception {
        Assert.assertEquals(
            new Long(1224694807000L), //2008:10:22 17:00:07
            new ExifUnixTimeStamp(
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
                )
            ).value());
    }

    /**
     * -1L if exif does not contain time.
     */
    @Test
    public void noTimeData() throws Exception {
        Assert.assertEquals(
            new Long(-1L), //2008:10:22 17:00:07
            new ExifUnixTimeStamp(
                new ExifAttribute(
                    new ConstSource<>(
                        new ExifInterface(
                            new File(
                                getClass().getClassLoader().getResource(
                                    "exif_sample_nothing.jpg"
                                ).toURI()
                            ).getAbsolutePath()
                        )
                    ), TAG_DATETIME_ORIGINAL
                )
            ).value()
        );
    }
}