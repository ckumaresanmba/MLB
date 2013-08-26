package com.mylimobook.pojos;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DataSearchResponceForGSON {

	//
	@SerializedName("SearchResult")
	ArrayList<SearchResultPojo> SearchResultList = new ArrayList<SearchResultPojo>();

	public ArrayList<SearchResultPojo> getSearchResultList() {
		return SearchResultList;
	}

	public void setSearchResultList(ArrayList<SearchResultPojo> searchResultList) {
		SearchResultList = searchResultList;
	}
}
