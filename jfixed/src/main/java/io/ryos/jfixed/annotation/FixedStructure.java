package io.ryos.jfixed.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class as a fixed-length structure definition.
 * A structure consists of Header, Data (repeatable), Trailer, and End sections.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FixedStructure {
    /**
     * Line identifier field name that determines the section type.
     * This field should contain a value that identifies which section the line belongs to.
     * For example, "H" for header, "D" for data, "T" for trailer, "E" for end.
     * 
     * @return the field name that contains the line identifier
     */
    String lineIdentifierField() default "";
    
    /**
     * Header identifier value. If a line's identifier field matches this value, it's treated as a header.
     * 
     * @return the header identifier value
     */
    String headerIdentifier() default "H";
    
    /**
     * Data identifier value. If a line's identifier field matches this value, it's treated as a data record.
     * 
     * @return the data identifier value
     */
    String dataIdentifier() default "D";
    
    /**
     * Trailer identifier value. If a line's identifier field matches this value, it's treated as a trailer.
     * 
     * @return the trailer identifier value
     */
    String trailerIdentifier() default "T";
    
    /**
     * End identifier value. If a line's identifier field matches this value, it's treated as an end record.
     * 
     * @return the end identifier value
     */
    String endIdentifier() default "E";
}
