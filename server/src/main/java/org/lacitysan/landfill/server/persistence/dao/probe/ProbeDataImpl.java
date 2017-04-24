package org.lacitysan.landfill.server.persistence.dao.probe;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.lacitysan.landfill.server.persistence.dao.AbstractDaoImpl;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.springframework.stereotype.Repository;

/**
 * @author Alvin Quach
 */
@Repository
public class ProbeDataImpl extends AbstractDaoImpl<ProbeData> implements ProbeDataDao {

	@Override
	public List<ProbeData> getExceedances() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(ProbeData.class)
				// TODO Add criteria here.
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}
	
	@Override
	public ProbeData initialize(ProbeData probeData) {
		if (probeData == null) {
			return null;
		}
		probeData.getInspectors().forEach(inspector -> Hibernate.initialize(inspector));
		return probeData;
	}

}
