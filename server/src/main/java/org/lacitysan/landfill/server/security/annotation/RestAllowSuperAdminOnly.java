package org.lacitysan.landfill.server.security.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Specifies that the annotated method can only be accessed by the super admin account,
 * regardless of what other roles are specified by the <code>@RestControllerSecurity</code>
 * and <code>@RestSecurity</code> annotations.
 * @author Alvin Quach
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RestAllowSuperAdminOnly {

}
