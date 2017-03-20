package org.lacitysan.landfill.server.config.appvars.model;

import java.lang.reflect.ParameterizedType;

/**
 * @author Alvin Quach
 * @param <T>
 */
public class Var<T> {
	
	private String key;
	private T defaultValue;
	private T value;
	
	public Var(String key, T defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.value = defaultValue;
	}
	
	public Var(String key, T defaultValue, T value) {
		this.key = key;
		this.defaultValue = defaultValue;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

//	public void setKey(String key) {
//		this.key = key;
//	}

	public T getDefaultValue() {
		return defaultValue;
	}

//	public void setDefaultValue(T defaultValue) {
//		this.defaultValue = defaultValue;
//	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public Class<?> getType() {
		return (Class<?>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
