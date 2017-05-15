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
 * Implemented data access object for <code>AbstractEntity</code> entities.
 * Provides common database operations such as retrieving, updating, and deleting entities.
 * @author Alvin Quach
 * @param <T> extends <code>AbstractEntity</code>
 */
public abstract class AbstractDaoImpl<T extends AbstractEntity> implements AbstractDao<T> {

	@Autowired
	protected HibernateTemplate hibernateTemplate;

	/** 
	 * Retrieves an entity from the database that matches the given id.
	 * <br></br>
	 * Will return <code>null</code> if the specified id is <code>null</code>.
	 * @param id The id to search for.
	 * @param serialNumber The serial number to search for.
	 * @return Entity from the database that matches the given id, or <code>null</code> if no entity was found or if the specified id was <code>null</code>.
	 */
	@Override
	@Transactional
	public T getById(Integer id) {
		if (id == null) {
			return null;
		}
		Object result = hibernateTemplate.getSessionFactory().getCurrentSession()
				.createCriteria(getGenericClass())
				.add(Restrictions.idEq(id))
				.uniqueResult();
		return initialize(checkType(result));
	}

	/** 
	 * Retrieves a list of entites from the database that is of the correct generic type.
	 * @return The list of entites from the database that is of the correct generic type.
	 */
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

	/** 
	 * Inserts an entity into the database.
	 * <br></br>
	 * Will return <code>null</code> if the entity is <code>null</code>.
	 * @return Entity that was inserted into the database, with the id updated.
	 */
	@Override
	@Transactional
	public T create(T entity) {
		if (entity == null) {
			return null;
		}
		hibernateTemplate.save(entity);
		return entity;
	}

	/** 
	 * Updates the entity in the database.
	 * <br></br>
	 * Will return <code>null</code> if the entity is <code>null</code>.
	 * @return Updated version of the entity.
	 */
	@Override
	@Transactional
	public T update(T entity) {
		if (entity == null) {
			return null;
		}
		hibernateTemplate.update(entity);
		hibernateTemplate.getSessionFactory().getCurrentSession().flush();
		hibernateTemplate.getSessionFactory().getCurrentSession().clear();
		return getById(entity.getId());
	}

	/** 
	 * Deletes an entity from the database.
	 * Will return <code>null</code> if the entity is <code>null</code>.
	 * @return The deleted entity.
	 */
	@Override
	@Transactional
	public T delete(T entity) {
		if (entity == null) {
			return null;
		}
		hibernateTemplate.delete(entity);
		return entity;
	}
	
	/** 
	 * Deletes an entity from the database.
	 * This method is slower than the <code>delete()</code> method,
	 * but can be used in some cases where the <code>delete()</code> method results in errors.
	 * <br></br>
	 * Will return <code>null</code> if the entity is <code>null</code>.
	 * @return The deleted entity.
	 */
	@Override
	@Transactional
	public T deleteSafe(T entity) {
		if (entity == null) {
			return null;
		}
		entity = getById(entity.getId()); // Retrieve a copy of the entity from the database before trying to delete it.
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

	/**
	 * Gets the generic class of the implementing DAO.
	 * @return The generic class of the implementing DAO
	 */
	protected Class<?> getGenericClass() {
		return (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
