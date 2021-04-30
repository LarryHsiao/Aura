package com.larryhsiao.aura.identity;

import com.larryhsiao.clotho.Source;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Test for {@link DeviceId}.
 */
@RunWith(RobolectricTestRunner.class)
public class DeviceIdTest {
    /**
     * The uuid exist.
     */
    @Test
    public void exist() {
        assertNotEquals(
            "",
            new DeviceId(new Source<String>() {
                @Override
                public String value() {
                    return "";
                }
            }).value()
        );
    }
}