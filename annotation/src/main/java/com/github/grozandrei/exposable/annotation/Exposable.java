/**
 *
 */
package com.github.grozandrei.exposable.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.lang.model.element.Modifier;

/**
 * Exposable annotation. Can be applied to classes. All classes annotated with {@link Exposable} will have generated field names at compile time.
 *
 * @author Andrei Groza
 *
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Exposable {

	/**
	 * Retrieves the modifiers to exclude from generating metamodel
	 * 
	 * @return the modifiers to exclude from generating metamodel
	 */
	Modifier[] exclude() default { Modifier.STATIC };
}
