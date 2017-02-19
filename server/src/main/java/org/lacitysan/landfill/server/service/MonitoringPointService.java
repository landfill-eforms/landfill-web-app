package org.lacitysan.landfill.server.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.model.util.IntegerRange;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class MonitoringPointService {

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
	 * @return A list containing the ranges of the enum value ordinals that matched the input parameters. 
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

}


