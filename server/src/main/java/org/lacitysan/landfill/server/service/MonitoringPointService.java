package org.lacitysan.landfill.server.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPoint;
import org.lacitysan.landfill.server.persistence.enums.location.MonitoringPointType;
import org.lacitysan.landfill.server.persistence.enums.location.Site;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for both the <code>Site</code> and <code>MonitoringPoint</code> enums.
 * @author Alvin Quach
 */
@Service
public class MonitoringPointService {
	
	/**
	 * Finds a site by its short name. 
	 * If there are multiple sites with the same short name, then it will try to return the active site.
	 * @param shortName The case-insensitive short name of the site.
	 * @return A <code>Site</code> that matches the given short name, or <code>null</code> if no suitable site was found.
	 */
	public Site getSiteByShortName(String shortName) {
		
		Site[] sites = Site.values();
		
		// Prefer active site.
		for (Site site : sites) {
			if (site.getShortName().equalsIgnoreCase(shortName) && site.isActive()) {
				return site;
			}
		}
		
		// If no active site was found, then consider all sites.
		for (Site site : sites) {
			if (site.getShortName().equalsIgnoreCase(shortName)) {
				return site;
			}
		}
		
		// If no site was found, then return null.
		return null;
		
	}
	
	/**
	 * Finds a site by its enum name.
	 * @param siteEnumName The case-insensitive enum name of the site.
	 * @return A <code>Site</code> that matches the given enum name, or <code>null</code> if no suitable value was found.
	 */
	public Site getSiteByEnumName(String siteEnumName) {
		for (Site site : Site.values()) {
			if (site.name().equalsIgnoreCase(siteEnumName)) {
				return site;
			}
		}
		return null;
	}
	
	/**
	 * Finds a grid by its site and grid ID.
	 * @param siteName The grid's <code>Site</code> enum value.
	 * @param gridId The case-insensitive ID or name of the grid.
	 * @return A grid-type <code>MonitoringPoint</code> that matches the input parameters, or <code>null</code> if no suitable grid was found. 
	 */
	public MonitoringPoint getGridBySiteNameAndId(Site site, String gridId) {
		for (MonitoringPoint point : MonitoringPoint.values()) {
			if (point.getSite() == site && point.getType() == MonitoringPointType.GRID && point.getName().equalsIgnoreCase(gridId)) {
				return point;
			}
		}
		return null;
	}
	
	/**
	 * Finds a probe by its site and probe ID.
	 * @param siteName The probe's <code>Site</code> enum value.
	 * @param probeId The case-insensitive ID or name of the probe.
	 * @return A probe-type <code>MonitoringPoint</code> that matches the input parameters, or <code>null</code> if no suitable probe was found. 
	 */
	public MonitoringPoint getProbeBySiteNameAndId(Site site, String probeId) {
		for (MonitoringPoint point : MonitoringPoint.values()) {
			if (point.getSite() == site && point.getType() == MonitoringPointType.PROBE && point.getName().equalsIgnoreCase(probeId)) {
				return point;
			}
		}
		return null;
	}
	
	/**
	 * Searches the <code>MonitoringPoint</code> enum and returns the set of values that match the parameters.
	 * @param type The type of monitoring point to search for.
	 * @param sites The sites to search for.
	 * @return A set of <code>MonitoringPoint</code> that fulfilled the input parameters. 
	 */
	public Set<MonitoringPoint> getMonitoringPoints(MonitoringPointType type, Site... sites) {
		Set<MonitoringPoint> result = new HashSet<>();
		if (sites.length > 0 && type != null) {
			List<Site> siteList = Arrays.asList(sites);
			for (MonitoringPoint monitoringPoint : MonitoringPoint.values()) {
				if (type == monitoringPoint.getType() && siteList.contains(monitoringPoint.getSite())) {
					result.add(monitoringPoint);
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns a string that can be used to query database fields containing <code>MonitoringPoint</code> constant names by a specific site and monitoring point type.
	 * An example of a <code>MonitoringPoint</code> constant name is <code>LC_GRID_69</code>, where <code>LC</code> is the site's short name, <code>GRID</code> is the monitoring point type, and <code>69</code> is the monitoring point name.
	 * @param site The monitoring point's site.
	 * @param type The monitoring point's type.
	 * @return A string in the format of the site's short name, followed by an underscore, followed by the monitoring point type (ie. <code>LC_GRID</code>).
	 */
	public String getSiteAndTypeQueryString(Site site, MonitoringPointType type) {
		return site.getShortName() + "_" + type.getName();
	}

}


