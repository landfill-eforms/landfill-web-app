package org.lacitysan.landfill.server.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lacitysan.landfill.server.persistence.enums.UserPermission;

/**
 * Specifies which <code>UserPermission</code> can access the methods in annotated controller.
 * Can be overridden on a per-method basis by annotating the method with <code>RestSecurity</code>.
 * @author Alvin Quach
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RestControllerSecurity {

	/** List of user permission that can access the resources in the annotated controller. */
	UserPermission[] value() default {};
	
}
