package org.lacitysan.landfill.server.persistence.dao.instrument;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;

/**
 * @author aquach
 */
public interface InstrumentTypeDao {

	List<InstrumentType> getAllInstrumentTypes();

	void update(InstrumentType instrument);

}
