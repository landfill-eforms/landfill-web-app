package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;

public interface UnverifiedDataSetDao {

	List<UnverifiedDataSet> getAll();

	UnverifiedDataSet getById(Integer id);
	
	Object update(UnverifiedDataSet unverifiedDataSet);

	Object create(UnverifiedDataSet unverifiedDataSet);

	Object delete(UnverifiedDataSet unverifiedDataSet);

}
