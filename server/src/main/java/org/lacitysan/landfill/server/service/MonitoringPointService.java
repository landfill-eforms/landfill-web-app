package org.lacitysan.landfill.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.model.util.IntegerRange;
import org.springframework.stereotype.Service;

/**
 * Handles the logical operations for both the <code>Site</code> and <code>MonitoringPoint</code> enums.
 * @author Alvin Quach
 */
@Service
public class MonitoringPointService {
	
	/**
	 * Finds a site by its short name.
	 * @param shortName The case-insensitive short name of the site.
	 * @return A <code>Site</code> that matches the given short name, or <code>null</code> if no suitable site was found.
	 */
	public Site getSiteByShortName(String shortName) {
		for (Site site : Site.values()) {
			if (site.getShortName().equalsIgnoreCase(shortName)) {
				return site;
			}
		}
		return null;
	}
	
	/**
	 * Finds a site by its name.
	 * @param siteName The case-insensitive name of the site.
	 * @return A <code>Site</code> that matches the given short name, or <code>null</code> if no suitable site was found.
	 */
	public Site getSiteByName(String siteName) {
		for (Site site : Site.values()) {
			if (site.getName().equalsIgnoreCase(siteName)) {
				return site;
			}
		}
		return null;
	}
	
	/**
	 * Finds a grid by its site and grid ID.
	 * @param siteName The grids <code>Site</code> enum value.
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
	 * Finds a random monitoring point that matches the parameters.
	 * @param type The type of monitoring point to search for.
	 * @param sites The sites to search for.
	 * @return A random <code>MonitoringPoint</code> that matches the input parameters, or <code>null</code> if no suitable monitoring point was found.  
	 */
	public MonitoringPoint getRandom(MonitoringPointType type, Site... sites) {
		List<IntegerRange> ranges = getRanges(type, sites);
		for (int i = 0; i < ranges.size(); i++) {
			if (i < ranges.size() - 1 && Math.random() > 1.0 / ranges.size()) {
				continue;
			}
			IntegerRange range = ranges.get(i);
			double index =  Math.random() * (range.getMax() - range.getMin()) + range.getMin();
			return MonitoringPoint.values()[(int)index];
		}
		return null;
	}
	
	/**
	 * Searches the <code>MonitoringPoint</code> enum and returns the ordinal ranges of the values that match the parameters.
	 * @param type The type of monitoring point to search for.
	 * @param sites The sites to search for.
	 * @return A list containing the ranges of the enum value ordinals that fulfilled the input parameters. 
	 */
	public List<IntegerRange> getRanges(MonitoringPointType type, Site... sites) {
		List<IntegerRange> result = new ArrayList<>();
		if (sites.length > 0 && type != null) {
			int rangeStart = -1;
			int rangeEnd = -1;
			boolean rangeActive = false;
			List<Site> siteList = Arrays.asList(sites);
			for (MonitoringPoint monitoringPoint : MonitoringPoint.values()) {
				if (type == monitoringPoint.getType() && siteList.contains(monitoringPoint.getSite())) {
					if (rangeActive) {
						rangeEnd++;
					}
					else {
						rangeEnd = rangeStart = monitoringPoint.ordinal();
						rangeActive = true;
					}
				}
				else if (rangeActive) {
					result.add(new IntegerRange(rangeStart, rangeEnd));
					System.out.println("Range found at (" + rangeStart + ", " + rangeEnd + ").");
					rangeActive = false;
				}
			}
		}
		return result;
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

}


