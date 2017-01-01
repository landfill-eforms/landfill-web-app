package org.lacitysan.landfill.server.persistence.dao.test;

import org.lacitysan.landfill.server.persistence.entities.test.Sleep;
import org.lacitysan.landfill.server.persistence.entities.test.Test;

public interface SleepTestDao {

	Sleep getSleepById(Integer id);

	Test getTestById(Integer id);

}
