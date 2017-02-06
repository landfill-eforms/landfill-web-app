package org.lacitysan.landfill.server.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.lacitysan.landfill.server.model.MonitoringPoint;
import org.lacitysan.landfill.server.model.MonitoringPointType;
import org.lacitysan.landfill.server.model.Site;
import org.lacitysan.landfill.server.service.model.OrdinalRange;
import org.springframework.stereotype.Service;

/**
 * @author Alvin Quach
 */
@Service
public class MonitoringPointService {

	public List<OrdinalRange> getRanges(Collection<Site> sites, Collection<MonitoringPointType> types) {
		List<OrdinalRange> result = new ArrayList<>();
		if (sites != null && types != null && !sites.isEmpty() && !types.isEmpty()) {
			int rangeStart = -1;
			int rangeEnd = -1;
			boolean rangeActive = false;
			for (MonitoringPoint monitoringPoint : MonitoringPoint.values()) {
				if (sites.contains(monitoringPoint.getSite()) && types.contains(monitoringPoint.getType())) {
					if (rangeActive) {
						rangeEnd++;
					}
					else {
						rangeEnd = rangeStart = monitoringPoint.ordinal();
						rangeActive = true;
					}
				}
				else if (rangeActive) {
					result.add(new OrdinalRange(rangeStart, rangeEnd));
					System.out.println("Range found at (" + rangeStart + ", " + rangeEnd + ").");
					rangeActive = false;
				}
			}
		}
		return result;
	}

	public List<OrdinalRange> getGridsBySite(Site site) {
		List<Site> sites = new ArrayList<>();
		sites.add(site);
		List<MonitoringPointType> types = new ArrayList<>();
		types.add(MonitoringPointType.GRID);
		return getRanges(sites, types);
	}

}


