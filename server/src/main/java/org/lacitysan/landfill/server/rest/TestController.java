package org.lacitysan.landfill.server.rest;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/test")
@RestController
public class TestController {

	@Autowired
	EmailService emailService;
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public void sendEmail() {
		emailService.sendTestEmail();
	}
		
}
