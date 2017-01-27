package org.lacitysan.landfill.server.service.datamap.mapper;

import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;
import org.lacitysan.landfill.server.service.datamap.model.mobile.InstantaneousDataMobile;

/**
 * A utilities class for converting data models between the web server and the mobile app.
 * @author Alvin Quach
 */
public class MobileDataMapper {

	/**
	 * Maps an <code>InstantaneousData</code> object into an <code>InstantaneousDataMobile</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMobile</code> representation of the input object.
	 */
	public static InstantaneousDataMobile mapInstantaneousData(InstantaneousData entity) {
		InstantaneousDataMobile result = new InstantaneousDataMobile();
		// TODO Implement logic
		return result;
	}

	/**
	 * Unmaps a <code>InstantaneousDataMobile</code> object into an <code>InstantaneousData</code> object.
	 * Currently, the user, monitoring point, and instrument data are not mapped, and will be <code>null</code> in the resulting output.
	 * @param entity The <code>InstantaneousDataMobile</code> object to be unmapped.
	 * @return The <code>InstantaneousData</code> representation of the input object.
	 */
	public static InstantaneousData unmapInstantaneousData(InstantaneousDataMobile entity) {
		InstantaneousData result = new InstantaneousData();
		// TODO Implement logic
		return result;
	}

}
