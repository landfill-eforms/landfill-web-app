package org.lacitysan.landfill.server.persistence.dao;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;

/**
 * @author Alvin Quach
 * @param <T>
 */
public interface AbstractDao<T extends AbstractEntity> {

	T getById(Integer id);
	
	List<T> getAll();
	
	T create(T entity);

	T update(T entity);

	T delete(T entity);
	
	T deleteSafe(T entity);
	
	/** 
	 * Must be able to handle <code>null</code> inputs.
	 * @param entity The entity to initialize.
	 * @return The initialized entity (can be <code>null</code>).
	 */
	T initialize(T entity);

}
