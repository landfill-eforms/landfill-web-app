package org.lacitysan.landfill.server.persistence.dao.instrument;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.stereotype.Repository;

/**
 * Implemented data access object for <code>InstrumentType</code> entities.
 * @author aquach
 */
@Repository
public class InstrumentTypeDaoImpl extends AbstractDaoImpl<InstrumentType> implements InstrumentTypeDao {

	@Override
	public InstrumentType initialize(InstrumentType instrumentType) {
		if (instrumentType == null) {
			return null;
		}
		instrumentType.getInstruments().forEach(instrument -> {
			Hibernate.initialize(instrument.getInstrumentType());
			Hibernate.initialize(instrument.getInstrumentStatus());
			Hibernate.initialize(instrument.getSite());
		});
		return instrumentType;
	}

}
