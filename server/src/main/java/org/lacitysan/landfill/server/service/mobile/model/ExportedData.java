package org.lacitysan.landfill.server.service.mobile.model;

import java.util.ArrayList;
import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.user.User;

/**
 * @author Alvin Quach
 */
public class ExportedData {
	
	List<Instrument> instruments = new ArrayList<>();
	
	List<User> users = new ArrayList<>();

	public List<Instrument> getInstruments() {
		return instruments;
	}

	public void setInstruments(List<Instrument> instruments) {
		this.instruments = instruments;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
