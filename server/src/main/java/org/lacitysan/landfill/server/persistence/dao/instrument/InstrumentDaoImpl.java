package org.lacitysan.landfill.server.persistence.dao.instrument;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class InstrumentDaoImpl implements InstrumentDao {

	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@Override
	@Transactional
	public Instrument getInstrumentById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(Instrument.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof Instrument) {
			Instrument instrument = (Instrument)result;
			return initialize(instrument);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Instrument> getAllInstruments() {
		List<Instrument> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(Instrument.class)
				.list();
		result.forEach(instrument -> initialize(instrument));
		return result;
	}
	
	@Override
	@Transactional
	public Object update(Instrument instrument) {
		hibernateTemplate.update(instrument);
		return true;
	}
	
	@Override
	@Transactional
	public Object create(Instrument instrument) {
		return hibernateTemplate.save(instrument);
	}
	
	@Override
	@Transactional
	public Object delete(Instrument instrument) {
		hibernateTemplate.delete(instrument);
		return true;
	}
	
	private Instrument initialize(Instrument instrument) {
		Hibernate.initialize(instrument.getInstrumentType());
		Hibernate.initialize(instrument.getInstrumentStatus());
		Hibernate.initialize(instrument.getSite());
		return instrument;
	}
	
}
