package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DataContactResPojo {

	public ArrayList<PendingContacts> getPendingContacts() {
		return PendingContacts;
	}

	public void setPendingContacts(ArrayList<PendingContacts> pendingContacts) {
		PendingContacts = pendingContacts;
	}

	public ArrayList<ExistingContactsPojo> getExistingContacts() {
		return ExistingContacts;
	}

	public void setExistingContacts(
			ArrayList<ExistingContactsPojo> existingContacts) {
		ExistingContacts = existingContacts;
	}

	@SerializedName("PendingContacts")
	private ArrayList<PendingContacts> PendingContacts;
	@SerializedName("ExistingContacts")
	private ArrayList<ExistingContactsPojo> ExistingContacts;

}
