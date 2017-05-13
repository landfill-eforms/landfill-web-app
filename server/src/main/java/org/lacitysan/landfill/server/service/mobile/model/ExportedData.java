package org.lacitysan.landfill.server.service.mobile.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alvin Quach
 */
@JsonInclude(Include.NON_NULL)
public class ExportedData {
	
	List<Object> instruments = new ArrayList<>();
	
	List<Object> users = new ArrayList<>();

	public List<Object> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Object> instruments) {
		this.instruments = instruments;
	}

	public List<Object> getUsers() {
		return users;
	}

	public void setUsers(List<Object> users) {
		this.users = users;
	}

}
