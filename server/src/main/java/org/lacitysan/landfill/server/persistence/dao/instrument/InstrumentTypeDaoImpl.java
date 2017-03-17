package org.lacitysan.landfill.server.persistence.dao.instrument;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class InstrumentTypeDaoImpl extends AbstractDaoImpl<InstrumentType> implements InstrumentTypeDao {

	public InstrumentType initialize(InstrumentType instrumentType) {
		instrumentType.getInstruments().forEach(instrument -> {
			Hibernate.initialize(instrument.getInstrumentType());
			Hibernate.initialize(instrument.getInstrumentStatus());
			Hibernate.initialize(instrument.getSite());
		});
		return instrumentType;
	}

}
