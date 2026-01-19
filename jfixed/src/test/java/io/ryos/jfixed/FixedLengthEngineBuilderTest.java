package io.ryos.jfixed;

import io.ryos.jfixed.core.FixedLengthEngine;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthEngineBuilderTest {

    @Test
    void testCreate() {
        FixedLengthEngineBuilder builder = FixedLengthEngineBuilder.create();
        assertNotNull(builder);
    }

    @Test
    void testDefaultCharset() {
        FixedLengthEngine engine = FixedLengthEngineBuilder.create().build();
        assertNotNull(engine);
    }

    @Test
    void testCharset() {
        Charset charset = StandardCharsets.ISO_8859_1;
        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .charset(charset)
                .build();
        assertNotNull(engine);
    }

    @Test
    void testCharsetNull() {
        assertThrows(IllegalArgumentException.class, () -> FixedLengthEngineBuilder.create().charset(null));
    }

    @Test
    void testRegisterConverter() {
        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .registerConverter(CustomType.class, (value, format) -> new CustomType(value))
                .build();
        assertNotNull(engine);
    }

    @Test
    void testRegisterConverterNullType() {
        assertThrows(IllegalArgumentException.class, () -> FixedLengthEngineBuilder.create()
                .registerConverter(null, (value, format) -> value));
    }

    @Test
    void testRegisterConverterNullConverter() {
        assertThrows(IllegalArgumentException.class, () -> FixedLengthEngineBuilder.create()
                .registerConverter(String.class, null));
    }

    @Test
    void testRegisterConverterMultiple() {
        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .registerConverter(CustomType.class, (value, format) -> new CustomType(value))
                .registerConverter(AnotherCustomType.class, (value, format) -> new AnotherCustomType(value))
                .build();
        assertNotNull(engine);
    }

    @Test
    void testBuilderFluentInterface() {
        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .charset(StandardCharsets.UTF_8)
                .registerConverter(CustomType.class, (value, format) -> new CustomType(value))
                .charset(StandardCharsets.ISO_8859_1)
                .build();
        assertNotNull(engine);
    }

    @Test
    void testBuildMultipleTimes() {
        FixedLengthEngineBuilder builder = FixedLengthEngineBuilder.create()
                .charset(StandardCharsets.UTF_8);

        FixedLengthEngine engine1 = builder.build();
        FixedLengthEngine engine2 = builder.build();

        assertNotNull(engine1);
        assertNotNull(engine2);
        assertNotSame(engine1, engine2);
    }

    @Test
    void testRegisterConverterWithFormat() {
        BiFunction<String, String, CustomType> converter = (value, format) -> new CustomType(value + ":" + format);

        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .registerConverter(CustomType.class, converter)
                .build();
        assertNotNull(engine);
    }

    @Test
    void testDifferentCharsets() {
        Charset[] charsets = {
                StandardCharsets.UTF_8,
                StandardCharsets.ISO_8859_1,
                StandardCharsets.US_ASCII,
                StandardCharsets.UTF_16
        };

        for (Charset charset : charsets) {
            FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                    .charset(charset)
                    .build();
            assertNotNull(engine);
        }
    }

    @Test
    void testRegisterConverterOverwrite() {
        BiFunction<String, String, CustomType> converter1 = (value, format) -> new CustomType(value + "1");
        BiFunction<String, String, CustomType> converter2 = (value, format) -> new CustomType(value + "2");

        FixedLengthEngine engine = FixedLengthEngineBuilder.create()
                .registerConverter(CustomType.class, converter1)
                .registerConverter(CustomType.class, converter2)
                .build();
        assertNotNull(engine);
    }

    // Helper classes for testing
    static class CustomType {
        final String value;

        CustomType(String value) {
            this.value = value;
        }
    }

    static class AnotherCustomType {
        final String value;

        AnotherCustomType(String value) {
            this.value = value;
        }
    }
}
