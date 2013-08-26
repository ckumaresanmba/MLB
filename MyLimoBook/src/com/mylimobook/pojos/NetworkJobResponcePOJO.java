package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class NetworkJobResponcePOJO {

	private String Status, Message;

	@SerializedName("Data")
	private DataNetworkJobResponce Data;

	public DataNetworkJobResponce getData() {
		return Data;
	}

	public void setData(DataNetworkJobResponce data) {
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
