package com.mylimobook.pojos;

import java.util.ArrayList;

import com.mylimobook.jobs.EventTypesPOJO;

public class DataEditJob {
	
	
	private  JobPojo Job;
	public JobPojo getJob() {
		return Job;
	}
	public void setJob(JobPojo job) {
		Job = job;
	}
	public ArrayList<EventTypesPOJO> getEventTypes() {
		return EventTypes;
	}
	public void setEventTypes(ArrayList<EventTypesPOJO> eventTypes) {
		EventTypes = eventTypes;
	}
	public ArrayList<AirportsPojo> getAirports() {
		return Airports;
	}
	public void setAirports(ArrayList<AirportsPojo> airports) {
		Airports = airports;
	}
	public ArrayList<Locations> getLocations() {
		return Locations;
	}
	public void setLocations(ArrayList<Locations> locations) {
		Locations = locations;
	}
	private ArrayList<EventTypesPOJO> EventTypes= new ArrayList<EventTypesPOJO>();
	private ArrayList<AirportsPojo> Airports= new ArrayList<AirportsPojo>();
	private ArrayList<Locations> Locations= new ArrayList<Locations>();
	
}
