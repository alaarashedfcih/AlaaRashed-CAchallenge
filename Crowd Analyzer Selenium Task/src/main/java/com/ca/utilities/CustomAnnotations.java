package com.ca.utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class contains custom annotations for Excel data mapping.
 */
public class CustomAnnotations {

    /**
     * The @ExcelColumn annotation is used to map fields to specific columns in an Excel sheet.
     * It supports both index-based and name-based mapping for better flexibility.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface ExcelColumn {
        /**
         * Specifies the index of the column in the Excel sheet.
         * Defaults to 0 if not explicitly set.
         *
         * @return the column index
         */
        int value() default 0;

        /**
         * Optional name of the column in the Excel sheet.
         * This allows referencing columns by name instead of index.
         *
         * @return the column name
         */
        String name() default "";
    }
}
