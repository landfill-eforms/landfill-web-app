package org.lacitysan.landfill.server.service.datamap.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.Instrument;
import org.lacitysan.landfill.server.persistence.entity.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.entity.User;
import org.lacitysan.landfill.server.service.datamap.model.mobile.InstantaneousDataMobile;

/**
 * A utilities class for converting data models between the web server and the mobile app.
 * @author Alvin Quach
 */
public class MobileDataMapper {

	/**
	 * Maps an <code>InstantaneousData</code> object into an <code>InstantaneousDataMobile</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMobile</code> representation of the input object.
	 */
	public static InstantaneousDataMobile mapInstantaneousData(InstantaneousData entity) {
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
	public static InstantaneousData unmapInstantaneousData(InstantaneousDataMobile entity) throws ParseException {
		
		InstantaneousData result = new InstantaneousData();

		Instrument instrument = new Instrument();
		instrument.setId(1);
		result.setInstrument(instrument);
		
		User user = new User();
		user.setUserId(1);
		result.setUser(user);
		
		MonitoringPoint monitoringPoint = new MonitoringPoint();
		monitoringPoint.setId(51);
		result.setMonitoringPoint(monitoringPoint);
		
		result.setImeNumber("");
		result.setBarometricPressure((short)2998);
		result.setMethaneLevel((int)(entity.getMethaneReading() * 100));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		result.setStartTime(new Timestamp(dateFormat.parse(entity.getmStartDate()).getTime()));
		result.setEndTime(new Timestamp(dateFormat.parse(entity.getmEndDate()).getTime()));
		
		return result;
		
	}

}
