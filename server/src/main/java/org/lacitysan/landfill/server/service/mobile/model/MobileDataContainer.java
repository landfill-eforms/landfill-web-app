package org.lacitysan.landfill.server.service.mobile.model;

import java.util.HashSet;
import java.util.Set;

public class MobileDataContainer {
	
	private Set<MobileImeData> mImeDatas = new HashSet<>();
	
	private Set<MobileInstantaneousData> mInstantaneousDatas = new HashSet<>();
	
	private Set<MobileWarmspotData> mWarmSpotDatas = new HashSet<>();

	private String filename;
	
	public Set<MobileImeData> getmImeDatas() {
		return mImeDatas;
	}

	public void setmImeDatas(Set<MobileImeData> mImeDatas) {
		this.mImeDatas = mImeDatas;
	}

	public Set<MobileInstantaneousData> getmInstantaneousDatas() {
		return mInstantaneousDatas;
	}

	public void setmInstantaneousDatas(Set<MobileInstantaneousData> mInstantaneousDatas) {
		this.mInstantaneousDatas = mInstantaneousDatas;
	}

	public Set<MobileWarmspotData> getmWarmSpotDatas() {
		return mWarmSpotDatas;
	}

	public void setmWarmSpotDatas(Set<MobileWarmspotData> mWarmSpotDatas) {
		this.mWarmSpotDatas = mWarmSpotDatas;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}