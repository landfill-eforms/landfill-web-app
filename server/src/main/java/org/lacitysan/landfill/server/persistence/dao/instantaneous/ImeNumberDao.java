package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;

public interface ImeNumberDao extends AbstractDao<ImeNumber> {

	List<ImeNumber> getBySiteName(String siteName);

	List<ImeNumber> getBySiteAndDateCode(Site site, Integer dateCode);
	
	List<ImeNumber> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode);
	
	List<ImeNumber> getVerifiedBySiteAndDateCode(Site site, Integer dateCode);
	
	ImeNumber getByImeNumber(ImeNumber imeNumber);

}
