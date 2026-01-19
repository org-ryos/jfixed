package io.ryos.jfixed;

import io.ryos.jfixed.core.FixedLengthEngine;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthParserTest {

    @Test
    void testCreate() {
        FixedLengthEngine engine = FixedLengthParser.create();
        assertNotNull(engine);
    }

    @Test
    void testCreateWithCharset() {
        FixedLengthEngine engine = FixedLengthParser.create(StandardCharsets.UTF_8);
        assertNotNull(engine);
    }

    @Test
    void testCreateWithDifferentCharsets() {
        Charset[] charsets = {
                StandardCharsets.UTF_8,
                StandardCharsets.ISO_8859_1,
                StandardCharsets.US_ASCII,
                StandardCharsets.UTF_16
        };

        for (Charset charset : charsets) {
            FixedLengthEngine engine = FixedLengthParser.create(charset);
            assertNotNull(engine);
        }
    }

    @Test
    void testCreateWithNullCharset() {
        assertThrows(IllegalArgumentException.class, () -> FixedLengthParser.create(null));
    }

    @Test
    void testBuilder() {
        FixedLengthEngineBuilder builder = FixedLengthParser.builder();
        assertNotNull(builder);
    }

    @Test
    void testBuilderWithCustomSettings() {
        FixedLengthEngine engine = FixedLengthParser.builder()
                .charset(StandardCharsets.ISO_8859_1)
                .registerConverter(CustomType.class, (value, format) -> new CustomType(value))
                .build();
        assertNotNull(engine);
    }

    @Test
    void testCreateDefaultIsUTF8() {
        FixedLengthEngine engine1 = FixedLengthParser.create();
        FixedLengthEngine engine2 = FixedLengthParser.create(StandardCharsets.UTF_8);
        assertNotNull(engine1);
        assertNotNull(engine2);
    }

    @Test
    void testBuilderFluentInterface() {
        FixedLengthEngine engine = FixedLengthParser.builder()
                .charset(StandardCharsets.UTF_8)
                .registerConverter(CustomType.class, (value, format) -> new CustomType(value))
                .build();
        assertNotNull(engine);
    }

    @Test
    void testCreateMultipleInstances() {
        FixedLengthEngine engine1 = FixedLengthParser.create();
        FixedLengthEngine engine2 = FixedLengthParser.create();
        assertNotNull(engine1);
        assertNotNull(engine2);
        assertNotSame(engine1, engine2);
    }

    @Test
    void testBuilderMultipleInstances() {
        FixedLengthEngineBuilder builder1 = FixedLengthParser.builder();
        FixedLengthEngineBuilder builder2 = FixedLengthParser.builder();
        assertNotNull(builder1);
        assertNotNull(builder2);
        assertNotSame(builder1, builder2);
    }

    // Helper class for testing
    static class CustomType {
        final String value;

        CustomType(String value) {
            this.value = value;
        }
    }
}
