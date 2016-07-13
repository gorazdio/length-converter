package io.gorazd.length.length;

import io.gorazd.length.length.Length;
import io.gorazd.length.length.LengthImpl;
import io.gorazd.length.unit.Unit;
import io.gorazd.length.unit.Units;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class LengthImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private final Unit UNIT = Units.METER;
    private final String STRING_VALUE = "111.111";
    private final String STRING_ALL = "111.111 m";
    private final BigDecimal VALUE = new BigDecimal(STRING_VALUE);
    private Length length;

    @Before
    public void setUp() throws Exception {
        length = new LengthImpl(VALUE, UNIT);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testNullStringValue() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("value is null");
        new LengthImpl((String)null, UNIT);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testNullBigDecimalValue() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("value is null");
        new LengthImpl((BigDecimal) null, UNIT);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testInvalidStringValue() throws Exception {
        thrown.expect(NumberFormatException.class);
        new LengthImpl("123foo", UNIT);
    }

    @Test
    public void testEmptyStringValue() throws Exception {
        thrown.expect(NumberFormatException.class);
        new LengthImpl(" ", UNIT);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void testNullUnit() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("unit is null");
        new LengthImpl(VALUE, null);
    }

    @Test
    public void testStringValueConstructor() throws Exception {
        Length length = new LengthImpl(STRING_VALUE, UNIT);
        assertEquals(VALUE, length.getValue());
    }

    @Test
    public void testGetValue() throws Exception {
        assertEquals(VALUE, length.getValue());
    }

    @Test
    public void testDropsTrailingZeroes() throws Exception {
        assertEquals(VALUE, new LengthImpl("111.11100", UNIT).getValue());
    }

    @Test
    public void testGetValueAsString() throws Exception {
        assertEquals(STRING_VALUE, length.getValueAsString());
    }

    @Test
    public void testGetUnit() throws Exception {
        assertEquals(UNIT, length.getUnit());
    }

    @Test
    public void testAsReadableString() throws Exception {
        assertEquals(STRING_ALL, length.asReadableString());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(STRING_ALL, length.toString());
    }

    @Test
    public void equalsContract() {
        EqualsVerifier.forClass(LengthImpl.class).verify();
    }
}