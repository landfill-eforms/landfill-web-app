package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;

public interface ImeDataDao {

	List<ImeData> getAll();

	ImeData getById(Integer id);

	ImeData create(ImeData imeData);
	
	ImeData update(ImeData imeData);

	ImeData delete(ImeData imeData);

}
