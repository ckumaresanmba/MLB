package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class MyJobResponcePOJO {

	private String Status, Message;

	@SerializedName("Data")
	private DataMYJobResponce Data;

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

	public DataMYJobResponce getData() {
		return Data;
	}

	public void setData(DataMYJobResponce data) {
		Data = data;
	}

}
