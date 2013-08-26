package com.mylimobook.jobs;

import com.google.gson.annotations.SerializedName;

public class EventTypeResponcePOJO {

	
	@SerializedName("Status")	
	String Status="";
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public DataEventType getData() {
		return Data;
	}
	public void setData(DataEventType data) {
		Data = data;
	}
	@SerializedName("Data")
	DataEventType Data;
				
}
