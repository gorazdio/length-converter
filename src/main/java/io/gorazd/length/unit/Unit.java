package io.gorazd.length.unit;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public interface Unit {
    @Nonnull
    String getSuffix();

    @Nonnull
    BigDecimal getMeterMultiplier();
}
