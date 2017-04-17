package org.lacitysan.landfill.server.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

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
	protected HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public T getById(Integer id) {
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.idEq(id))
				.uniqueResult();
		return checkType(result);
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
		return entity;
	}

	@Override
	@Transactional
	public T delete(T entity) {
		hibernateTemplate.delete(entity);
		return entity;
	}

	@SuppressWarnings("unchecked")
	protected T checkType(Object entity) {
		if (getGenericClass().isInstance(entity)) {
			return (T)entity;
		}
		return null;
	}

	private Class<?> getGenericClass() {
		return (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
