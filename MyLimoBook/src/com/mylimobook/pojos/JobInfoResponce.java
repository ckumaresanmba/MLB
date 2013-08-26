package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class JobInfoResponce {
    /*"Status":"Success",
    "Data":{
        "JobInfo":{},
        "Payments":[
        ]
    },
    "Message":null
    */
    
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
	public JobInfoData getData() {
		return Data;
	}
	public void setData(JobInfoData data) {
		Data = data;
	}
	private String Status,Message;
	@SerializedName("Data")
    private JobInfoData Data;
}
