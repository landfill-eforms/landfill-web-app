package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.lib.enumeration.Site;
import org.lacitysan.landfill.server.persistence.entity.MonitoringPoint;

public interface MonitoringPointsDao {

	MonitoringPoint getBySiteAndMonitoringPointName(Site site, String monitoringPointName);

	List<MonitoringPoint> getAllMonitoringPoints();

}
