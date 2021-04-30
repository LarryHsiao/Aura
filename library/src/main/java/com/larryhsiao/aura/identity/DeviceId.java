package com.larryhsiao.aura.identity;

import android.media.MediaDrm;
import com.larryhsiao.clotho.Source;

import java.util.Arrays;
import java.util.UUID;

/**
 * Source to build unique id of the Android device.
 * <p>
 * Use Drm id from device.
 *
 * @see <a href="https://stackoverflow.com/questions/58103580/android-10-imei-no-longer-available-on-api-29-looking-for-alternatives">Stackoverflow</a>
 */
public class DeviceId implements Source<String> {
    private final Source<String> fallbackSource;

    public DeviceId(Source<String> fallbackSource) {
        this.fallbackSource = fallbackSource;
    }

    @Override
    public String value() {
        try {
            return Arrays.toString(
                new MediaDrm(
                    new UUID(
                        -0x121074568629b532L,
                        -0x5c37d8232ae2de13L
                    )
                ).getPropertyByteArray(MediaDrm.PROPERTY_DEVICE_UNIQUE_ID)
            );
        } catch (Exception e) {
            return fallbackSource.value();
        }
    }
}
