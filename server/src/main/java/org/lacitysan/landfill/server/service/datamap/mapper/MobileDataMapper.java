package org.lacitysan.landfill.server.service.datamap.mapper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.lacitysan.landfill.server.model.IMENumberStatus;
import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.persistence.dao.user.UserDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.service.MonitoringPointService;
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
	UserDao userDao;
	
	@Autowired
	MonitoringPointService monitoringPointService;
	
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
	 * Unmaps a <code>InstantaneousDataMobile</code> object into an <code>UnverifiedInstantaneousData</code> object.
	 * Currently, the user, monitoring point, and instrument data are not mapped, and will be <code>null</code> in the resulting output.
	 * @param entity The <code>InstantaneousDataMobile</code> object to be unmapped.
	 * @param imeNumbers
	 * @return The <code>InstantaneousData</code> representation of the input object.
	 * @throws ParseException 
	 */
	public UnverifiedInstantaneousData unmapInstantaneousData(InstantaneousDataMobile entity, Collection<IMENumber> imeNumbers) throws ParseException {

		UnverifiedInstantaneousData result = new UnverifiedInstantaneousData();

		Instrument instrument = new Instrument();
		instrument.setId(1);
		result.setInstrument(instrument);

		// TODO Implement this inside the enum.
		if (entity.getGridId() != null && !entity.getGridId().isEmpty() && entity.getLandFillLocation() != null && !entity.getLandFillLocation().isEmpty()) {
			MonitoringPoint grid = getGridBySiteNameAndId(entity.getLandFillLocation(), entity.getGridId());
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
					result.setImeNumber(imeNumber);
					imeNumberFound = true;
					break;
				}
			}
			if (!imeNumberFound) {
				IMENumber imeNumber = new IMENumber();
				imeNumber.setId(0);
				Site site = getSiteByShortName(entity.getImeNumber().substring(0,2)); // Redundant?
				Timestamp discoveryDate = new Timestamp(new SimpleDateFormat("MMddyy").parse(entity.getImeNumber().substring(3,9)).getTime());
				if (site != null && entity.getImeNumber().substring(10,12).matches("^-?\\d+$")) {
					imeNumber.setSite(site);
					imeNumber.setDiscoveryDate(discoveryDate);
					imeNumber.setSequence(Short.parseShort(entity.getImeNumber().substring(10,12)));
					imeNumber.setStatus(IMENumberStatus.UNVERIFIED);
					imeNumbers.add(imeNumber);
					result.setImeNumber(imeNumber);
				}
			}
		}
		result.setMethaneLevel((int)(entity.getMethaneReading() * 100));

		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		result.setStartTime(new Timestamp(dateFormat.parse(entity.getmStartDate()).getTime()));
		result.setEndTime(new Timestamp(dateFormat.parse(entity.getmEndDate()).getTime()));

		return result;

	}
	
	// TODO Move this to its own service.
		private Site getSiteByShortName(String shortName) {
			for (Site site : Site.values()) {
				if (site.getShortName().equals(shortName)) {
					return site;
				}
			}
			return null;
		}
	
	// TODO Move this to its own service.
	private Site getSiteBySiteName(String siteName) {
		for (Site site : Site.values()) {
			if (site.getName().equals(siteName)) {
				return site;
			}
		}
		return null;
	}
	
	// TODO Move this to its own service.
	private MonitoringPoint getGridBySiteNameAndId(String siteName, String gridId) {
		Site site = getSiteBySiteName(siteName);
		for (MonitoringPoint point : MonitoringPoint.values()) {
			if (point.getSite() == site && point.getType() == MonitoringPointType.GRID && point.getName().equals(gridId)) {
				return point;
			}
		}
		return null;
	}

}
