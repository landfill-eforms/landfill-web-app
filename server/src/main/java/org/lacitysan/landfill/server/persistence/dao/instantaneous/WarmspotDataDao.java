package org.lacitysan.landfill.server.persistence.dao.instantaneous;

import java.util.List;

import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;

public interface WarmspotDataDao {

	List<WarmspotData> getAllWarmspots();

	WarmspotData getWarmspotById(Integer id);

	WarmspotData create(WarmspotData warmspotData);
	
	WarmspotData update(WarmspotData warmspotData);

	WarmspotData delete(WarmspotData warmspotData);

}
