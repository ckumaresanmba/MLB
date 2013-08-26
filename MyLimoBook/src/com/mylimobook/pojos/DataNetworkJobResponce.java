package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DataNetworkJobResponce {
	
	@SerializedName("NetworkJobs")
	private ArrayList<NetworkJobs> NetworkJobs;

  
    
    
    public ArrayList<NetworkJobs> getNetworkJobs() {
		return NetworkJobs;
	}

	public void setNetworkJobs(ArrayList<NetworkJobs> networkJobs) {
		NetworkJobs = networkJobs;
	}

	public int getRows() {
		return Rows;
	}

	public void setRows(int rows) {
		Rows = rows;
	}

	private int Rows;
	


}
