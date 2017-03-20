package org.lacitysan.landfill.server.config.appvars.model;

public class StringVar extends Var<String> {

	public StringVar(String key, String defaultValue) {
		super(key, defaultValue);
	}

	public StringVar(String key, String defaultValue, String value) {
		super(key, defaultValue, value);
	}

}
