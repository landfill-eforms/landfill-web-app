package org.lacitysan.landfill.server.service.serviceemission;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.serviceemission.ServiceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.ServiceEmissionData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles the logical operations for service emissions data related classes.
 * @param <T> The type of the service emissions exceedance data (ie. <code>InstantaneousData</code>).
 * @author Alvin Quach
 */
public abstract class ServiceEmissionDataService<T extends ServiceEmissionData> {
	
	@Autowired
	protected MonitoringPointService monitoringPointService;
	
	public List<T> getBySite(String siteEnumName) {
		Site site = monitoringPointService.getSiteByEnumName(siteEnumName);
		if (site == null) {
			return new ArrayList<>();
		}
		return getCrudRepository().getBySiteAndDate(site, null, null);
	}
	
	public List<T> getBySiteAndDate(String siteEnumName, Long start, Long end) {
		Site site = monitoringPointService.getSiteByEnumName(siteEnumName);
		if (site == null) {
			return new ArrayList<>();
		}
		start = (start == null || start < 0) ? null : start;
		end = (end == null || end < 0) ? null : DateTimeUtils.addDay(end);
		return getCrudRepository().getBySiteAndDate(site, start, end);
	}
	
	abstract protected ServiceEmissionDataDao<T> getCrudRepository();

}
