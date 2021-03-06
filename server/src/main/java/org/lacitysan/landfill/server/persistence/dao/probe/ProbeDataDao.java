package org.lacitysan.landfill.server.persistence.dao.probe;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;

/**
 * Data access object for <code>ProbeData</code> entities.
 * @author Alvin Quach
 */
public interface ProbeDataDao extends AbstractDao<ProbeData> {
	
	public List<ProbeData> getExceedances();

}
