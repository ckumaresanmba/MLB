package com.mylimobook.pojos;

import com.google.gson.annotations.SerializedName;

public class JobInfo {

	
	
	 private String BookStDt,PAirName,PTerName,PFlightNo,PSub,
	    DAirName,EvnTpe,Notes
	    ,DFlightNo,DTerName,DSub,Mobile,ParUname,ParRgNo,CUname,CRgNo,StsDt;
	   
	/* "BookStDt":"2013-08-16T16:27:00",
	    "JobStatusId":100,
	    "IsRet":false,
	    "NoOfPass":11,
	    "NoOfBSeats":0,
	    "PAirName":"Kingsford Smith",
	    "PTerName":"Terminal 3",
	    "PFlightNo":"123",
	    "PSub":null,
	    "JobId":14,
	    "DAirName":"",
	    "EvnTpe":"Test Updated",
	    "Notes":"notes",
	    "DFlightNo":null,
	    "DTerName":"Terminal 3",
	    "DSub":"BOGAN GATE",
	    "CustName":"hhhh vvg",
	    "Mobile":"89666666666",
	    "JbAmt":123.00,
	    "ParUname":null,
	    "ParRgNo":null,
	    "CUname":null,
	    "CRgNo":null,
	    "StsDt":"2013-08-16T09:58:37.85",
	    "OffDate":null,
	    "ConfDate":null,
	    "AccDate":null,
	    "ProPhtoUrl":null,
	    "IsDisplayJob":false
	 */
	 @SerializedName("CustName")
	 private String CustName;
	 
	 private int JobStatusId;
	 private int NoOfPass;
	 private int NoOfBSeats;
	 private int JobId;
		private	boolean IsRet,IsDisplayJob;
	 
	 public boolean isIsDisplayJob() {
		return IsDisplayJob;
	}
	public void setIsDisplayJob(boolean isDisplayJob) {
		IsDisplayJob = isDisplayJob;
	}

	 private	double JbAmt;
	 
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
		public String getEvnTpe() {
			return EvnTpe;
		}
		public void setEvnTpe(String evnTpe) {
			EvnTpe = evnTpe;
		}
		public String getNotes() {
			return Notes;
		}
		public void setNotes(String notes) {
			Notes = notes;
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
		public String getCustName() {
			return CustName;
		}
		public void setCustName(String custName) {
			CustName = custName;
		}
		public String getMobile() {
			return Mobile;
		}
		public void setMobile(String mobile) {
			Mobile = mobile;
		}
		public String getParUname() {
			return ParUname;
		}
		public void setParUname(String parUname) {
			ParUname = parUname;
		}
		public String getParRgNo() {
			return ParRgNo;
		}
		public void setParRgNo(String parRgNo) {
			ParRgNo = parRgNo;
		}
		public String getCUname() {
			return CUname;
		}
		public void setCUname(String cUname) {
			CUname = cUname;
		}
		public String getCRgNo() {
			return CRgNo;
		}
		public void setCRgNo(String cRgNo) {
			CRgNo = cRgNo;
		}
		public String getStsDt() {
			return StsDt;
		}
		public void setStsDt(String stsDt) {
			StsDt = stsDt;
		}
		public int getJobStatusId() {
			return JobStatusId;
		}
		public void setJobStatusId(int jobStatusId) {
			JobStatusId = jobStatusId;
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
		public double getJbAmt() {
			return JbAmt;
		}
		public void setJbAmt(double jbAmt) {
			JbAmt = jbAmt;
		}
		
		
}
