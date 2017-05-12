package org.lacitysan.landfill.server.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.criterion.Restrictions;
import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 */
public abstract class AbstractDaoImpl<T extends AbstractEntity> implements AbstractDao<T> {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public T getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.idEq(id))
				.uniqueResult();
		return initialize(checkType(result));
	}

	@Override
	@Transactional
	public List<T> getAll() {
		List<?> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.list();
		return result.stream()
				.map(e -> initialize(checkType(e)))
				.filter(e -> e != null)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public T create(T entity) {
		hibernateTemplate.save(entity);
		return entity;
	}

	@Override
	@Transactional
	public T update(T entity) {
		hibernateTemplate.update(entity);
		hibernateTemplate.getSessionFactory().getCurrentSession().flush();
		hibernateTemplate.getSessionFactory().getCurrentSession().clear();
		return getById(entity.getId());
	}

	@Override
	@Transactional
	public T delete(T entity) {
		hibernateTemplate.delete(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public T deleteSafe(T entity) {
		entity = getById(entity.getId());
		hibernateTemplate.delete(entity);
		return entity;
	}

	/**
	 * Checks if an object is an instance of this data access object's generic inner class.
	 * @param entity The object to check.
	 * @return The object typecasted to the generic inner class, or <code>null</code> if there is a type mismatch.
	 */
	@SuppressWarnings("unchecked")
	protected T checkType(Object entity) {
		if (getGenericClass().isInstance(entity)) {
			return (T)entity;
		}
		return null;
	}

	protected Class<?> getGenericClass() {
		return (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
