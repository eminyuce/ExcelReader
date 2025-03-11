package com.excel.reader.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation to track method execution time
@Target(ElementType.METHOD) // This can only be used on methods
@Retention(RetentionPolicy.RUNTIME) // The annotation will be available at runtime
public @interface TimedExecution {
    String value() default ""; // Optional: You can add additional attributes if needed
}
