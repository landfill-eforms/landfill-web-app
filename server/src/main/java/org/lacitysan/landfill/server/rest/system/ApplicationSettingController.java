package org.lacitysan.landfill.server.rest.system;

import java.util.Map;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableService;
import org.lacitysan.landfill.server.config.app.vars.model.ApplicationVariableSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/appvars")
@RestController
public class ApplicationSettingController {
	
	@Autowired
	ApplicationVariableService applictaionVariableService;
	
	@RequestMapping(method=RequestMethod.GET)
	Map<String, ApplicationVariableSerialization> getMap() {
		return applictaionVariableService.map();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	Map<String, ApplicationVariableSerialization> update(Map<String, ApplicationVariableSerialization> map) {
		return applictaionVariableService.update(map);
	}
	
}
