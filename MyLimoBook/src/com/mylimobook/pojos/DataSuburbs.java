package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DataSuburbs {

	
	
	@SerializedName("Suburbs")
	ArrayList<SuburbsPojo> suburbsPojosList;

	public ArrayList<SuburbsPojo> getSuburbsPojosList() {
		return suburbsPojosList;
	}

	public void setSuburbsPojosList(ArrayList<SuburbsPojo> suburbsPojosList) {
		this.suburbsPojosList = suburbsPojosList;
	}

}
