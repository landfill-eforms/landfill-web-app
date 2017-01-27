package org.lacitysan.landfill.server.service.datamap.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.lacitysan.landfill.lib.enumeration.Site;
import org.lacitysan.landfill.server.persistence.dao.MonitoringPointsDao;
import org.lacitysan.landfill.server.persistence.dao.UsersDao;
import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.Instrument;
import org.lacitysan.landfill.server.persistence.entity.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.entity.User;
import org.lacitysan.landfill.server.service.datamap.model.mobile.InstantaneousDataMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A utilities class for converting data models between the web server and the mobile app.
 * @author Alvin Quach
 */
@Service
public class MobileDataMapper {

	@Autowired
	UsersDao usersDao;

	@Autowired
	MonitoringPointsDao monitoringPointsDao;

	/**
	 * Maps an <code>InstantaneousData</code> object into an <code>InstantaneousDataMobile</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMobile</code> representation of the input object.
	 */
	public InstantaneousDataMobile mapInstantaneousData(InstantaneousData entity) {
		InstantaneousDataMobile result = new InstantaneousDataMobile();
		// TODO Implement logic
		return result;
	}

	/**
	 * Unmaps a <code>InstantaneousDataMobile</code> object into an <code>InstantaneousData</code> object.
	 * Currently, the user, monitoring point, and instrument data are not mapped, and will be <code>null</code> in the resulting output.
	 * @param entity The <code>InstantaneousDataMobile</code> object to be unmapped.
	 * @return The <code>InstantaneousData</code> representation of the input object.
	 * @throws ParseException 
	 */
	public InstantaneousData unmapInstantaneousData(InstantaneousDataMobile entity) throws ParseException {

		InstantaneousData result = new InstantaneousData();

		Instrument instrument = new Instrument();
		instrument.setId(1);
		result.setInstrument(instrument);

		// Will this have performance issues?
		User user = usersDao.getUserByUsername(entity.getInspectorName());
		if (user == null) {
			user = new User();
			user.setUserId(1);
		}
		result.setUser(user);

		MonitoringPoint monitoringPoint = null;
		// TODO Implement this inside the enum.
		if (entity.getGridId() != null && !entity.getGridId().isEmpty() && entity.getLandFillLocation() != null && !entity.getLandFillLocation().isEmpty()) {
			for (Site site : Site.values()) {
				if (site.getName().equals(entity.getLandFillLocation())) {
					monitoringPoint = monitoringPointsDao.getBySiteAndMonitoringPointName(site, entity.getGridId());
					break;
				}
			}
		}
		if (monitoringPoint == null) {
			System.out.println("Error Unmapping Instantaneous Data: Monitoring point not found.");
			return null;
		}
		result.setMonitoringPoint(monitoringPoint);

		result.setImeNumber(entity.getImeNumber());
		result.setBarometricPressure((short)2998);
		result.setMethaneLevel((int)(entity.getMethaneReading() * 100));

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		result.setStartTime(new Timestamp(dateFormat.parse(entity.getmStartDate()).getTime()));
		result.setEndTime(new Timestamp(dateFormat.parse(entity.getmEndDate()).getTime()));

		return result;

	}

}
