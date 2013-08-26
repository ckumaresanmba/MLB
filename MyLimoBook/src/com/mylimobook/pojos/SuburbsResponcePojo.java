package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class SuburbsResponcePojo {

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@SerializedName("Status")
	String Status;

	@SerializedName("Data")
	private DataSuburbs data;

	public DataSuburbs getData() {
		return data;
	}

	public void setData(DataSuburbs data) {
		this.data = data;
	}
}
