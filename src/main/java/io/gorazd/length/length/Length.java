package io.gorazd.length.length;

import io.gorazd.length.unit.Unit;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public interface Length {
    @Nonnull
    BigDecimal getValue();

    @Nonnull
    Unit getUnit();

    @Nonnull
    String asReadableString();

    @Nonnull
    String getValueAsString();
}
