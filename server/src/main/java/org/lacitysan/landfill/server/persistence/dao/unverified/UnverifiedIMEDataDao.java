package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIMEData;

public interface UnverifiedIMEDataDao {

	List<UnverifiedIMEData> getAll();

	UnverifiedIMEData getById(Integer id);

	Object update(UnverifiedIMEData imeNumber);

	Object create(UnverifiedIMEData imeNumber);

}
