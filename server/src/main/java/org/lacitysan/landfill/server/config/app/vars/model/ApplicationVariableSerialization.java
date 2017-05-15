package org.lacitysan.landfill.server.config.app.vars.model;

import org.lacitysan.landfill.server.config.app.vars.ApplicationVariableDefinition;

/**
 * The class that individual application variables serialize to when they are received as part of a JSON.
 * @author Alvin Quach
 */
public class ApplicationVariableSerialization {
	
	private ApplicationVariableType type;
	private Object defaultValue;
	private Object value;
	
	public ApplicationVariableSerialization() {}
	
	public ApplicationVariableSerialization(ApplicationVariableDefinition appVar) {
		this.type = appVar.getType();
		this.defaultValue = appVar.getDefaultValue();
		this.value = appVar.getValue();
	}
	
	public ApplicationVariableType getType() {
		return type;
	}
	
	public void setType(ApplicationVariableType type) {
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
