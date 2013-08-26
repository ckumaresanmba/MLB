package com.mylimobook.pojos;

public class SearchResultPojo {

	private String UserKey, FirstName, LastName, LocDescription, RegoNo,
			PhotoName, MobileNo, VehicleType,
			
			
			DateJoined,PhotoUrl,StatusIsNotified,Email;

	
	int ContactRequestId;
	public boolean isIsNotified() {
		return IsNotified;
	}

	public void setIsNotified(boolean isNotified) {
		IsNotified = isNotified;
	}

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

	private boolean IsNotified,IsBlocked,IsPending;
	
  /*     "UserKey":"73536b16-0367-41da-9d1f-a8b55609a6fa",
    "FirstName":"Jaydeep",
    "LastName":"Icoderz",
    "LocDescription":"Sydney",
    "RegoNo":"HC123",
    "PhotoUrl":null,
    "PhotoName":"",
    "MobileNo":"9874561230",
    "VehicleType":null,
    "DateJoined":null,
    "IsNotified":null,
    "ContactRequestId":0,
    "Email":"jaydeep.icoderz@gmail.com",
    "IsBlocked":false,
    "IsPending":true
	*/
	

	public String getPhotoUrl() {
		return PhotoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}

	public String getStatusIsNotified() {
		return StatusIsNotified;
	}

	public void setStatusIsNotified(String statusIsNotified) {
		StatusIsNotified = statusIsNotified;
	}

	public int getContactRequestId() {
		return ContactRequestId;
	}

	public void setContactRequestId(int contactRequestId) {
		ContactRequestId = contactRequestId;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	private int Status;

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

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

}
