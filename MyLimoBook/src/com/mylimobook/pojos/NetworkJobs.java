package com.mylimobook.pojos;

public class NetworkJobs {
//
//    "BookStDt":"2013-08-02T22:17:00",
//    "":false,
//    "":true,
//    "":11,
//    "":1,
//    "PAirName":"Test Airport Name Updated",
//    "PTerName":"Test Terminal Name",
//    "PFlightNo":"flight No",
//    "PSub":null,
//    "JobId":1017,
//    "DAirName":null,
//    "":null,
//    "":null,
//    "":"AARONS PASS",
//    "":156.18,
//    "":"jay 1",
//    "":"f29e9cd7-f465-4ee2-9846-c73f2e20c06c",
//    "":false,
//    "":"hh"
//    	
	private String BookStDt,PAirName,PTerName,PFlightNo,PSub
	
	,DAirName,DFlightNo,DTerName,DSub,FName,UserKey,RegoNo;

	public String getBookStDt() {
		return BookStDt;
	}

	public void setBookStDt(String bookStDt) {
		BookStDt = bookStDt;
	}

	public String getPAirName() {
		return PAirName;
	}

	public void setPAirName(String pAirName) {
		PAirName = pAirName;
	}

	public String getPTerName() {
		return PTerName;
	}

	public void setPTerName(String pTerName) {
		PTerName = pTerName;
	}

	public String getPFlightNo() {
		return PFlightNo;
	}

	public void setPFlightNo(String pFlightNo) {
		PFlightNo = pFlightNo;
	}

	public String getPSub() {
		return PSub;
	}

	public void setPSub(String pSub) {
		PSub = pSub;
	}

	public String getDAirName() {
		return DAirName;
	}

	public void setDAirName(String dAirName) {
		DAirName = dAirName;
	}

	public String getDFlightNo() {
		return DFlightNo;
	}

	public void setDFlightNo(String dFlightNo) {
		DFlightNo = dFlightNo;
	}

	public String getDTerName() {
		return DTerName;
	}

	public void setDTerName(String dTerName) {
		DTerName = dTerName;
	}

	public String getDSub() {
		return DSub;
	}

	public void setDSub(String dSub) {
		DSub = dSub;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getUserKey() {
		return UserKey;
	}

	public void setUserKey(String userKey) {
		UserKey = userKey;
	}

	public String getRegoNo() {
		return RegoNo;
	}

	public void setRegoNo(String regoNo) {
		RegoNo = regoNo;
	}

	public int getNoOfPass() {
		return NoOfPass;
	}

	public void setNoOfPass(int noOfPass) {
		NoOfPass = noOfPass;
	}

	public int getNoOfBSeats() {
		return NoOfBSeats;
	}

	public void setNoOfBSeats(int noOfBSeats) {
		NoOfBSeats = noOfBSeats;
	}

	public int getJobId() {
		return JobId;
	}

	public void setJobId(int jobId) {
		JobId = jobId;
	}

	public boolean isIsRet() {
		return IsRet;
	}

	public void setIsRet(boolean isRet) {
		IsRet = isRet;
	}

	public boolean isIsAccepted() {
		return IsAccepted;
	}

	public void setIsAccepted(boolean isAccepted) {
		IsAccepted = isAccepted;
	}

	public boolean isIsCovered() {
		return IsCovered;
	}

	public void setIsCovered(boolean isCovered) {
		IsCovered = isCovered;
	}

	public double getAmount() {
		return Amount;
	}

	public void setAmount(double amount) {
		Amount = amount;
	}

	private int  NoOfPass, NoOfBSeats,JobId;
	private boolean IsRet,IsAccepted,IsCovered;

	private double Amount;
	
	

}
