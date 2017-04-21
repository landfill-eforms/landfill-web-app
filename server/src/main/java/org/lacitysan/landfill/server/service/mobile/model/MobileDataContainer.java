package org.lacitysan.landfill.server.service.mobile.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Alvin Quach
 */
public class MobileDataContainer {

	private String filename;

	private Set<MobileImeData> mImeDatas = new HashSet<>();

	private Set<MobileInstantaneousData> mInstantaneousDatas = new HashSet<>();

	private Set<MobileIntegratedData> mIntegratedDatas = new HashSet<>();

	private Set<MobileIseData> mIseDatas = new HashSet<>();
	
	private Set<MobileProbeData> mProbeDatas = new HashSet<>();

	private Set<MobileWarmspotData> mWarmSpotDatas = new HashSet<>();

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

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

	public Set<MobileIntegratedData> getmIntegratedDatas() {
		return mIntegratedDatas;
	}

	public void setmIntegratedDatas(Set<MobileIntegratedData> mIntegratedDatas) {
		this.mIntegratedDatas = mIntegratedDatas;
	}

	public Set<MobileIseData> getmIseDatas() {
		return mIseDatas;
	}

	public void setmIseDatas(Set<MobileIseData> mIseDatas) {
		this.mIseDatas = mIseDatas;
	}

	public Set<MobileProbeData> getmProbeDatas() {
		return mProbeDatas;
	}

	public void setmProbeDatas(Set<MobileProbeData> mProbeDatas) {
		this.mProbeDatas = mProbeDatas;
	}

	public Set<MobileWarmspotData> getmWarmSpotDatas() {
		return mWarmSpotDatas;
	}

	public void setmWarmSpotDatas(Set<MobileWarmspotData> mWarmSpotDatas) {
		this.mWarmSpotDatas = mWarmSpotDatas;
	}

}