package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;

public interface ImeNumberDao {

	List<ImeNumber> getAll();
	
	List<ImeNumber> getBySite(String siteName);

	ImeNumber getByImeNumber(String imeNumber);
	
	Object update(ImeNumber imeNumber);

	Object create(ImeNumber imeNumber);

}
