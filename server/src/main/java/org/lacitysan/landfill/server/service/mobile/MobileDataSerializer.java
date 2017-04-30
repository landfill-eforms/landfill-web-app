package org.lacitysan.landfill.server.service.mobile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
		
		// Objects are manually mapped for better control of which fields are included.
		
		// Add instruments to exported data set.
		instrumentDao.getAll().parallelStream().forEach(instrument -> {
			
			// Map instrument fields.
			Map<String, Object> mappedInstrument = new LinkedHashMap<>();
			mappedInstrument.put("id", instrument.getId());
			mappedInstrument.put("serialNumber", instrument.getSerialNumber());
			mappedInstrument.put("serialNumber", instrument.getSerialNumber());
			mappedInstrument.put("instrumentType", instrument.getInstrumentType());
			mappedInstrument.put("instrumentStatus", instrument.getInstrumentStatus());
			mappedInstrument.put("site", instrument.getSite());
			mappedInstrument.put("description", instrument.getDescription());
			
			// Create a new map for instrument type.
			Map<String, Object> instrumentType = new HashMap<>();
			instrumentType.put("id", instrument.getInstrumentType().getId());
			instrumentType.put("type", instrument.getInstrumentType().getType());
			instrumentType.put("manufacturer", instrument.getInstrumentType().getManufacturer());
			instrumentType.put("description", instrument.getInstrumentType().getDescription());
			instrumentType.put("instantaneous", instrument.getInstrumentType().getInstantaneous());
			instrumentType.put("probe", instrument.getInstrumentType().getProbe());
			instrumentType.put("methanePercent", instrument.getInstrumentType().getMethanePercent());
			instrumentType.put("methanePpm", instrument.getInstrumentType().getMethanePpm());
			instrumentType.put("hydrogenSulfidePpm", instrument.getInstrumentType().getHydrogenSulfidePpm());
			instrumentType.put("oxygenPercent", instrument.getInstrumentType().getOxygenPercent());
			instrumentType.put("carbonDioxidePercent", instrument.getInstrumentType().getCarbonDioxidePercent());
			instrumentType.put("nitrogenPercent", instrument.getInstrumentType().getNitrogenPercent());
			instrumentType.put("pressure", instrument.getInstrumentType().getPressure());
			mappedInstrument.put("instrumentType", instrumentType);
			
			// Insert mapped instrument.
			exportedData.getInstruments().add(mappedInstrument);
			
		});
		
		// Add instruments to exported data set.
		userDao.getAll().parallelStream().forEach(user -> {
			
			// Map user fields.
			Map<String, Object> mappedUser = new LinkedHashMap<>();
			mappedUser.put("id", user.getId());
			mappedUser.put("username", user.getUsername());
			mappedUser.put("password", user.getPassword());
			mappedUser.put("firstname", user.getFirstname());
			mappedUser.put("middlename", user.getMiddlename());
			mappedUser.put("lastname", user.getLastname());
			mappedUser.put("emailAddress", user.getEmailAddress());
			mappedUser.put("employeeId", user.getEmployeeId());
			mappedUser.put("enabled", user.getEnabled());
			
			// Insert mapped user.
			exportedData.getUsers().add(mappedUser);
			
		});
		
		return exportedData;
		
	}

}
