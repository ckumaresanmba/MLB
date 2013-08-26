package com.mylimobook.pojos;

public class JobPojo {

	private String FirstName,LastName,MobileNo,BookingDateStartTime,Pkup_FlightNo,Pickup_StreetNo,Pickup_State
	,Pickup_Address,Pickup_Suburb,Pickup_PostalCode,Pickup_Landmark,Destn_FlightNo,Destn_StreetNo,
	Dest_Address,Dest_Suburb,Dest_PostalCode,Dest_Landmark,Dest_State,UserKey,Notes;
	
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
	public String getMobileNo() {
		return MobileNo;
	}
	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}
	public String getBookingDateStartTime() {
		return BookingDateStartTime;
	}
	public void setBookingDateStartTime(String bookingDateStartTime) {
		BookingDateStartTime = bookingDateStartTime;
	}
	public String getPkup_FlightNo() {
		return Pkup_FlightNo;
	}
	public void setPkup_FlightNo(String pkup_FlightNo) {
		Pkup_FlightNo = pkup_FlightNo;
	}
	public String getPickup_StreetNo() {
		return Pickup_StreetNo;
	}
	public void setPickup_StreetNo(String pickup_StreetNo) {
		Pickup_StreetNo = pickup_StreetNo;
	}
	public String getPickup_State() {
		return Pickup_State;
	}
	public void setPickup_State(String pickup_State) {
		Pickup_State = pickup_State;
	}
	public String getPickup_Address() {
		return Pickup_Address;
	}
	public void setPickup_Address(String pickup_Address) {
		Pickup_Address = pickup_Address;
	}
	public String getPickup_Suburb() {
		return Pickup_Suburb;
	}
	public void setPickup_Suburb(String pickup_Suburb) {
		Pickup_Suburb = pickup_Suburb;
	}
	public String getPickup_PostalCode() {
		return Pickup_PostalCode;
	}
	public void setPickup_PostalCode(String pickup_PostalCode) {
		Pickup_PostalCode = pickup_PostalCode;
	}
	public String getPickup_Landmark() {
		return Pickup_Landmark;
	}
	public void setPickup_Landmark(String pickup_Landmark) {
		Pickup_Landmark = pickup_Landmark;
	}
	public int getDestn_AirportId() {
		return Destn_AirportId;
	}
	public void setDestn_AirportId(int destn_AirportId) {
		Destn_AirportId = destn_AirportId;
	}
	public String getDestn_FlightNo() {
		return Destn_FlightNo;
	}
	public void setDestn_FlightNo(String destn_FlightNo) {
		Destn_FlightNo = destn_FlightNo;
	}
	public String getDestn_StreetNo() {
		return Destn_StreetNo;
	}
	public void setDestn_StreetNo(String destn_StreetNo) {
		Destn_StreetNo = destn_StreetNo;
	}
	public String getDest_Address() {
		return Dest_Address;
	}
	public void setDest_Address(String dest_Address) {
		Dest_Address = dest_Address;
	}
	public String getDest_Suburb() {
		return Dest_Suburb;
	}
	public void setDest_Suburb(String dest_Suburb) {
		Dest_Suburb = dest_Suburb;
	}
	public String getDest_PostalCode() {
		return Dest_PostalCode;
	}
	public void setDest_PostalCode(String dest_PostalCode) {
		Dest_PostalCode = dest_PostalCode;
	}
	public String getDest_Landmark() {
		return Dest_Landmark;
	}
	public void setDest_Landmark(String dest_Landmark) {
		Dest_Landmark = dest_Landmark;
	}
	public String getDest_State() {
		return Dest_State;
	}
	public void setDest_State(String dest_State) {
		Dest_State = dest_State;
	}
	public String getUserKey() {
		return UserKey;
	}
	public void setUserKey(String userKey) {
		UserKey = userKey;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
	public boolean isIsReturnPickUp() {
		return IsReturnPickUp;
	}
	public void setIsReturnPickUp(boolean isReturnPickUp) {
		IsReturnPickUp = isReturnPickUp;
	}
	public boolean isSpecialNeeds() {
		return SpecialNeeds;
	}
	public void setSpecialNeeds(boolean specialNeeds) {
		SpecialNeeds = specialNeeds;
	}
	public boolean isIsDisplay() {
		return IsDisplay;
	}
	public void setIsDisplay(boolean isDisplay) {
		IsDisplay = isDisplay;
	}
	public int getJobId() {
		return JobId;
	}
	public void setJobId(int jobId) {
		JobId = jobId;
	}
	public int getNoOfpassengers() {
		return NoOfpassengers;
	}
	public void setNoOfpassengers(int noOfpassengers) {
		NoOfpassengers = noOfpassengers;
	}
	public int getNoOfBaBySheets() {
		return NoOfBaBySheets;
	}
	public void setNoOfBaBySheets(int noOfBaBySheets) {
		NoOfBaBySheets = noOfBaBySheets;
	}
	public int getEventTypeId() {
		return EventTypeId;
	}
	public void setEventTypeId(int eventTypeId) {
		EventTypeId = eventTypeId;
	}
	public int getPkup_AirportId() {
		return Pkup_AirportId;
	}
	public void setPkup_AirportId(int pkup_AirportId) {
		Pkup_AirportId = pkup_AirportId;
	}
	public double getAmount() {
		return Amount;
	}
	public void setAmount(double amount) {
		Amount = amount;
	}
	private boolean IsReturnPickUp,SpecialNeeds,IsDisplay;
	private int JobId,NoOfpassengers,NoOfBaBySheets,EventTypeId,Pkup_AirportId,Destn_AirportId;
	private double Amount;
	
    
/*    "":true,
    "":10,
    "":0,
    "":false,
    "":null,
    "":6,
    "":3,
    "":"qzxy",
    "":null,
    "":null,
    "":null,
    "":null,
    "":null,
    "":null,
    "":null,
    "":null,
    "":"no",
    "":"",
    "":"ABBOTSFORD",
    "":"2046",
    "":"yyy",
    "":"NSW",
    "":true,
    "Amount":123.89,
    "":"8733dde1-dec9-423e-98c5-545dfd73dfa2",
    "JobId":1032*/
    
    
    
}
