package io.gorazd.length.unit;

import java.math.BigDecimal;

public class Units {
    public static final Unit METER = new UnitImpl("m", new BigDecimal("1"));
    public static final Unit YARD = new UnitImpl("yd", new BigDecimal("0.9144"));
    public static final Unit INCH = new UnitImpl("in", new BigDecimal("0.0254"));
}
