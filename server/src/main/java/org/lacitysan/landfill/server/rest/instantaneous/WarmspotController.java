package org.lacitysan.landfill.server.rest.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.instantaneous.WarmspotDataDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/warmspot-data")
@RestController
public class WarmspotController {
	
	@Autowired
	WarmspotDataDao warmspotDataDao;
	
	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	@ResponseBody
	public List<WarmspotData> getAll() {
		return warmspotDataDao.getAll();
	}

}
