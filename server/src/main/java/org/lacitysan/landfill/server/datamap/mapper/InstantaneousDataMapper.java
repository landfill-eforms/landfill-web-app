package org.lacitysan.landfill.server.datamap.mapper;

import org.lacitysan.landfill.server.datamap.entity.InstantaneousDataMapped;
import org.lacitysan.landfill.server.persistence.entity.InstantaneousData;

/**
 * @author Alvin Quach
 */
public class InstantaneousDataMapper {

	/**
	 * Maps an <code>InstantaneousData</code> object into an <code>InstantaneousDataMapped</code> object.
	 * @param entity The <code>InstantaneousData</code> object to be mapped.
	 * @return The <code>InstantaneousDataMapped</code> representation of the input object.
	 */
	public static InstantaneousDataMapped map(InstantaneousData entity) {
		InstantaneousDataMapped result = new InstantaneousDataMapped();
		// TODO Implement logic
		return result;
	}

	/**
	 * Unmaps a <code>InstantaneousDataMapped</code> object into an <code>InstantaneousData</code> object.
	 * Currently, the user, monitoring point, and instrument data are not mapped, and will be <code>null</code> in the resulting output.
	 * @param entity The <code>InstantaneousDataMapped</code> object to be unmapped.
	 * @return The <code>InstantaneousData</code> representation of the input object.
	 */
	public static InstantaneousData unmap(InstantaneousDataMapped entity) {
		InstantaneousData result = new InstantaneousData();
		// TODO Implement logic
		return result;
	}

}
