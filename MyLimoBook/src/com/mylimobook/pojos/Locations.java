package com.mylimobook.pojos;

public class Locations {
	private String CityCode, LocDescription, LocationCode;

	public Locations(String CityCode, String LocDescription, String LocationCode) {
		this.CityCode = CityCode;
		this.LocDescription = LocDescription;
		this.LocationCode = LocationCode;

	}

	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	public String getLocDescription() {
		return LocDescription;
	}

	public void setLocDescription(String locDescription) {
		LocDescription = locDescription;
	}

	public String getLocationCode() {
		return LocationCode;
	}

	public void setLocationCode(String locationCode) {
		LocationCode = locationCode;
	}

}
