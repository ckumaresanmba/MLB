package com.mylimobook.pojos;

public class ExistingContactsPojo {

	/*
	 * "UserKey":"d7b7efc9-b49f-4c64-b0a3-856e5be89cca", "FirstName":"jay",
	 * "LastName":"4", "LocDescription":"Melbourne", "RegoNo":"wy",
	 * "PhotoContent":null, "PhotoName":"", "Status":0, "MobileNo":"123456",
	 * "VehicleType":null, "DateJoined":null, "IsNotified":null,
	 * "ContactRequestId":0
	 */
/*	
    "UserKey":"d7b7efc9-b49f-4c64-b0a3-856e5be89cca",
    "FirstName":"jaydeep",
    "LastName":"4 new",
    "LocDescription":"Melbourne",
    "RegoNo":"wy",
    "PhotoUrl":null,
    "PhotoName":null,
    "Status":0,
    "MobileNo":"123456",
    "VehicleType":null,
    "DateJoined":null,
    "IsNotified":null,
    "ContactRequestId":0,
    "Email":null
    VehicleTypeName
    
    IsPending
*/
	private String UserKey, FirstName, LastName, LocDescription, RegoNo,PhotoUrl,
			PhotoContent, PhotoName, MobileNo, VehicleType, DateJoined,
			Email,VehicleTypeName;
	private boolean IsPending,IsBlocked,IsNotified;
	/*public String getVehicleTypeName() {
		return VehicleTypeName;
	}

	public void setVehicleTypeName(String vehicleTypeName) {
		VehicleTypeName = vehicleTypeName;
	}*/

	public boolean isIsBlocked() {
		return IsBlocked;
	}

	public void setIsBlocked(boolean isBlocked) {
		IsBlocked = isBlocked;
	}

	public boolean isIsPending() {
		return IsPending;
	}

	public void setIsPending(boolean isPending) {
		IsPending = isPending;
	}

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	boolean isChecked=false;
	private int Status, ContactRequestId;

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}



	public String getUserKey() {
		return UserKey;
	}

	public void setUserKey(String userKey) {
		UserKey = userKey;
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

	public String getLocDescription() {
		return LocDescription;
	}

	public void setLocDescription(String locDescription) {
		LocDescription = locDescription;
	}

	public String getRegoNo() {
		
		RegoNo=RegoNo+"";
		if(RegoNo.equalsIgnoreCase("null"))
		{
			RegoNo="";
		}
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

	public boolean getIsNotified() {
		return IsNotified;
	}

	public void setIsNotified(boolean isNotified) {
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
