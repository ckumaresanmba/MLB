package com.mylimobook.pojos;

public class PendingContacts {
   /* "UserKey":"f29e9cd7-f465-4ee2-9846-c73f2e20c06c",
    "FirstName":"jay",
    "LastName":"1",
    "LocDescription":"Adelaide",
    "RegoNo":"hh",
    "PhotoContent":"",
    "PhotoName":"api",
    "Status":10,
    "MobileNo":null,
    "VehicleType":null,
    "DateJoined":null,
    "IsNotified":false,
    "ContactRequestId":8
    VehicleTypeName
    
    
    */
   private boolean IsNotified;
	private String VehicleTypeName,UserKey,FirstName,LastName,LocDescription,RegoNo,PhotoContent,PhotoName,MobileNo,
	VehicleType,DateJoined,Email,PhotoUrl;
	/*public String getVehicleTypeName() {
		return VehicleTypeName;
	}
	public void setVehicleTypeName(String vehicleTypeName) {
		VehicleTypeName = vehicleTypeName;
	}*/
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhotoUrl() {
		return PhotoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}
	private	int Status,ContactRequestId;
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
