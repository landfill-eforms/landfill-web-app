package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;

public interface WarmspotDataDao {

	List<WarmspotData> getAllWarmspots();

	WarmspotData getWarmspotById(Integer id);

	Object update(WarmspotData warmspotData);

	Object create(WarmspotData warmspotData);

	Object delete(WarmspotData warmspotData);

}
