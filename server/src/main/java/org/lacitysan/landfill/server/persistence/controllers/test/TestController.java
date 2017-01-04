package org.lacitysan.landfill.server.persistence.controllers.test;

import org.lacitysan.landfill.lib.enumeration.UserRole;
import org.lacitysan.landfill.server.persistence.dao.test.SleepTestDao;
import org.lacitysan.landfill.server.security.annotation.RestControllerSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * For testing purposes.
 * @author Alvin Quach
 */
@RestControllerSecurity(UserRole.SUPER_ADMIN)
@RequestMapping("rest/test")
@RestController
public class TestController {

	@Autowired
	SleepTestDao sleepTestDao;
	
	@RequestMapping(value="/sleep/{id}", method=RequestMethod.GET)
	public Object get(@PathVariable Integer id) {
		return sleepTestDao.getSleepById(id);
	}
	
	@RequestMapping(value="/authentication", method=RequestMethod.GET)
	public Object get() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getClass().getName());
		return auth;
	}
	
}
