package org.lacitysan.landfill.server.service.datamap.model.mobile;

/**
 * @author Alvin Quach
 */
public class InstantaneousDataMobile {
	
	private String mId;
	private String landFillLocation;
	private String gridId;
	private Double methaneReading;
	private String mStartDate;
	private String mEndDate;
	private String imeNumber;
	private String inspectorName;
	private String inspectorUserName;
	private Double barometricPressure;
	private String instrumentSerialNumber;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getLandFillLocation() {
		return landFillLocation;
	}
	public void setLandFillLocation(String landFillLocation) {
		this.landFillLocation = landFillLocation;
	}
	public String getGridId() {
		return gridId;
	}
	public void setGridId(String gridId) {
		this.gridId = gridId;
	}
	public Double getMethaneReading() {
		return methaneReading;
	}
	public void setMethaneReading(Double methaneReading) {
		this.methaneReading = methaneReading;
	}
	public String getmStartDate() {
		return mStartDate;
	}
	public void setmStartDate(String mStartDate) {
		this.mStartDate = mStartDate;
	}
	public String getmEndDate() {
		return mEndDate;
	}
	public void setmEndDate(String mEndDate) {
		this.mEndDate = mEndDate;
	}
	public String getImeNumber() {
		return imeNumber;
	}
	public void setImeNumber(String imeNumber) {
		this.imeNumber = imeNumber;
	}
	public String getInspectorName() {
		return inspectorName;
	}
	public void setInspectorName(String inspectorName) {
		this.inspectorName = inspectorName;
	}
	public String getInspectorUserName() {
		return inspectorUserName;
	}
	public void setInspectorUserName(String inspectorUserName) {
		this.inspectorUserName = inspectorUserName;
	}
	public Double getBarometricPressure() {
		return barometricPressure;
	}
	public void setBarometricPressure(Double barometricPressure) {
		this.barometricPressure = barometricPressure;
	}
	public String getInstrumentSerialNumber() {
		return instrumentSerialNumber;
	}
	public void setInstrumentSerialNumber(String instrumentSerialNumber) {
		this.instrumentSerialNumber = instrumentSerialNumber;
	}

}
