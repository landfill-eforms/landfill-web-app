package com.landfill_eforms.server.persistence.dao.test;

import com.landfill_eforms.server.persistence.entities.test.Sleep;
import com.landfill_eforms.server.persistence.entities.test.Test;

public interface SleepTestDao {

	Sleep getSleepById(Integer id);

	Test getTestById(Integer id);

}
