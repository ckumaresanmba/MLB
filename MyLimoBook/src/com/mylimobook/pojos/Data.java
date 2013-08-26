package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Data {
	
	@SerializedName("Profile")
	private Profile profile;
	
	
	@SerializedName("Locations")
	ArrayList<Locations> LocationsList;
	
	
	@SerializedName("MakeTypes")
	ArrayList<MakeTypes> makeTypesList;
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public ArrayList<Locations> getLocationsList() {
		return LocationsList;
	}
	public void setLocationsList(ArrayList<Locations> locationsList) {
		LocationsList = locationsList;
	}
	public ArrayList<MakeTypes> getMakeTypesList() {
		return makeTypesList;
	}
	public void setMakeTypesList(ArrayList<MakeTypes> makeTypesList) {
		this.makeTypesList = makeTypesList;
	}
	
}
