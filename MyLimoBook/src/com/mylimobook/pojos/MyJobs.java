package com.mylimobook.pojos;

public class MyJobs {

	/*
	 * // "BookStDt":"2013-07-30T07:52:00", "JobStatusId":100, "IsRet":true,
	 * "NoOfPass":1, "NoOfBSeats":0, "PAirName":null, "PTerName":null,
	 * "PFlightNo":"fg", "PSub":null, "JobId":15,
	 * "DAirName":"Test Airport Name Updated", "DFlightNo":null,
	 * "DTerName":"Test Terminal Name", "DSub":"BABYL CREEK", "ParUname":null,
	 * "ParRgNo":null, "CUname":null, "CRgNo":null, "AccUserCount":0,
	 * "RableComm":null, "RECComm":null, "PableComm":null, "PACN":null,
	 * "RecAmt":null, "ExpAmt":155.50
	 */
	private String BookStDt, PAirName, PTerName, PFlightNo, PSub, DAirName,
			DFlightNo, DTerName, DSub, ParUname, ParRgNo, CUname, CRgNo,
			RableComm, RECComm, PableComm, PACN, RecAmt, ExpAmt;
	private int JobStatusId, NoOfPass, NoOfBSeats, JobId, AccUserCount;
	private boolean IsRet;

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
		CUname=CUname+"";
		if(CUname.equalsIgnoreCase("null"))
		{
			CUname="";
		}
		return CUname;
	}

	public void setCUname(String cUname) {
		CUname = cUname;
	}

	public String getCRgNo() {
		CRgNo=CRgNo+"";
		if(CRgNo.equalsIgnoreCase("null"))
		{
			CRgNo="";
		}
		return CRgNo;
	}

	public void setCRgNo(String cRgNo) {
		CRgNo = cRgNo;
	}

	public String getRableComm() {
		return RableComm;
	}

	public void setRableComm(String rableComm) {
		RableComm = rableComm;
	}

	public String getRECComm() {
		return RECComm;
	}

	public void setRECComm(String rECComm) {
		RECComm = rECComm;
	}

	public String getPableComm() {
		return PableComm;
	}

	public void setPableComm(String pableComm) {
		PableComm = pableComm;
	}

	public String getPACN() {
		return PACN;
	}

	public void setPACN(String pACN) {
		PACN = pACN;
	}

	public String getRecAmt() {
		return RecAmt;
	}

	public void setRecAmt(String recAmt) {
		RecAmt = recAmt;
	}

	public String getExpAmt() {
		return ExpAmt;
	}

	public void setExpAmt(String expAmt) {
		ExpAmt = expAmt;
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

	public int getAccUserCount() {
		return AccUserCount;
	}

	public void setAccUserCount(int accUserCount) {
		AccUserCount = accUserCount;
	}

	public boolean isIsRet() {
		return IsRet;
	}

	public void setIsRet(boolean isRet) {
		IsRet = isRet;
	}

}
