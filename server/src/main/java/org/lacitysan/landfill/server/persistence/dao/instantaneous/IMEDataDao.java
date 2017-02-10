package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.IMEData;

public interface IMEDataDao {

	List<IMEData> getAll();

	IMEData getById(Integer id);

	Object update(IMEData imeNumber);

	Object create(IMEData imeNumber);

}
