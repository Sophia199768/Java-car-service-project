package org.example.aspects.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Auditable} annotation is used to indicate that a method or class should be
 * subject to auditing. When applied to a method, it signals that the method's execution
 * and relevant details should be logged or monitored. When applied to a class, it applies
 * auditing to all methods within the class.
 *
 * <p>Classes and methods annotated with {@code Auditable} are typically handled by an
 * aspect or interceptor that implements the auditing logic.</p>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Auditable {
}
