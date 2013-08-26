package com.mylimobook.pojos;

public class MakeTypes {

	private int MakeId, NoOfSeats;
	private String Description = "";

	public MakeTypes(int MakeId, String Description, int NoOfSeats) {
		this.MakeId = MakeId;
		this.Description = Description;
		this.NoOfSeats = NoOfSeats;
	}

	public int getMakeId() {
		return MakeId;
	}

	public int getNoOfSeats() {
		return NoOfSeats;
	}

	public String getDescription() {
		return Description;
	}

}
