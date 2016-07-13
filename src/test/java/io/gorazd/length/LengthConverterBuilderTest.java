package io.gorazd.length;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;

public class LengthConverterBuilderTest {

    private LengthConverter lengthConverter1;
    private LengthConverter lengthConverter2;


    @Before
    public void setUp() throws Exception {
        lengthConverter1 = LengthConverter.builder().build();
        lengthConverter2 = LengthConverter.builder().setRounding(RoundingMode.DOWN).setScale(2).build();
    }

    @Test
    public void testSetScale() throws Exception {
        Assert.assertEquals(4, lengthConverter1.scale);
        Assert.assertEquals(2, lengthConverter2.scale);
    }

    @Test
    public void testSetRounding() throws Exception {
        Assert.assertEquals(RoundingMode.HALF_UP, lengthConverter1.rounding);
        Assert.assertEquals(RoundingMode.DOWN, lengthConverter2.rounding);
    }
}