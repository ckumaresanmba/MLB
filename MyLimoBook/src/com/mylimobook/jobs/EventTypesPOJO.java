package com.mylimobook.jobs;

public class EventTypesPOJO {
	
	public EventTypesPOJO(int EventTypeId,String Description)
	{
		this.EventTypeId=EventTypeId;
		this.Description=Description;
	}
	
	public int getEventTypeId() {
		return EventTypeId;
	}
	public void setEventTypeId(int eventTypeId) {
		EventTypeId = eventTypeId;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	int  EventTypeId;
	String Description="";
	
 

}
