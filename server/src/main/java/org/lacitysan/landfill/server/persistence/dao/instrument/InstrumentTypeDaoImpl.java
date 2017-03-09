package org.lacitysan.landfill.server.persistence.dao.instrument;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class InstrumentTypeDaoImpl implements InstrumentTypeDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public InstrumentType getInstrumentTypeById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(InstrumentType.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof InstrumentType) {
			InstrumentType instrumentType = (InstrumentType)result;
			return initialize(instrumentType);
		}
		return null;
	}	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<InstrumentType> getAllInstrumentTypes() {
		List<InstrumentType> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(InstrumentType.class)
				.list();
		result.stream().forEach(instrumentType -> initialize(instrumentType));
		return result;
	}

	@Override
	@Transactional
	public Object update(InstrumentType instrumentType) {
		hibernateTemplate.update(instrumentType);
		return true;
	}

	@Override
	@Transactional
	public Object create(InstrumentType instrumentType) {
		return hibernateTemplate.save(instrumentType);
	}

	@Override
	@Transactional
	public Object delete(InstrumentType instrumentType) {
		hibernateTemplate.delete(instrumentType);
		return true;
	}

	private InstrumentType initialize(InstrumentType instrumentType) {
		instrumentType.getInstruments().stream().forEach(instrument -> {
			Hibernate.initialize(instrument.getInstrumentType());
			Hibernate.initialize(instrument.getInstrumentStatus());
			Hibernate.initialize(instrument.getSite());
		});
		return instrumentType;
	}

}
