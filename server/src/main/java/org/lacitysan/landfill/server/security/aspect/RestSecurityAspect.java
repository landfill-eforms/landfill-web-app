package org.lacitysan.landfill.server.security.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestAllowSuperAdminOnly;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurityMode;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Performs security checks on REST controllers and methods using aspect oriented programming.
 * @author Alvin Quach
 */
@Aspect
@Component
public class RestSecurityAspect {
	
	/** The generic message to include in the response when the user does not have access to the resource. */
	private static final String ACCESS_DENIED_MESSAGE = "You are not authorized to access this resource.";

	@Before("execution(* org.lacitysan.landfill.server.rest..*(..))")
	public void before(JoinPoint joinPoint) throws AccessDeniedException {

		if (ApplicationConstant.DEBUG) printStart(joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getName());

		// Get the Authentication from the security context holder.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// If no Authentication was found, then deny access.
		if (auth == null) {
			if (ApplicationConstant.DEBUG) printDenied("Authentication could not be retrived from SecurityContextHolder.");
			throw new AccessDeniedException("Authentication error.");
		}

		// Get user permissions from the Authentication.
		Set<String> userPermissions = auth.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toSet());

		// If the user is the super admin, then allow access.
		if (userPermissions.contains(ApplicationConstant.SUPER_ADMIN_PERMISSION_NAME)) {
			if (ApplicationConstant.DEBUG) printSuccess("User is the super admin.");
			return;
		}
		
		// At this point, it has been determined that the user is not the super admin.
		
		// Check if the class is only accessible by the super admin.
		if (AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), RestAllowSuperAdminOnly.class) != null) {
			if (ApplicationConstant.DEBUG) printDenied("This controller can only be accessed by the super admin.");
			throw new AccessDeniedException(ACCESS_DENIED_MESSAGE);
		}

		// Get the method that is being called.
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

		// Check if the method is only accessible by the super admin.
		if (AnnotationUtils.findAnnotation(method, RestAllowSuperAdminOnly.class) != null) {
			if (ApplicationConstant.DEBUG) printDenied("This method can only be accessed by the super admin.");
			throw new AccessDeniedException(ACCESS_DENIED_MESSAGE);
		}
		
		// If the controller/method is not restricted to the super admin, then allow access if the user is an regular admin.
		if (userPermissions.contains(UserPermission.ADMIN.name())) {
			if (ApplicationConstant.DEBUG) printSuccess("User is an admin.");
			return;
		}
		
		// Get the @RestSecurity annotation from the method. Traverses super methods if @RestSecurity cannot be found on the given method itself.
		RestSecurity restSecurity = AnnotationUtils.findAnnotation(method, RestSecurity.class);
		
		// The set of permissions allowed to access the method as defined by the method's @RestSecurity and/or the controller's @RestControllerSecurity.
		Set<String> allowedPermissions = new HashSet<>();

		// Get the set of permissions allowed to access the method from its @RestSecurty, and add it to the allowedPermissions set.
		if (restSecurity != null) {
			allowedPermissions.addAll(Arrays.asList(restSecurity.value()).stream().map(r -> r.name()).collect(Collectors.toSet()));
		}

		else if (ApplicationConstant.DEBUG && restSecurity == null) printSuccess("No RestSecurity was found for the method.");

		// Get the RestSecurityMode from the @RestSecurity. If no permissions were defined by the @RestSecurity (or if it was null), then the rest security mode will default to APPEND.
		RestSecurityMode restSecurityMode = (allowedPermissions.isEmpty() ? RestSecurityMode.APPEND : restSecurity.mode());

		// If the RestSecurityMode is APPEND, then an additional set of permissions will have to be retrieved from the controller's @RestControllerSecurity.
		if (restSecurityMode == RestSecurityMode.APPEND) {

			// Get the @RestControllerSecurity from the controller. Traverses super classes if @RestControllerSecurity cannot be found on the given class itself.
			RestControllerSecurity restControllerSecurity = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), RestControllerSecurity.class);

			// Get the set of permissions allowed to access the method from the @RestControllerSecurty and add the permissions to the allowedPermissions set.
			if (restControllerSecurity != null) {
				allowedPermissions.addAll(Arrays.asList(restControllerSecurity.value()).stream().map(r -> r.name()).collect(Collectors.toSet()));
			}

			else if (ApplicationConstant.DEBUG) printSuccess("No RestControllerSecurity was found for the controller.");
		}

		// If the allowedPermissions set is empty, then everyone with a valid Authentication is allowed to access the resource.
		if (allowedPermissions.isEmpty()) {
			if (ApplicationConstant.DEBUG) printSuccess("No specific permissions are required to access this resource.");
			return;
		}

		// If any of the user's permissions match a permission from the allowedPermissions set, then allow access.
		if (allowedPermissions.removeAll(userPermissions)) {
			if (ApplicationConstant.DEBUG) printSuccess("User has at least one of the required permission(s).");
			return;
		}

		// Deny access if none of the permissions match.
		else {
			if (ApplicationConstant.DEBUG) printDenied("User does not have any of the required permissions(s).");
			throw new AccessDeniedException(ACCESS_DENIED_MESSAGE);
		}

	}
	
	/** Prints the start of the debug message */
	private void printStart(String methodName, String className) {
		System.out.println("DEBUG:\tRest Security Aspect:");
		System.out.println("\tHijacked '" + methodName + "()' from '" + className + "'.");
	}

	/** Prints the end of the debug message if access was denied. */
	private void printDenied(String message) {
		System.out.println("\tAccess Denied: " + message);
	}

	/** Prints the end of the debug message if access was granted. */
	private void printSuccess(String message) {
		System.out.println("\tAccess Granted: " + message);
	}

}
