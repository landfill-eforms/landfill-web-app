package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedImeData;

public interface UnverifiedImeDataDao {

	List<UnverifiedImeData> getAll();

	UnverifiedImeData getById(Integer id);

	Object update(UnverifiedImeData imeNumber);

	Object create(UnverifiedImeData imeNumber);

}
