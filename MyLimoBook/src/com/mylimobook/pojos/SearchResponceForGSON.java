package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class SearchResponceForGSON {

	


 

	@SerializedName("Status")
	private String Status;
	
	@SerializedName("Data")      
    private DataSearchResponceForGSON data= new DataSearchResponceForGSON();
  

    public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public DataSearchResponceForGSON getData() {
		return data;
	}
	public void setData(DataSearchResponceForGSON data) {
		this.data = data;
	}
	          
	
}
