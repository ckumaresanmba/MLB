package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DataMYJobResponce {
	
	@SerializedName("MyJobs")
	private ArrayList<MyJobs> MyJobs;
	
	public ArrayList<MyJobs> getMyJobs() {
		return MyJobs;
	}

	public void setMyJobs(ArrayList<MyJobs> myJobs) {
		MyJobs = myJobs;
	}


}
