package com.silverhetch.aura;

import android.location.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Locale;

/**
 * Learning test for Address object.
 */
@RunWith(RobolectricTestRunner.class)
public class AddressLearningTest {

    /**
     * Simple check the address with default location.
     */
    @Test
    public void simple() {
        Assert.assertEquals(
            null,
            new Address(Locale.getDefault()).getAddressLine(0)
        );
    }
}
