package io.ryos.jfixed.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a field in a structure class as representing a specific section.
 * Used in conjunction with @FixedStructure to identify which field represents which section.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FixedSection {
    /**
     * The section type this field represents.
     * 
     * @return the section type
     */
    SectionType value();
    
    /**
     * Section types in a fixed-length structure.
     */
    enum SectionType {
        /**
         * Header section - appears once at the beginning
         */
        HEADER,
        
        /**
         * Data section - can appear multiple times (repeatable)
         */
        DATA,
        
        /**
         * Trailer section - appears once before the end
         */
        TRAILER,
        
        /**
         * End section - appears once at the end
         */
        END
    }
}
