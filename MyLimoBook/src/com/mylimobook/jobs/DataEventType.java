package com.mylimobook.jobs;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
import com.mylimobook.pojos.AirportsPojo;

public class DataEventType {

	@SerializedName("EventTypes")
	ArrayList<EventTypesPOJO> EventTypes;

	@SerializedName("Airports")
	ArrayList<AirportsPojo> AirportsPojoList;

	public ArrayList<AirportsPojo> getAirportsPojoList() {
		return AirportsPojoList;
	}

	public void setAirportsPojoList(ArrayList<AirportsPojo> airportsPojoList) {
		AirportsPojoList = airportsPojoList;
	}

	public ArrayList<EventTypesPOJO> getEventTypes() {
		return EventTypes;
	}

	public void setEventTypes(ArrayList<EventTypesPOJO> eventTypes) {
		EventTypes = eventTypes;
	}
}
