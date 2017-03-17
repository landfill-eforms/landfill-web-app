package org.lacitysan.landfill.server.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alvin Quach
 * @param <T>
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {
	
	@Autowired
	HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public T getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.idEq(id))
				.uniqueResult();
		if (result != null) {
			return initialize((T)result);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<T> getAll() {
		List<T> result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.list();
		result.forEach(entity -> initialize(entity));
		return result;
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
		return entity;
	}

	@Override
	@Transactional
	public T delete(T entity) {
		hibernateTemplate.delete(entity);
		return entity;
	}
	
	private Class<?> getGenericClass() {
		return (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
