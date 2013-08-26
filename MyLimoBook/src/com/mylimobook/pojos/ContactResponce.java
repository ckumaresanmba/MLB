package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class ContactResponce {

	/*
	 * "Status":"Success", "Data":{}, "Message":null
	 */
	private String Status, Message;
	@SerializedName("Data")
	private DataContactResPojo Data;

	public DataContactResPojo getData() {
		return Data;
	}

	public void setData(DataContactResPojo data) {
		Data = data;
	}

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

}
