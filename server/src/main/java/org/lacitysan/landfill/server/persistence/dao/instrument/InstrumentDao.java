package org.lacitysan.landfill.server.persistence.dao.instrument;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;

/**
 * @author Alvin Quach
 */
public interface InstrumentDao {

	Instrument getInstrumentById(Integer id);
	
	List<Instrument> getAllInstruments();

	Object update(Instrument instrument);

	Object create(Instrument instrument);

	Object delete(Instrument instrument);

}
