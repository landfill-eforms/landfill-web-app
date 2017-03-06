package org.lacitysan.landfill.server.persistence.dao.instrument;

import java.util.List;

import org.hibernate.Hibernate;
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
	public void update(InstrumentType instrumentType) {
		hibernateTemplate.update(instrumentType);
	}
	
	private InstrumentType initialize(InstrumentType instrumentType) {
		//Hibernate.initialize(instrumentType);
		return instrumentType;
	}
	
}
