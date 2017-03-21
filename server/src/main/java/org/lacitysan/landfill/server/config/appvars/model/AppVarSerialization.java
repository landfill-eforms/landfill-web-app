package org.lacitysan.landfill.server.config.appvars.model;

import org.lacitysan.landfill.server.config.appvars.AppVar;

/**
 * @author Alvin Quach
 */
public class AppVarSerialization {
	
	private AppVarType type;
	private Object defaultValue;
	private Object value;
	
	public AppVarSerialization() {}
	
	public AppVarSerialization(AppVar appVar) {
		this.type = appVar.getType();
		this.defaultValue = appVar.getDefaultValue();
		this.value = appVar.getValue();
	}
	
	public AppVarType getType() {
		return type;
	}
	
	public void setType(AppVarType type) {
		this.type = type;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
