package io.gorazd.length;

import io.gorazd.length.length.LengthImpl;
import io.gorazd.length.unit.Unit;
import io.gorazd.length.unit.UnitImpl;
import io.gorazd.length.unit.Units;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class LengthConverterTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LengthConverter lengthConverter;

    @Before
    public void setUp() throws Exception {
        lengthConverter = new LengthConverter(4, RoundingMode.HALF_UP);
    }

    @Test
    public void testCustomUnits() throws Exception {
        final Unit fooUnit = new UnitImpl("foo", new BigDecimal("0.5"));
        final Unit barUnit = new UnitImpl("bar", new BigDecimal("2"));
        assertEquals(new LengthImpl(new BigDecimal("2.5"), barUnit), lengthConverter.convert(new LengthImpl(new BigDecimal("10"), fooUnit), barUnit));
    }

    @Test
    public void testConvertToMeter() throws Exception {
        assertEquals(new LengthImpl("9.144", Units.METER), lengthConverter.convert("10", Units.YARD, Units.METER));
        assertEquals(new LengthImpl("0.254", Units.METER), lengthConverter.convert("10", Units.INCH, Units.METER));
    }

    @Test
    public void testConvertFromMeter() throws Exception {
        assertEquals(new LengthImpl("10.9361", Units.YARD), lengthConverter.convert("10", Units.METER, Units.YARD));
        assertEquals(new LengthImpl("393.7008", Units.INCH), lengthConverter.convert("10", Units.METER, Units.INCH));
    }

    @Test
    public void testConvertLargeValue() throws Exception {
        assertEquals(new LengthImpl("10086802315020533472.2222", Units.YARD), lengthConverter.convert(Long.MAX_VALUE + "", Units.METER, Units.YARD));
    }

    @Test
    public void testConvertLargeNegativeValue() throws Exception {
        assertEquals(new LengthImpl("-10086802315020533473.3158", Units.YARD), lengthConverter.convert(Long.MIN_VALUE + "", Units.METER, Units.YARD));
    }

    @Test
    public void testConvertFromZero() throws Exception {
        assertEquals(new LengthImpl("0", Units.YARD), lengthConverter.convert("0", Units.METER, Units.YARD));
        assertEquals(new LengthImpl("0", Units.METER), lengthConverter.convert("0", Units.METER, Units.METER));
    }

    @Test
    public void testConvertNullValue() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("value was null");
        lengthConverter.convert(null, Units.YARD, Units.METER);
    }

    @Test
    public void testConvertInvalidValue() throws Exception {
        thrown.expect(NumberFormatException.class);
        lengthConverter.convert("123foo", Units.YARD, Units.METER);
    }

    @Test
    public void testConvertEmptyValue() throws Exception {
        thrown.expect(NumberFormatException.class);
        lengthConverter.convert(" ", Units.YARD, Units.METER);
    }

    @Test
    public void testConvertNullSourceUnit() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("sourceUnit was null");
        lengthConverter.convert("10", null, Units.METER);
    }

    @Test
    public void testConvertNullTargetUnit() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("targetUnit was null");
        lengthConverter.convert("10", Units.YARD, null);
    }

    @Test
    public void testConvertWithLengthObject() throws Exception {
        assertEquals(new LengthImpl("9.144", Units.METER), lengthConverter.convert(new LengthImpl("10", Units.YARD), Units.METER));
    }

    @Test
    public void testConvertWithLengthObjectNullValue() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("sourceLength was null");
        lengthConverter.convert(null, Units.METER);
    }

    @Test
    public void testConvertWithLengthObjectNullUnit() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("targetUnit was null");
        lengthConverter.convert(new LengthImpl("10", Units.YARD), null);
    }

    @Test
    public void testCustomScale() throws Exception {
        LengthConverter lengthConverter = new LengthConverter(2, RoundingMode.HALF_UP);
        assertEquals(new LengthImpl("10.94", Units.YARD), lengthConverter.convert("10", Units.METER, Units.YARD));
    }

    @Test
    public void testCustomRounding() throws Exception {
        LengthConverter lengthConverter = new LengthConverter(4, RoundingMode.UP);
        assertEquals(new LengthImpl("10.9362", Units.YARD), lengthConverter.convert("10", Units.METER, Units.YARD));
    }
}