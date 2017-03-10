package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.enums.Site;
import org.lacitysan.landfill.server.service.ImeService;
import org.lacitysan.landfill.server.service.MonitoringPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
@Repository
public class ImeNumberDaoImpl implements ImeNumberDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	ImeService imeService;

	@Autowired
	MonitoringPointService monitoringPointService;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getAll() {
		List<ImeNumber> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ImeNumber.class)
				.list();
		result.stream().forEach(imeNumber -> {
			initialize(imeNumber);
		});
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getBySiteName(String siteName) {
		List<ImeNumber> result = new ArrayList<>();
		Site site = Site.valueOf(siteName);
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", Site.valueOf(siteName)))
					.list();
			result.stream().forEach(imeNumber -> {
				initialize(imeNumber);
			});
			return result;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ImeNumber> getBySiteAndDateCode(Site site, Integer dateCode) {
		List<ImeNumber> result = new ArrayList<>();
		if (site != null) {
			result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", site))
					.add(Restrictions.eq("dateCode", dateCode))
					.list();
			result.stream().forEach(imeNumber -> {
				initialize(imeNumber);
			});
			return result;
		}
		return null;
	}

	@Override
	@Transactional
	public ImeNumber getByImeNumber(String imeNumber) {
		ImeNumber temp = imeService.getImeNumberFromString(imeNumber);
		if (temp != null) {
			Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
					.createCriteria(ImeNumber.class)
					.add(Restrictions.eq("site", temp.getSite()))
					.add(Restrictions.eq("dateCode", temp.getDateCode()))
					.add(Restrictions.eq("sequence", temp.getSequence()))
					.uniqueResult();
			if (result instanceof ImeNumber) {
				return initialize((ImeNumber)result);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Object update(ImeNumber imeNumber) {
		
		// TODO MOVE THIS
		for (ImeData data : imeNumber.getImeData() ) {
			data.setImeNumber(imeNumber);
		}
		
		hibernateTemplate.update(imeNumber);
		return true;
	}

	@Override
	@Transactional
	public Object create(ImeNumber imeNumber) {
		return hibernateTemplate.save(imeNumber);
	}
	
	
	// TODO Move this to a service.
	@Override
	@Transactional
	public short getNextSequence(Site site, Integer dateCode) {
		
		// Use TreeSet to store existing IME Numbers so that they are in order by sequence number.
		Set<ImeNumber> existing = new TreeSet<>(); 
		existing.addAll(getBySiteAndDateCode(site, dateCode));
		
		short last = 1;
		for (ImeNumber imeNumber : existing) {
			if (imeNumber.getSequence() > ++last) {
				break;
			}
		}
		return last;
	}

	private ImeNumber initialize(ImeNumber imeNumber) {
		Hibernate.initialize(imeNumber.getMonitoringPoints());
		imeNumber.getInstantaneousData().stream().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
			instantaneousData.getImeNumbers().stream().forEach(i -> Hibernate.initialize(i));
			instantaneousData.getWarmspotData().stream().forEach(w -> Hibernate.initialize(w));
		});
		imeNumber.getUnverifiedInstantaneousData().stream().forEach(instantaneousData -> {
			Hibernate.initialize(instantaneousData.getInstrument());
			instantaneousData.getImeNumbers().stream().forEach(i -> Hibernate.initialize(i));
			instantaneousData.getWarmspotData().stream().forEach(w -> Hibernate.initialize(w));
		});
		Hibernate.initialize(imeNumber.getImeData());
		return imeNumber;
	}

}
