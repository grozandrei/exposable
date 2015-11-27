/**
 *
 */
package org.grozandrei.exposable.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Exposable annotation. Can be applied to classes. All classes annotated with {@link Exposable} will have generated field names at compile time.
 *
 * @author Andrei Groza
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Exposable {
}
