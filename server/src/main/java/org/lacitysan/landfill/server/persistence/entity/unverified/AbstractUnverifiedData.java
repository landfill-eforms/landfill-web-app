package org.lacitysan.landfill.server.persistence.entity.unverified;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import org.lacitysan.landfill.server.persistence.entity.AbstractEntity;

public abstract class AbstractUnverifiedData extends AbstractEntity {
	
	@Transient
	List<String> errors = new ArrayList<>();
	
	@Transient
	Boolean selected;

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	
}
