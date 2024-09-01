package org.example.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Loggable} annotation is used to mark methods or classes whose execution should
 * be logged. This annotation can be applied to individual methods or entire classes. When
 * applied to a class, all methods within that class are subject to logging.
 *
 * <p>Logging behavior typically involves recording method entry, exit, and potentially
 * other relevant runtime information such as execution time or parameter values.</p>
 *
 * <p>The retention policy is set to {@code RUNTIME} to ensure the annotation is available
 * during runtime for reflective operations or aspect-oriented programming (AOP).</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface EnableLoggable {
}
