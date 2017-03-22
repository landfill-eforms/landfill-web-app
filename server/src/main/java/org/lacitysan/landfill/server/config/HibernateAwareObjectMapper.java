package org.lacitysan.landfill.server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class HibernateAwareObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 3578418410263867055L;

	public HibernateAwareObjectMapper() {
		registerModule(new Hibernate5Module());
	}

}
