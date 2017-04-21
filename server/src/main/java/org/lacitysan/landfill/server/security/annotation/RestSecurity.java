package org.lacitysan.landfill.server.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;

/**
 * Specifies which <code>UserPermission</code> can access the annotated method.
 * By default, the permissions specified by this annotation will override any permissions specified by the 
 * controller's <code>@RestControllerSecurity</code> annotation.
 * @author Alvin Quach
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RestSecurity {

	/** List of user permission that can access the annotated resource. */
	UserPermission[] value() default {};

	/** (Optional) Modifies the REST security behavior for the annotated resource. */
	RestSecurityMode mode() default RestSecurityMode.OVERRIDE;
	
}
