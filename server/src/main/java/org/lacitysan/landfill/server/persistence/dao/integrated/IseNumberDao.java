package org.lacitysan.landfill.server.persistence.dao.integrated;

import java.util.List;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.enums.location.Site;

public interface IseNumberDao extends AbstractDao<IseNumber> {

	List<IseNumber> getBySiteAndDateCode(Site site, Integer dateCode);

	List<IseNumber> getUnverifiedBySiteAndDateCode(Site site, Integer dateCode);

	List<IseNumber> getVerifiedBySiteAndDateCode(Site site, Integer dateCode);
	
	IseNumber getByIseNumber(IseNumber iseNumber);

}
