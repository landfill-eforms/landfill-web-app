package org.lacitysan.landfill.server.config.appvars;

import org.lacitysan.landfill.server.config.appvars.model.Var;

public enum AppVar {
	
	USERNAME_MIN_LENGTH (new Var<Integer>("usernameMinLength", 4));
	
	private Var<?> var;
	
	private AppVar(Var<?> var) {
		this.var = var;
	}

	public Var<?> getVar() {
		return var;
	}

}
