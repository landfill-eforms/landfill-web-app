package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMENumber;

public interface IMENumbersDao {

	List<IMENumber> getAll();

	IMENumber getById(Integer id);

	Object update(IMENumber imeNumber);

	Object create(IMENumber imeNumber);

}
