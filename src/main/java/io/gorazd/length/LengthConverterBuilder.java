package io.gorazd.length;

import java.math.RoundingMode;

public class LengthConverterBuilder {

    private int scale = 4;
    private RoundingMode rounding = RoundingMode.HALF_UP;

    protected LengthConverterBuilder() {}

    public LengthConverterBuilder setScale(int scale) {
        this.scale = scale;
        return this;
    }

    public LengthConverterBuilder setRounding(RoundingMode rounding) {
        this.rounding = rounding;
        return this;
    }

    public LengthConverter build() {
        return new LengthConverter(scale, rounding);
    }
}
