package org.lacitysan.landfill.server.persistence.dao.unverified;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class UnverifiedDataSetDaoImpl implements UnverifiedDataSetDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<UnverifiedDataSet> getAll() {
		List<UnverifiedDataSet> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UnverifiedDataSet.class)
				.list();
		result.stream().forEach(dataSet -> initialize(dataSet));
		return result;
	}
	
	@Override
	@Transactional
	public UnverifiedDataSet getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(UnverifiedDataSet.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result instanceof UnverifiedDataSet) {
			UnverifiedDataSet dataSet = (UnverifiedDataSet)result;
			return initialize(dataSet);
		}
		return null;
	}

	@Override
	@Transactional
	public Object update(UnverifiedDataSet unverifiedDataSet) {
		hibernateTemplate.update(unverifiedDataSet);
		return true;
	}

	@Override
	@Transactional
	public Object create(UnverifiedDataSet unverifiedDataSet) {
		return hibernateTemplate.save(unverifiedDataSet);
	}
	
	@Override
	@Transactional
	public Object delete(UnverifiedDataSet unverifiedDataSet) {
		hibernateTemplate.delete(unverifiedDataSet);
		return true;
	}
	
	private UnverifiedDataSet initialize(UnverifiedDataSet dataSet) {
		Hibernate.initialize(dataSet.getInspector().getPerson());
		Hibernate.initialize(dataSet.getUploadedBy());
		if (dataSet.getUploadedBy() != null) {
			Hibernate.initialize(dataSet.getUploadedBy().getPerson());
		}
		Hibernate.initialize(dataSet.getModifiedBy());
		if (dataSet.getModifiedBy() != null) {
			Hibernate.initialize(dataSet.getModifiedBy().getPerson());
		}
		dataSet.getUnverifiedInstantaneousData().stream().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
		});
		return dataSet;
	}

}
