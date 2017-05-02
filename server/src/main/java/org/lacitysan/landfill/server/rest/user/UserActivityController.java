package org.lacitysan.landfill.server.rest.user;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.user.UserActivity;
import org.lacitysan.landfill.server.persistence.enums.user.UserPermission;
import org.lacitysan.landfill.server.security.annotation.RestSecurity;
import org.lacitysan.landfill.server.service.user.UserActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/user-activity")
@RestController
public class UserActivityController {
	
	@Autowired
	UserActivityService userActivityService;
	
	@RestSecurity(UserPermission.VIEW_USERS)
	@RequestMapping(value="/list/user/id/{id}", method=RequestMethod.GET)
	public List<UserActivity> getByUserId(@PathVariable String id) {
		try {
			// For now, limit to activity within past 7 days.
			// TODO Add activity cutoff to application settings.
			return userActivityService.getByUserIdAndDate(Integer.valueOf(id), 7);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}
	
}
