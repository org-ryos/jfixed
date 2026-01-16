package io.ryos.jfixed.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConverterRegistryTest {

    private ConverterRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new ConverterRegistry();
    }

    @Test
    void testStringConversion() {
        Object result = registry.convert(String.class, "test", "");
        assertEquals("test", result);
    }

    @Test
    void testIntegerConversion() {
        Object result = registry.convert(Integer.class, "123", "");
        assertEquals(123, result);
    }

    @Test
    void testIntegerConversionWithWhitespace() {
        Object result = registry.convert(Integer.class, "  456  ", "");
        assertEquals(456, result);
    }

    @Test
    void testIntegerConversionNull() {
        Object result = registry.convert(Integer.class, null, "");
        assertNull(result);
    }

    @Test
    void testIntegerConversionEmpty() {
        Object result = registry.convert(Integer.class, "", "");
        assertNull(result);
    }

    @Test
    void testIntegerConversionWhitespaceOnly() {
        Object result = registry.convert(Integer.class, "   ", "");
        assertNull(result);
    }

    @Test
    void testPrimitiveIntConversion() {
        Object result = registry.convert(int.class, "789", "");
        assertEquals(789, result);
    }

    @Test
    void testPrimitiveIntConversionNull() {
        Object result = registry.convert(int.class, null, "");
        assertEquals(0, result);
    }

    @Test
    void testPrimitiveIntConversionEmpty() {
        Object result = registry.convert(int.class, "", "");
        assertEquals(0, result);
    }

    @Test
    void testLongConversion() {
        Object result = registry.convert(Long.class, "1234567890123", "");
        assertEquals(1234567890123L, result);
    }

    @Test
    void testLongConversionNull() {
        Object result = registry.convert(Long.class, null, "");
        assertNull(result);
    }

    @Test
    void testLongConversionEmpty() {
        Object result = registry.convert(Long.class, "", "");
        assertNull(result);
    }

    @Test
    void testPrimitiveLongConversion() {
        Object result = registry.convert(long.class, "9876543210", "");
        assertEquals(9876543210L, result);
    }

    @Test
    void testPrimitiveLongConversionNull() {
        Object result = registry.convert(long.class, null, "");
        assertEquals(0L, result);
    }

    @Test
    void testPrimitiveLongConversionEmpty() {
        Object result = registry.convert(long.class, "", "");
        assertEquals(0L, result);
    }

    @Test
    void testDoubleConversion() {
        Object result = registry.convert(Double.class, "123.456", "");
        assertEquals(123.456, (Double) result, 0.0001);
    }

    @Test
    void testDoubleConversionNull() {
        Object result = registry.convert(Double.class, null, "");
        assertNull(result);
    }

    @Test
    void testDoubleConversionEmpty() {
        Object result = registry.convert(Double.class, "", "");
        assertNull(result);
    }

    @Test
    void testPrimitiveDoubleConversion() {
        Object result = registry.convert(double.class, "789.123", "");
        assertEquals(789.123, (Double) result, 0.0001);
    }

    @Test
    void testPrimitiveDoubleConversionNull() {
        Object result = registry.convert(double.class, null, "");
        assertEquals(0.0, result);
    }

    @Test
    void testPrimitiveDoubleConversionEmpty() {
        Object result = registry.convert(double.class, "", "");
        assertEquals(0.0, result);
    }

    @Test
    void testFloatConversion() {
        Object result = registry.convert(Float.class, "123.45", "");
        assertEquals(123.45f, result);
    }

    @Test
    void testFloatConversionNull() {
        Object result = registry.convert(Float.class, null, "");
        assertNull(result);
    }

    @Test
    void testFloatConversionEmpty() {
        Object result = registry.convert(Float.class, "", "");
        assertNull(result);
    }

    @Test
    void testPrimitiveFloatConversion() {
        Object result = registry.convert(float.class, "456.78", "");
        assertEquals(456.78f, result);
    }

    @Test
    void testPrimitiveFloatConversionNull() {
        Object result = registry.convert(float.class, null, "");
        assertEquals(0.0F, result);
    }

    @Test
    void testPrimitiveFloatConversionEmpty() {
        Object result = registry.convert(float.class, "", "");
        assertEquals(0.0F, result);
    }

    @Test
    void testBooleanConversionTrue() {
        Object result = registry.convert(Boolean.class, "1", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionTrueCase() {
        Object result = registry.convert(Boolean.class, "true", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionTrueUpperCase() {
        Object result = registry.convert(Boolean.class, "TRUE", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionYes() {
        Object result = registry.convert(Boolean.class, "yes", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionYesUpperCase() {
        Object result = registry.convert(Boolean.class, "YES", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionY() {
        Object result = registry.convert(Boolean.class, "y", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionYUpperCase() {
        Object result = registry.convert(Boolean.class, "Y", "");
        assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBooleanConversionFalse() {
        Object result = registry.convert(Boolean.class, "0", "");
        assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testBooleanConversionNull() {
        Object result = registry.convert(Boolean.class, null, "");
        assertNull(result);
    }

    @Test
    void testBooleanConversionEmpty() {
        Object result = registry.convert(Boolean.class, "", "");
        assertNull(result);
    }

    @Test
    void testPrimitiveBooleanConversionTrue() {
        Object result = registry.convert(boolean.class, "1", "");
        assertTrue((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionTrueCase() {
        Object result = registry.convert(boolean.class, "true", "");
        assertTrue((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionYes() {
        Object result = registry.convert(boolean.class, "yes", "");
        assertTrue((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionY() {
        Object result = registry.convert(boolean.class, "y", "");
        assertTrue((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionFalse() {
        Object result = registry.convert(boolean.class, "0", "");
        assertFalse((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionNull() {
        Object result = registry.convert(boolean.class, null, "");
        assertFalse((Boolean) result);
    }

    @Test
    void testPrimitiveBooleanConversionEmpty() {
        Object result = registry.convert(boolean.class, "", "");
        assertFalse((Boolean) result);
    }

    @Test
    void testBigDecimalConversion() {
        Object result = registry.convert(BigDecimal.class, "123.456", "");
        assertEquals(new BigDecimal("123.456"), result);
    }

    @Test
    void testBigDecimalConversionWithWhitespace() {
        Object result = registry.convert(BigDecimal.class, "  789.123  ", "");
        assertEquals(new BigDecimal("789.123"), result);
    }

    @Test
    void testBigDecimalConversionNull() {
        Object result = registry.convert(BigDecimal.class, null, "");
        assertNull(result);
    }

    @Test
    void testBigDecimalConversionEmpty() {
        Object result = registry.convert(BigDecimal.class, "", "");
        assertNull(result);
    }

    @Test
    void testLocalDateConversion() {
        Object result = registry.convert(LocalDate.class, "20231225", "");
        assertEquals(LocalDate.of(2023, 12, 25), result);
    }

    @Test
    void testLocalDateConversionWithFormat() {
        Object result = registry.convert(LocalDate.class, "2023-12-25", "yyyy-MM-dd");
        assertEquals(LocalDate.of(2023, 12, 25), result);
    }

    @Test
    void testLocalDateConversionNull() {
        Object result = registry.convert(LocalDate.class, null, "");
        assertNull(result);
    }

    @Test
    void testLocalDateConversionEmpty() {
        Object result = registry.convert(LocalDate.class, "", "");
        assertNull(result);
    }

    @Test
    void testLocalDateConversionInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(LocalDate.class, "20231225", "invalid"));
    }

    @Test
    void testLocalDateConversionInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(LocalDate.class, "20231301", ""));
    }

    @Test
    void testLocalDateTimeConversion() {
        Object result = registry.convert(LocalDateTime.class, "20231225143000", "");
        assertEquals(LocalDateTime.of(2023, 12, 25, 14, 30, 0), result);
    }

    @Test
    void testLocalDateTimeConversionWithFormat() {
        Object result = registry.convert(LocalDateTime.class, "2023-12-25 14:30:00", "yyyy-MM-dd HH:mm:ss");
        assertEquals(LocalDateTime.of(2023, 12, 25, 14, 30, 0), result);
    }

    @Test
    void testLocalDateTimeConversionNull() {
        Object result = registry.convert(LocalDateTime.class, null, "");
        assertNull(result);
    }

    @Test
    void testLocalDateTimeConversionEmpty() {
        Object result = registry.convert(LocalDateTime.class, "", "");
        assertNull(result);
    }

    @Test
    void testLocalDateTimeConversionInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(LocalDateTime.class, "20231225143000", "invalid"));
    }

    @Test
    void testLocalDateTimeConversionInvalidDateTime() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(LocalDateTime.class, "20231301143000", ""));
    }

    @Test
    void testRegisterCustomConverter() {
        registry.register(CustomType.class, (value, format) -> new CustomType(value));
        Object result = registry.convert(CustomType.class, "test", "");
        assertNotNull(result);
        assertTrue(result instanceof CustomType);
        assertEquals("test", ((CustomType) result).value);
    }

    @Test
    void testRegisterNullType() {
        assertThrows(IllegalArgumentException.class, () -> registry.register(null, (value, format) -> value));
    }

    @Test
    void testRegisterNullConverter() {
        assertThrows(IllegalArgumentException.class, () -> registry.register(String.class, null));
    }

    @Test
    void testConvertUnsupportedType() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(CustomType.class, "test", ""));
    }

    @Test
    void testConvertWithException() {
        registry.register(CustomType.class, (value, format) -> {
            throw new RuntimeException("Conversion error");
        });
        assertThrows(IllegalArgumentException.class, () -> registry.convert(CustomType.class, "test", ""));
    }

    @Test
    void testConvertIntegerWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(Integer.class, "not-a-number", ""));
    }

    @Test
    void testConvertLongWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(Long.class, "not-a-number", ""));
    }

    @Test
    void testConvertDoubleWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(Double.class, "not-a-number", ""));
    }

    @Test
    void testConvertFloatWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(Float.class, "not-a-number", ""));
    }

    @Test
    void testConvertBigDecimalWithInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> registry.convert(BigDecimal.class, "not-a-number", ""));
    }

    // Helper class for testing custom converters
    static class CustomType {
        final String value;

        CustomType(String value) {
            this.value = value;
        }
    }
}
