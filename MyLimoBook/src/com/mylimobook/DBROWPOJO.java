package com.mylimobook;

public class DBROWPOJO {
	
	
	int Postalcode;
	String SuburbDesc,State,Lat,Long,Countrycode;

	public DBROWPOJO(int Postalcode,String SuburbDesc,String State,String Lat
			,String Long,String Countrycode)
	{
		this.Postalcode=Postalcode;

		this.SuburbDesc=SuburbDesc;
		this.State=State;
		this.Lat=Lat;
		this.Long=Long;
		this.Countrycode=Countrycode;

		
	}
	public int getPostalcode() {
		return Postalcode;
	}
	public void setPostalcode1(int postalcode) {
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
	public String getCountrycode() {
		return Countrycode;
	}
	public void setCountrycode(String countrycode) {
		Countrycode = countrycode;
	}
	}
