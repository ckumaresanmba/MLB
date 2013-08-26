package com.mylimobook.pojos;


public class Profile {

	
   
  /*  "LastName":"purohit",
    "Email":"jaydeep.icoderz@gmail.com",
    "MobileNo":"9874561232",
    "CreatedDate":"2013-08-14T05:39:14.797",
    "Colour":null,
    "UserKey":"f96095e9-a071-4e80-8154-33c900e0a64f",
    "CityCode":"4 ",
    "LocDescription":"Perth",
    "UpdatedDate":null,
    "RegoNo":"HC124",
    "MakeId":13,
    "PhotoUrl":"https://mylimobookimagestorage.blob.core.windows.net/profilephotos/f96095e9-a071-4e80-8154-33c900e0a64f.jpg",
    "State":"NSW"*/
    	
	private String FirstName = "";
	
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getPhotoUrl() {
		return PhotoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		PhotoUrl = photoUrl;
	}
	private String LastName = "", Email, MobileNo, CreatedDate, Colour,
			UserKey, CityCode, LocDescription, UpdatedDate, RegoNo,State,PhotoUrl;

	private int MakeId;

	

	
	
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getColour() {
		return Colour;
	}
	public void setColour(String colour) {
		Colour = colour;
	}
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getCityCode() {
		return CityCode;
	}
	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}
	public String getLocDescription() {
		return LocDescription;
	}
	public void setLocDescription(String locDescription) {
		LocDescription = locDescription;
	}
	public String getUpdatedDate() {
		return UpdatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		UpdatedDate = updatedDate;
	}
	public String getRegoNo() {
		return RegoNo;
	}
	public void setRegoNo(String regoNo) {
		RegoNo = regoNo;
	}
	
	public int getMakeId() {
		return MakeId;
	}
	public void setMakeId(int makeId) {
		MakeId = makeId;
	}
	

	
}
