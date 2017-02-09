package org.lacitysan.landfill.server.persistence.dao.unverified;

import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;

public interface UnverifiedDataSetsDao {

	Object update(UnverifiedDataSet unverifiedDataSet);

	Object create(UnverifiedDataSet unverifiedDataSet);

}
