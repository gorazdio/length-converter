package io.gorazd.length;

import com.google.common.base.Preconditions;
import io.gorazd.length.length.Length;
import io.gorazd.length.length.LengthImpl;
import io.gorazd.length.unit.Unit;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Converter for converting a measurement of length from one unit of length to another. It uses {@link LengthImpl} to represent the measurement.
 * For convenience reasons, the value of measure can be passed directly, but only as a {@link String} for decimal precision reasons, see {@link LengthImpl} for more about decimal precision.
 * The converter support setting the scale (number of decimal places) and the {@link RoundingMode} to be used in the calculation of the final result.
 *
 * Example:
 * LengthConverter converter = LengthConverter.builder().setRounding(RoundingMode.DOWN).setScale(2).build();
 * converter.convert("10.22", Units.METER, UNITS.INCH);
 */
public class LengthConverter {

    protected final int scale;
    protected final RoundingMode rounding;

    protected LengthConverter(int scale, RoundingMode rounding) {
        this.scale = scale;
        this.rounding = rounding;
    }

    @Nonnull
    public Length convert(@Nonnull Length sourceLength, @Nonnull Unit targetUnit) {
        Preconditions.checkNotNull(sourceLength, "sourceLength was null");
        Preconditions.checkNotNull(targetUnit, "targetUnit was null");
        final BigDecimal meterSourceLength = sourceLength.getValue().multiply(sourceLength.getUnit().getMeterMultiplier());
        final BigDecimal unitTargetLength = meterSourceLength.divide(targetUnit.getMeterMultiplier(), scale, rounding);
        return new LengthImpl(unitTargetLength, targetUnit);
    }

    @Nonnull
    public Length convert(@Nonnull String value, @Nonnull Unit sourceUnit, @Nonnull Unit targetUnit) {
        Preconditions.checkNotNull(value, "value was null");
        Preconditions.checkNotNull(sourceUnit, "sourceUnit was null");
        Preconditions.checkNotNull(targetUnit, "targetUnit was null");
        final Length sourceLength = new LengthImpl(value, sourceUnit);
        return convert(sourceLength, targetUnit);
    }

    public static LengthConverterBuilder builder() {
        return new LengthConverterBuilder();
    }
}
