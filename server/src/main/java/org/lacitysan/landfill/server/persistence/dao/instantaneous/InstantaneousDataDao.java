package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

/**
 * @author Alvin Quach
 */
public interface InstantaneousDataDao extends AbstractDao<InstantaneousData> {

	List<InstantaneousData> getBySite(Site site);

	List<InstantaneousData> getBySiteAndDate(Site site, Long start, Long end);

}