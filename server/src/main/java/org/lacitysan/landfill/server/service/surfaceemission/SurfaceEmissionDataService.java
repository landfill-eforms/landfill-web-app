package org.lacitysan.landfill.server.service.surfaceemission;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.surfaceemission.SurfaceEmissionDataDao;
import org.lacitysan.landfill.server.persistence.entity.surfaceemission.SurfaceEmissionData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.lacitysan.landfill.server.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles the logical operations for surface emissions data related classes.
 * @param <T> The type of the surface emissions exceedance data (ie. <code>InstantaneousData</code>).
 * @author Alvin Quach
 */
public abstract class SurfaceEmissionDataService<T extends SurfaceEmissionData> {
	
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
	
	abstract protected SurfaceEmissionDataDao<T> getCrudRepository();

}
