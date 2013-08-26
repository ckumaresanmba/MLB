package com.mylimobook.pojos;

public class SelectedContactDetailsPOJO {

	/*
	 * "UserKey":"8733dde1-dec9-423e-98c5-545dfd73dfa2","" +
	 * "FirstName":"jaydeep","LastName":"j",
	 * "LocDescription":"Sydney","RegoNo":"fdd","PhotoContent":null,
	 * "PhotoName":"~/ProfilePhotos/8733dde1-dec9-423e-98c5-545dfd73dfa2.jpg",
	 * "Status":0,
	 * "MobileNo":"123456789","VehicleType":null,"DateJoined":null,"IsNotified"
	 * :null,"ContactRequestId":0
	 */

	private String UserKey, LocDescription, FirstName, LastName, RegoNo,
			PhotoContent, PhotoName, MobileNo, VehicleType, DateJoined,
			IsNotified;
	
	private int Status, ContactRequestId;

	private boolean isFromPendingContact;
	
	
	public boolean isFromPendingContact() {
		return isFromPendingContact;
	}
	public void setFromPendingContact(boolean isFromPendingContact) {
		this.isFromPendingContact = isFromPendingContact;
	}
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getLocDescription() {
		return LocDescription;
	}
	public void setLocDescription(String locDescription) {
		LocDescription = locDescription;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getRegoNo() {
		return RegoNo;
	}
	public void setRegoNo(String regoNo) {
		RegoNo = regoNo;
	}
	public String getPhotoContent() {
		return PhotoContent;
	}
	public void setPhotoContent(String photoContent) {
		PhotoContent = photoContent;
	}
	public String getPhotoName() {
		return PhotoName;
	}
	public void setPhotoName(String photoName) {
		PhotoName = photoName;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getVehicleType() {
		return VehicleType;
	}
	public void setVehicleType(String vehicleType) {
		VehicleType = vehicleType;
	}
	public String getDateJoined() {
		return DateJoined;
	}
	public void setDateJoined(String dateJoined) {
		DateJoined = dateJoined;
	}
	public String getIsNotified() {
		return IsNotified;
	}
	public void setIsNotified(String isNotified) {
		IsNotified = isNotified;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getContactRequestId() {
		return ContactRequestId;
	}
	public void setContactRequestId(int contactRequestId) {
		ContactRequestId = contactRequestId;
	}
	
}
