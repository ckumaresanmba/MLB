package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class SuburbsPojo {

	
	@SerializedName("SuburbId")
	int SuburbId;
	@SerializedName("Postalcode")

	int Postalcode;
	
	@SerializedName("SuburbDesc")
	String SuburbDesc;

	@SerializedName("State")
	String  State;

	@SerializedName("Lat")
	String Lat;
	
	
	@SerializedName("Long")
	String  Long;
	

	@SerializedName("CountryCode")
	String  CountryCode;
	
	
	public int getSuburbId() {
		return SuburbId;
	}
	public void setSuburbId(int suburbId) {
		SuburbId = suburbId;
	}
	public int getPostalcode() {
		return Postalcode;
	}
	public void setPostalcode(int postalcode) {
		Postalcode = postalcode;
	}
	public String getSuburbDesc() {
		return SuburbDesc;
	}
	public void setSuburbDesc(String suburbDesc) {
		SuburbDesc = suburbDesc;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getLong() {
		return Long;
	}
	public void setLong(String l) {
		Long = l;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	
	
}
