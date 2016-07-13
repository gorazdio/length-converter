package io.gorazd.length.unit;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public class UnitImpl implements Unit {

    private final String suffix;

    private final BigDecimal meterMultiplier;

    public UnitImpl(@Nonnull String suffix, @Nonnull BigDecimal meterMultiplier) {
        Preconditions.checkNotNull(suffix, "suffix is null");
        Preconditions.checkArgument(!suffix.trim().isEmpty(), "suffix is a blank string");
        Preconditions.checkNotNull(meterMultiplier, "meterMultiplier is null");
        this.suffix = suffix;
        this.meterMultiplier = meterMultiplier;
    }

    @Nonnull
    public String getSuffix() {
        return suffix;
    }

    @Nonnull
    public BigDecimal getMeterMultiplier() {
        return meterMultiplier;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitImpl)) return false;
        UnitImpl unit = (UnitImpl) o;
        return Objects.equal(suffix, unit.suffix) &&
                Objects.equal(meterMultiplier, unit.meterMultiplier);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(suffix, meterMultiplier);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("suffix", suffix)
                .add("meterMultiplier", meterMultiplier)
                .toString();
    }
}
