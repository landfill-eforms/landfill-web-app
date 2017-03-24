package org.lacitysan.landfill.server.persistence.dao.instrument;

import org.lacitysan.landfill.server.persistence.dao.AbstractDao;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;

/**
 * @author Alvin Quach
 */
public interface InstrumentDao extends AbstractDao<Instrument> {

	Instrument getByTypeAndSerialNumber(InstrumentType instrumentType, String serialNumber);
	
}
