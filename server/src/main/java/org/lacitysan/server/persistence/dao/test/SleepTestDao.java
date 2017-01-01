package org.lacitysan.server.persistence.dao.test;

import org.lacitysan.server.persistence.entities.test.Sleep;
import org.lacitysan.server.persistence.entities.test.Test;

public interface SleepTestDao {

	Sleep getSleepById(Integer id);

	Test getTestById(Integer id);

}
