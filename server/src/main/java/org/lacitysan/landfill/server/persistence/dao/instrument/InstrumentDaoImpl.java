package org.lacitysan.landfill.server.persistence.dao.instrument;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class InstrumentDaoImpl extends AbstractDaoImpl<Instrument> implements InstrumentDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	public Instrument getByTypeAndSerialNumber(InstrumentType instrumentType, String serialNumber) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(Instrument.class)
				.add(Restrictions.eq("instrumentType", instrumentType))
				.add(Restrictions.eq("serialNumber", serialNumber).ignoreCase()) // The ignoreCase() is not actually needed when working with SQL Server.
				.uniqueResult();
		return initialize(checkType(result));
	}

	@Override
	public Instrument initialize(Instrument instrument) {
		if (instrument == null) {
			return null;
		}
		Hibernate.initialize(instrument.getInstrumentType());
		Hibernate.initialize(instrument.getInstrumentStatus());
		Hibernate.initialize(instrument.getSite());
		return instrument;
	}

}
