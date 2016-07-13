package io.gorazd.length.length;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.gorazd.length.unit.Unit;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

/**
 * Representation of a measurement of length, consisting of a decimal value and a unit of measure.
 * The decimal value is implemented as {@link BigDecimal} in order to avoid loss of precision inherent in
 * the binary representation of {@link Double}. For convenience, the decimal value can be set and retrieved as a {@link String}.
 */
public class LengthImpl implements Length {
    private final BigDecimal value;
    private final Unit unit;

    public LengthImpl(@Nonnull String value, @Nonnull Unit unit) throws NumberFormatException {
        this(new BigDecimal(Preconditions.checkNotNull(value, "value is null")), unit);
    }

    public LengthImpl(@Nonnull BigDecimal value, @Nonnull Unit unit) {
        this.value = Preconditions.checkNotNull(value, "value is null").stripTrailingZeros();
        this.unit = Preconditions.checkNotNull(unit, "unit is null");
    }

    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    @Nonnull
    public Unit getUnit() {
        return unit;
    }

    @Nonnull
    public String asReadableString() {
        return String.format("%s %s", value.stripTrailingZeros().toPlainString(), unit.getSuffix());
    }

    @Nonnull
    public String getValueAsString() {
        return value.toPlainString();
    }

    @Override
    public String toString() {
        return asReadableString();
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;
        LengthImpl length = (LengthImpl) o;
        return Objects.equal(value, length.value) &&
                Objects.equal(unit, length.unit);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(value, unit);
    }
}
