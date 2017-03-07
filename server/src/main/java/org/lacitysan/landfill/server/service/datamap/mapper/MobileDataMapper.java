package org.lacitysan.landfill.server.service.datamap.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.MonitoringPoint;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.service.datamap.model.mobile.MobileInstantaneousData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A utilities class for converting data models between the web server and the mobile app.
 * @author Alvin Quach
 */
@Service
public class MobileDataMapper {

	@Autowired
	UserDao userDao;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
	/**
	 * Maps an <code>InstantaneousData</code> object into an <code>InstantaneousDataMobile</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMobile</code> representation of the input object.
	 */
	public MobileInstantaneousData mapInstantaneousData(InstantaneousData entity) {
		MobileInstantaneousData result = new MobileInstantaneousData();
		// TODO Implement logic
		return result;
	}

	/**
	 * Unmaps a <code>InstantaneousDataMobile</code> object into an <code>UnverifiedInstantaneousData</code> object.
	 * Currently, the user, monitoring point, and instrument data are not mapped, and will be <code>null</code> in the resulting output.
	 * @param entity The <code>InstantaneousDataMobile</code> object to be unmapped.
	 * @param imeNumbers
	 * @return The <code>InstantaneousData</code> representation of the input object.
	 * @throws ParseException 
	 */
	public UnverifiedInstantaneousData unmapInstantaneousData(MobileInstantaneousData entity, Collection<IMENumber> imeNumbers) throws ParseException {

		UnverifiedInstantaneousData result = new UnverifiedInstantaneousData();

		Instrument instrument = new Instrument();
		instrument.setId(1);
		result.setInstrument(instrument);

		// TODO Implement this inside the enum.
		if (entity.getGridId() != null && !entity.getGridId().isEmpty() && entity.getLandFillLocation() != null && !entity.getLandFillLocation().isEmpty()) {
			MonitoringPoint grid = monitoringPointService.getGridBySiteNameAndId(monitoringPointService.getSiteByName(entity.getLandFillLocation()), entity.getGridId());
			if (grid == null) {
				System.out.println("Error Unmapping Instantaneous Data: Grid not found.");
				return null;
			}
			result.setMonitoringPoint(grid);
		}
		
		if (entity.getImeNumber() != null) {
			boolean imeNumberFound = false;
			for (IMENumber imeNumber : imeNumbers) {
				if (imeNumber.toString().equals(entity.getImeNumber())) {
					result.setImeNumbers(new HashSet<>(Arrays.asList(new IMENumber[] {imeNumber}))); // TODO Change this.
					imeNumberFound = true;
					break;
				}
			}
			if (!imeNumberFound) {
/*				IMENumber imeNumber = new IMENumber();
				imeNumber.setId(0);
				Site site = monitoringPointService.getSiteByShortName(entity.getImeNumber().substring(0,2)); // Redundant?
				Timestamp discoveryDate = new Timestamp(new SimpleDateFormat("MMddyy").parse(entity.getImeNumber().substring(3,9)).getTime());
				if (site != null && entity.getImeNumber().substring(10,12).matches("^-?\\d+$") && entity.getImeNumber().substring(10,12).matches("^-?\\d+$")) {
					imeNumber.setSite(site);
					imeNumber.setDiscoveryDate(discoveryDate);
					imeNumber.setSequence(Short.parseShort(entity.getImeNumber().substring(10,12)));
					imeNumber.setStatus(IMENumberStatus.UNVERIFIED);
					imeNumbers.add(imeNumber);
					result.setImeNumber(imeNumber);
				}*/
			}
		}
		result.setMethaneLevel((int)(entity.getMethaneReading() * 100));

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		result.setStartTime(new Timestamp(dateFormat.parse(entity.getmStartDate()).getTime()));
		result.setEndTime(new Timestamp(dateFormat.parse(entity.getmEndDate()).getTime()));

		return result;

	}

}
