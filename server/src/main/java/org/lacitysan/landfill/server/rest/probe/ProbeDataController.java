package org.lacitysan.landfill.server.rest.probe;

import java.util.List;

import org.lacitysan.landfill.server.config.app.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.dao.probe.ProbeDataDao;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alvin Quach
 */
@RequestMapping(ApplicationConstant.RESOURCE_PATH + "/probe-data")
@RestController
public class ProbeDataController {
	
	@Autowired
	ProbeDataDao probeDataDao;

	@RequestMapping(value="/list/all", method=RequestMethod.GET)
	public List<ProbeData> getAll() {
		return probeDataDao.getAll();
	}

	@RequestMapping(value="/unique/id/{id}", method=RequestMethod.GET)
	public ProbeData getById(@PathVariable String id) {
		try {
			return probeDataDao.getById(Integer.valueOf(id));
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ProbeData create(@RequestBody ProbeData probeData) {
		// TODO Create service.
		return probeDataDao.create(probeData);
	}

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public ProbeData update(@RequestBody ProbeData probeData) {
		// TODO Create service.
		return probeDataDao.update(probeData);
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public ProbeData delete(@RequestBody ProbeData probeData) {
		if (probeData == null) return null;
		return probeDataDao.delete(probeData);
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ProbeData deleteById(@PathVariable String id) {
		try {
			ProbeData probeData = new ProbeData();
			probeData.setId(Integer.parseInt(id));
			return delete(probeData);
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

}
