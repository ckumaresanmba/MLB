package com.mylimobook.pojos;


public class MyJobRowPOJO {

	private String dayNO, dayStr, time, JobName, JobAdress, ammount;
	boolean isoffload;

	public	MyJobRowPOJO(String dayNO, String dayStr, String time, String JobName,
			String JobAdress, String ammount, boolean isoffload) {
		this.dayNO = dayNO;
		this.dayStr = dayStr;
		this.time = time;
		this.JobName = JobName;
		this.JobAdress = JobAdress;
		this.ammount = ammount;
		this.isoffload = isoffload;

	}

	public String getDayNO() {
		return dayNO;
	}

	public String getDayStr() {
		return dayStr;
	}

	public String getTime() {
		return time;
	}

	public String getJobName() {
		return JobName;
	}

	public String getJobAdress() {
		return JobAdress;
	}

	public String getAmmount() {
		return ammount;
	}

	public boolean isIsoffload() {
		return isoffload;
	}
}
