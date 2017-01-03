package org.lacitysan.landfill.server.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lacitysan.landfill.lib.enumeration.UserRole;

/**
 * Specifies which <code>UserRole</code> can access the methods in annotated controller.
 * Can be overridden on a per-method basis by annotating the method with <code>RestSecurity</code>.
 * @author Alvin Quach
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestControllerSecurity {

	/** List of user role that can access the resources in the annotated controller. */
	UserRole[] value() default {};
	
}
