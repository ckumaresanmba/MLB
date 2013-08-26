package com.mylimobook.pojos;

public class EditJobRes {

	
/*    "Status":"Success",
    "Data":{
        "Job":{},
        "EventTypes":[],
        "Airports":[],
        "Locations":[]
    },
    "Message":null*/
    
    private String Status,Message;
    public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public DataEditJob getData() {
		return Data;
	}
	public void setData(DataEditJob data) {
		Data = data;
	}
	private DataEditJob  Data;
    
    
}
