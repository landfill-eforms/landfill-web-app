package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;

public interface ImeNumberDao {

	List<ImeNumber> getAll();
	
	List<ImeNumber> getBySiteName(String siteName);

	ImeNumber getByImeNumber(ImeNumber imeNumber);
	
	List<ImeNumber> getBySiteAndDateCode(Site site, Integer dateCode);
	
	ImeNumber create(ImeNumber imeNumber);
	
	ImeNumber update(ImeNumber imeNumber);

	ImeNumber delete(ImeNumber imeNumber);

}
