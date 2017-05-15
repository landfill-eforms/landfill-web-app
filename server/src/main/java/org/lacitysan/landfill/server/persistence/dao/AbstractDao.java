package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;

/**
 * Data access object for <code>AbstractEntity</code> entities.
 * @author Alvin Quach
 * @param <T> extends <code>AbstractEntity</code>
 */
public interface AbstractDao<T extends AbstractEntity> {

	T getById(Integer id);
	
	List<T> getAll();
	
	T create(T entity);

	T update(T entity);

	T delete(T entity);
	
	T deleteSafe(T entity);
	
	/** 
	 * Initializes an entity retrieved from the database by Hibernate.
	 * <br></br>
	 * Implementations must be able to handle <code>null</code> inputs.
	 * @param entity The entity to initialize.
	 * @return The initialized entity (can be <code>null</code>).
	 */
	T initialize(T entity);

}
