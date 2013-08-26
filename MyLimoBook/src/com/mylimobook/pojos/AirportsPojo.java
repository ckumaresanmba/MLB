package com.mylimobook.pojos;

public class AirportsPojo {

	private int AirportId;

	public int getAirportId() {
		return AirportId;
	}

	public void setAirportId(int airportId) {
		AirportId = airportId;
	}

	public String getAirportCode() {
		return AirportCode;
	}

	public void setAirportCode(String airportCode) {
		AirportCode = airportCode;
	}

	public String getAirportName() {
		return AirportName;
	}

	public void setAirportName(String airportName) {
		AirportName = airportName;
	}

	public String getTerminalName() {
		return TerminalName;
	}

	public void setTerminalName(String terminalName) {
		TerminalName = terminalName;
	}

	public String getCityCode() {
		return CityCode;
	}

	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}

	private String AirportCode, AirportName, TerminalName, CityCode;
}
