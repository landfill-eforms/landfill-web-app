package org.lacitysan.landfill.server.persistence.dao.integrated;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.integrated.IntegratedData;

public interface IntegratedDataDao extends AbstractDao<IntegratedData> {

	List<IntegratedData> getBySite(String siteName);

	List<IntegratedData> getBySiteAndDate(String siteName, Long start, Long end);

}
