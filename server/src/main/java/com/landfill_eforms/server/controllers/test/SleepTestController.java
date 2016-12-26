package com.landfill_eforms.server.controllers.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.landfill_eforms.server.dao.test.SleepTestDao;

/**
 * For testing purposes.
 * @author Alvin Quach
 */
@RequestMapping("rest/test")
@RestController
public class SleepTestController {

	@Autowired
	SleepTestDao sleepTestDao;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Object get(@PathVariable Integer id) {
		return sleepTestDao.getSleepById(id);
	}
	
}
