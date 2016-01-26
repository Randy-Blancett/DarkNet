/**
 * 
 */
package net.darkowl.darkNet.darkObjects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Randy Blancett
 * @since Jan 6, 2016
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Output {
	// should the method be checked for change
	public boolean enabled() default true;
}
