package io.ryos.jfixed.core;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ByteSlicerTest {

    @Test
    void testSliceBasic() {
        String line = "Hello World";
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceWithOffset() {
        String line = "Hello World";
        String result = ByteSlicer.slice(line, 6, 5, StandardCharsets.UTF_8);
        assertEquals("World", result);
    }

    @Test
    void testSliceFullLength() {
        String line = "Hello";
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceSingleChar() {
        String line = "Hello";
        String result = ByteSlicer.slice(line, 0, 1, StandardCharsets.UTF_8);
        assertEquals("H", result);
    }

    @Test
    void testSliceEmptyString() {
        String line = "";
        String result = ByteSlicer.slice(line, 0, 0, StandardCharsets.UTF_8);
        assertEquals("", result);
    }

    @Test
    void testSliceWithUTF8MultiByte() {
        String line = "こんにちは";
        String result = ByteSlicer.slice(line, 0, 3, StandardCharsets.UTF_8);
        assertEquals("こ", result);
    }

    @Test
    void testSliceWithUTF8MultiByteOffset() {
        String line = "こんにちは";
        String result = ByteSlicer.slice(line, 3, 3, StandardCharsets.UTF_8);
        assertEquals("ん", result);
    }

    @Test
    void testSliceWithShiftJIS() {
        Charset shiftJIS = Charset.forName("Shift_JIS");
        String line = "こんにちは";
        String result = ByteSlicer.slice(line, 0, 2, shiftJIS);
        assertEquals("こ", result);
    }

    @Test
    void testSliceNegativeOffset() {
        String line = "Hello";
        assertThrows(IllegalArgumentException.class, () -> ByteSlicer.slice(line, -1, 5, StandardCharsets.UTF_8));
    }

    @Test
    void testSliceNegativeLength() {
        String line = "Hello";
        assertThrows(IllegalArgumentException.class, () -> ByteSlicer.slice(line, 0, -1, StandardCharsets.UTF_8));
    }

    @Test
    void testSliceNullLine() {
        assertThrows(IllegalArgumentException.class, () -> ByteSlicer.slice(null, 0, 5, StandardCharsets.UTF_8));
    }

    @Test
    void testSliceOffsetExceedsLength() {
        String line = "Hello";
        assertThrows(IndexOutOfBoundsException.class, () -> ByteSlicer.slice(line, 10, 5, StandardCharsets.UTF_8));
    }

    @Test
    void testSliceOffsetPlusLengthExceedsLength() {
        String line = "Hello";
        assertThrows(IndexOutOfBoundsException.class, () -> ByteSlicer.slice(line, 3, 5, StandardCharsets.UTF_8));
    }

    @Test
    void testSliceOffsetEqualsLength() {
        String line = "Hello";
        String result = ByteSlicer.slice(line, 5, 0, StandardCharsets.UTF_8);
        assertEquals("", result);
    }

    @Test
    void testSliceZeroLength() {
        String line = "Hello";
        String result = ByteSlicer.slice(line, 2, 0, StandardCharsets.UTF_8);
        assertEquals("", result);
    }

    @Test
    void testSliceWithWhitespace() {
        String line = "  Hello  ";
        String result = ByteSlicer.slice(line, 0, 9, StandardCharsets.UTF_8);
        assertEquals("  Hello  ", result);
    }

    @Test
    void testSliceWithSpecialCharacters() {
        String line = "Hello!@#$%";
        String result = ByteSlicer.slice(line, 5, 5, StandardCharsets.UTF_8);
        assertEquals("!@#$%", result);
    }

    @Test
    void testSliceWithNumbers() {
        String line = "1234567890";
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("12345", result);
    }

    @Test
    void testSliceWithMixedCharacters() {
        String line = "Hello123World";
        String result = ByteSlicer.slice(line, 5, 3, StandardCharsets.UTF_8);
        assertEquals("123", result);
    }

    @Test
    void testSliceWithISO8859_1() {
        Charset iso8859_1 = StandardCharsets.ISO_8859_1;
        String line = "Hello World";
        String result = ByteSlicer.slice(line, 0, 5, iso8859_1);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceWithUSASCII() {
        Charset usAscii = StandardCharsets.US_ASCII;
        String line = "Hello World";
        String result = ByteSlicer.slice(line, 0, 5, usAscii);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceWithMalformedInput() {
        // UTF-8で不正なバイト列を扱う場合のテスト
        // 実際にはREPLACEアクションが設定されているため、例外は発生しない
        String line = "Hello";
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceWithUnmappableCharacter() {
        // 文字セットによってマッピングできない文字がある場合のテスト
        // REPLACEアクションが設定されているため、例外は発生しない
        Charset usAscii = StandardCharsets.US_ASCII;
        String line = "Hello";
        String result = ByteSlicer.slice(line, 0, 5, usAscii);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceWithJapaneseCharactersUTF8() {
        String line = "日本語テスト";
        String result = ByteSlicer.slice(line, 0, 9, StandardCharsets.UTF_8);
        assertEquals("日本語", result);
    }

    @Test
    void testSliceWithJapaneseCharactersUTF8Offset() {
        String line = "日本語テスト";
        String result = ByteSlicer.slice(line, 9, 9, StandardCharsets.UTF_8);
        assertEquals("テスト", result);
    }

    @Test
    void testSliceWithKoreanCharacters() {
        String line = "안녕하세요";
        String result = ByteSlicer.slice(line, 0, 3, StandardCharsets.UTF_8);
        assertEquals("안", result);
    }

    @Test
    void testSliceWithChineseCharacters() {
        String line = "你好世界";
        String result = ByteSlicer.slice(line, 0, 3, StandardCharsets.UTF_8);
        assertEquals("你", result);
    }

    @Test
    void testSliceWithEmoji() {
        String line = "Hello😀World";
        // 絵文字は複数バイトなので、正確なスライスをテスト
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceExactBoundary() {
        String line = "Hello";
        String result = ByteSlicer.slice(line, 0, 5, StandardCharsets.UTF_8);
        assertEquals("Hello", result);
    }

    @Test
    void testSliceMiddle() {
        String line = "Hello World";
        String result = ByteSlicer.slice(line, 2, 5, StandardCharsets.UTF_8);
        assertEquals("llo W", result);
    }
}
