package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class JobInfoData {

	/*
	 * "PSub":null, "JobId":1014, "DAirName":"", "EvnTpe":"Test",
	 * "Notes":"notes", "DFlightNo":null, "DTerName":"Test Terminal Name",
	 * "DSub":"ZARA", "CustName":"jaydeep icoderz", "Mobile":"1234567890",
	 * "JbAmt":289.11, "ParUname":null, "ParRgNo":null, "CUname":null,
	 * "CRgNo":null, "StsDt":"2013-08-02T06:04:59.673"
	 */
	@SerializedName("Payments")
	private ArrayList<PaymentsPojo> Payments;

	public ArrayList<PaymentsPojo> getPayments() {
		return Payments;
	}

	public void setPayments(ArrayList<PaymentsPojo> payments) {
		Payments = payments;
	}

	public JobInfo getJobInfo() {
		return JobInfo;
	}

	public void setJobInfo(JobInfo jobInfo) {
		JobInfo = jobInfo;
	}

	@SerializedName("JobInfo")
	private JobInfo JobInfo;

}
