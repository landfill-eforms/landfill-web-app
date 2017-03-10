package org.lacitysan.landfill.server.service.datamap.mapper;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.service.datamap.model.mobile.MobileInstantaneousData;
import org.springframework.stereotype.Service;

/**
 * Serializer service for converting data models from the web server to the mobile app.
 * @author Alvin Quach
 */
@Service
public class MobileDataSerializer {

	/**
	 * Serializes an <code>InstantaneousData</code> object into an <code>InstantaneousDataMobile</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMobile</code> representation of the input object.
	 */
	public MobileInstantaneousData mapInstantaneousData(InstantaneousData entity) {
		MobileInstantaneousData result = new MobileInstantaneousData();
		// TODO Implement logic
		return result;
	}

}
