package org.lacitysan.landfill.server.security.aspect;

import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.lacitysan.landfill.lib.enumeration.UserRole;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.security.annotation.RestSecurityMode;
import org.springframework.core.annotation.AnnotationUtils;
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

	private static final boolean DEBUG = true;
	private static final String DENIED_ROLES_NO_MATCH = "User does not have any of the required roles(s).";

	@Before("execution(* org.lacitysan.landfill.server.persistence.controllers..*(..))")
	public void before(JoinPoint joinPoint) throws AccessDeniedException {

		if (DEBUG) printStart(joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getName());

		// Get the Authentication from the security context holder.
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		// If no Authentication was found, then deny access.
		if (auth == null) {
			String message = "Authentication error.";
			if (DEBUG) printDenied(message);
			throw new AccessDeniedException(message);
		}

		// Get user roles from the Authentication.
		Set<String> userRoles = auth.getAuthorities().stream().map(g -> g.getAuthority()).collect(Collectors.toSet());

		// If the user is a super admin, then allow access.
		if (userRoles.contains(UserRole.SUPER_ADMIN.getName())) {
			if (DEBUG) printSuccess("User is a super admin.");
			return;
		}

		// The set of roles allowed to access the method as defined by the method's @RestSecurity and/or the controller's @RestControllerSecurity.
		Set<String> allowedRoles = new HashSet<>();

		// Get the method that is being called.
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

		// Get the RestSecurity annotation from the method. Traverses super methods if RestSecurity can be found on the given method itself.
		RestSecurity restSecurity = AnnotationUtils.findAnnotation(method, RestSecurity.class);

		// Get the set of roles allowed to access the method from its RestSecurty, and add it to the allowedRoles set.
		if (restSecurity != null) {
			allowedRoles.addAll(Arrays.asList(restSecurity.value()).stream().map(r -> r.toString()).collect(Collectors.toSet()));
		}

		else if (DEBUG && restSecurity == null) System.out.println("No RestSecurity was found for the method.");

		// Get the RestSecurityMode from the RestSecurity. If no roles were defined by the RestSecurity (or if it was null), then the rest security mode will default to APPEND.
		RestSecurityMode restSecurityMode = (allowedRoles.isEmpty() ? RestSecurityMode.APPEND : restSecurity.mode());

		// If the RestSecurityMode is APPEND, then an additional set of roles will have to be retrieved from the controller's RestControllerSecurity.
		if (restSecurityMode == RestSecurityMode.APPEND) {

			// Get the RestControllerSecurity from the controller. Traverses super classes if RestControllerSecurity can be found on the given class itself.
			RestControllerSecurity restControllerSecurity = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), RestControllerSecurity.class);

			// Get the set of roles allowed to access the method from the RestControllerSecurty and add the roles to the allowedRoles set.
			if (restControllerSecurity != null) {
				allowedRoles.addAll(Arrays.asList(restControllerSecurity.value()).stream().map(r -> r.toString()).collect(Collectors.toSet()));
			}

			else if (DEBUG) System.out.println("No RestControllerSecurity was found for the controller.");
		}
		
		// If the allowedRoles set is empty, then everyone with a valid Authentication is allowed to access the resource.
		if (allowedRoles.isEmpty()) {
			if (DEBUG) printSuccess("No specific roles are required to access this resource.");
			return;
		}
		
		// If any of the user's roles match a role from the allowedRoles set, then allow access.
		if (allowedRoles.removeAll(userRoles)) {
			if (DEBUG) printSuccess("User has at least one of the required role(s).");
			return;
		}

		// Deny access if none of the roles match.
		else {
			String message = "User does not have any of the required roles(s).";
			if (DEBUG) printDenied(message);
			throw new AccessDeniedException(message);
		}
		
	}

	/** Prints the start of the debug message */
	private void printStart(String methodName, String className) {
		System.out.println("*****REST SECURITY ASPECT*****");
		System.out.println("Hijacked '" + methodName + "()' from '" + className + "'.");
	}

	/** Prints the end of the debug message if access was denied. */
	private void printDenied(String message) {
		System.out.println("Access Denied: " + message);
		System.out.println("******************************");
	}

	/** Prints the end of the debug message if access was granted. */
	private void printSuccess(String message) {
		System.out.println("Access Granted: " + message);
		System.out.println("******************************");
	}

}
