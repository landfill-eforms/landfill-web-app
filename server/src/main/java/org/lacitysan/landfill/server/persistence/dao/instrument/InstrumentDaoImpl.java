package org.lacitysan.landfill.server.persistence.dao.instrument;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implemented data access object for <code>Instrument</code> entities.
 * @author Alvin Quach
 */
@Repository
public class InstrumentDaoImpl extends AbstractDaoImpl<Instrument> implements InstrumentDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	/** 
	 * Retrieves an <code>Instrument</code> from the database that matches the given <code>InstrumentType</code> and serial number.
	 * Will return <code>null</code> if any of the parametsr are <code>null</code>.
	 * @param instrumentType The <code>InstrumentType</code> to search for.
	 * @param serialNumber The serial number to search for.
	 * @return <code>Instrument</code> from the database that matches the given <code>InstrumentType</code> and serial number, 
	 * 		   or <code>null</code> if no <code>Instrument</code> was found or if any of the parameters was <code>null</code>.
	 */
	@Override
	@Transactional
	public Instrument getByTypeAndSerialNumber(InstrumentType instrumentType, String serialNumber) {
		if (instrumentType == null || serialNumber == null) {
			return null;
		}
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
