package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;

/**
 * @author Alvin Quach
 */
public interface InstantaneousDataDao {

	List<InstantaneousData> getBySite(String siteName);

}
