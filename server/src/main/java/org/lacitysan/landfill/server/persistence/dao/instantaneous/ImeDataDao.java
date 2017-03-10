package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;

public interface ImeDataDao {

	List<ImeData> getAll();

	ImeData getById(Integer id);

	Object update(ImeData imeNumber);

	Object create(ImeData imeNumber);

}
