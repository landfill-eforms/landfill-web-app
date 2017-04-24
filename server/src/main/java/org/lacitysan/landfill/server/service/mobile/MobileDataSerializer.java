package org.lacitysan.landfill.server.service.mobile;

import org.lacitysan.landfill.server.persistence.dao.instrument.InstrumentDao;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.service.mobile.model.ExportedData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Serializer service for converting data models from the web server to the mobile app.
 * @author Alvin Quach
 */
@Service
public class MobileDataSerializer {
	
	@Autowired
	InstrumentDao instrumentDao;
	
	@Autowired
	UserDao userDao;
	
	/** Takes a smelly dump and sends it to Grant. */
	public ExportedData generateDataDump() {
		ExportedData exportedData = new ExportedData();
		exportedData.getInstruments().addAll(instrumentDao.getAll());
		exportedData.getUsers().addAll(userDao.getAll());
		return exportedData;
	}

}
