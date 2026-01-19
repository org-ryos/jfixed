package io.ryos.jfixed.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedLengthExceptionTest {

    @Test
    void testFixedLengthExceptionWithAllFields() {
        String message = "Test error message";
        int lineNumber = 42;
        String fieldName = "testField";
        String originalLine = "test line data";
        Throwable cause = new RuntimeException("Root cause");

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, cause);

        assertEquals(message, exception.getMessage());
        assertEquals(lineNumber, exception.getLineNumber());
        assertEquals(fieldName, exception.getFieldName());
        assertEquals(originalLine, exception.getOriginalLine());
        assertEquals(cause, exception.getCause());
    }

    @Test
    void testFixedLengthExceptionWithNullFieldName() {
        String message = "Test error message";
        int lineNumber = 1;
        String originalLine = "test line data";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, null, originalLine, null);

        assertEquals(message, exception.getMessage());
        assertEquals(lineNumber, exception.getLineNumber());
        assertNull(exception.getFieldName());
        assertEquals(originalLine, exception.getOriginalLine());
        assertNull(exception.getCause());
    }

    @Test
    void testFixedLengthExceptionWithNullOriginalLine() {
        String message = "Test error message";
        int lineNumber = 1;
        String fieldName = "testField";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, null, null);

        assertEquals(message, exception.getMessage());
        assertEquals(lineNumber, exception.getLineNumber());
        assertEquals(fieldName, exception.getFieldName());
        assertNull(exception.getOriginalLine());
        assertNull(exception.getCause());
    }

    @Test
    void testFixedLengthExceptionWithZeroLineNumber() {
        String message = "Test error message";
        int lineNumber = 0;
        String fieldName = "testField";
        String originalLine = "test line data";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, null);

        assertEquals(0, exception.getLineNumber());
    }

    @Test
    void testFixedLengthExceptionWithNegativeLineNumber() {
        String message = "Test error message";
        int lineNumber = -1;
        String fieldName = "testField";
        String originalLine = "test line data";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, null);

        assertEquals(-1, exception.getLineNumber());
    }

    @Test
    void testFixedLengthExceptionToString() {
        String message = "Test error message";
        int lineNumber = 42;
        String fieldName = "testField";
        String originalLine = "test line data";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, null);

        String toString = exception.toString();
        assertTrue(toString.contains("FixedLengthException"));
        assertTrue(toString.contains(message));
        assertTrue(toString.contains(String.valueOf(lineNumber)));
        assertTrue(toString.contains(fieldName));
        assertTrue(toString.contains(originalLine));
    }

    @Test
    void testFixedLengthExceptionToStringWithNullFields() {
        String message = "Test error message";
        int lineNumber = 1;

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, null, null, null);

        String toString = exception.toString();
        assertTrue(toString.contains("FixedLengthException"));
        assertTrue(toString.contains(message));
        assertTrue(toString.contains(String.valueOf(lineNumber)));
        assertTrue(toString.contains("null"));
    }

    @Test
    void testFixedLengthExceptionWithEmptyStringFields() {
        String message = "Test error message";
        int lineNumber = 1;
        String fieldName = "";
        String originalLine = "";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, null);

        assertEquals("", exception.getFieldName());
        assertEquals("", exception.getOriginalLine());
    }

    @Test
    void testFixedLengthExceptionInheritance() {
        String message = "Test error message";
        int lineNumber = 1;
        String fieldName = "testField";
        String originalLine = "test line data";

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, null);

        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testFixedLengthExceptionWithNestedCause() {
        String message = "Test error message";
        int lineNumber = 1;
        String fieldName = "testField";
        String originalLine = "test line data";
        Throwable rootCause = new IllegalArgumentException("Root cause");
        Throwable cause = new RuntimeException("Intermediate cause", rootCause);

        FixedLengthException exception = new FixedLengthException(
                message, lineNumber, fieldName, originalLine, cause);

        assertEquals(cause, exception.getCause());
        assertEquals(rootCause, exception.getCause().getCause());
    }
}
