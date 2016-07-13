package io.gorazd.length.unit;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

public class UnitImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private static final BigDecimal MULTIPLIER = new BigDecimal("0.01");
    private static final String SUFFIX = "m";
    private Unit unit;

    @Before
    public void setUp() throws Exception {
        unit = new UnitImpl(SUFFIX, MULTIPLIER);
    }

    @Test
    public void testGetSuffix() throws Exception {
        Assert.assertEquals(SUFFIX, unit.getSuffix());
    }

    @Test
    public void testGetMeterMultiplier() throws Exception {
        Assert.assertEquals(MULTIPLIER, unit.getMeterMultiplier());
    }

    @Test
    public void testNullSuffix() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("suffix is null");
        new UnitImpl(null, MULTIPLIER);
    }

    @Test
    public void testEmptySuffix() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("suffix is a blank string");
        new UnitImpl("", MULTIPLIER);
    }

    @Test
    public void testNullMultiplier() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("meterMultiplier is null");
        new UnitImpl(SUFFIX, null);
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(UnitImpl.class).verify();
    }
}