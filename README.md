# Length converter

A simple library that converts measurement of length from one unit to another. The library achieves high precision with decimal numbers by using BigDecimal for representing values and doing mathematical operations. Because of this, all values must be passed to the library as either a BigDecimal or a String in order to avoid loss of precision inherent in double.

## Example

LengthConverter converter = LengthConverter.builder().setRounding(RoundingMode.DOWN).setScale(2).build();
converter.convert("10.22", Units.METER, UNITS.INCH);
