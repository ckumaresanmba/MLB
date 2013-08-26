package com.mylimobook;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;
import com.mylimobook.adapter.SearchContactAdapter;
import com.mylimobook.adapter.SearchContactAdapter.ViewHolder;
import com.mylimobook.contacts.ContactFragment;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.pojos.SearchResponceForGSON;
import com.mylimobook.pojos.SearchResultPojo;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;
import com.mylimobook.task.CustomAsynTaskJSONPOST;

public class SearchContact extends FragmentActivity implements
		AsyncTaskCompleteListener {
	EditText editText;
	static SearchContactAdapter searchContactAdapter;
	ImageButton imageButton_search;
	public static ArrayList<SearchResultPojo> searchList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);

		if (LimoUtil.isNetworkAvailable(this) == false) {
			LimoUtil.toast(
					getString(R.string.CheckYourInternetConnection), this);
		}

		imageButton_search = (ImageButton) findViewById(R.id.imageButton_search);
		ClearableEditText clearableEditText = (ClearableEditText) findViewById(R.id.textView1_search);
		editText = clearableEditText.getEditext();

		imageButton_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (editText.getText().toString().length() > 0) {
					performSearch();
					LimoUtil.hideKeyboard(editText, SearchContact.this);
				}

			}
		});
		performSearch();
		/*
		 * editText.setOnEditorActionListener(new
		 * TextView.OnEditorActionListener() {
		 * 
		 * @Override public boolean onEditorAction(TextView v, int actionId,
		 * KeyEvent event) {
		 * 
		 * return false; }
		 * 
		 * });
		 */

		listview = (ListView) findViewById(R.id.listView_search_contact);
		Button button_back = (Button) findViewById(R.id.button_back);
		button_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchContact.this.finish();
			}
		});

	}

	ListView listview;

	private void performSearch() {
		// TODO Auto-generated method stub

		new CustomAsynTaskGET(SearchContact.this, 1, "Searching...")
				.execute(LimoUtil.URL_Contacts + "?UserKey="
						+ new UserPefrencePOJO().getUserKey(SearchContact.this)
						+ "&SearchString=" + editText.getText().toString());

	}

	private SearchResponceForGSON searchResponceForGSON;

	int position = -1;

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(searchContactAdapter!=null)
		{
			searchContactAdapter.notifyDataSetChanged();
		}
	}
	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		// 07-24 17:23:45.236: I/System.out(27590):
		// {"Status":"Error","Data":null,"Message":["Search string required"]}

		if (result.equalsIgnoreCase("Exception") == false) {
			switch (taskId) {
			case 1:
				if (result != "Exception") {
					Gson gson = new Gson();
					searchResponceForGSON = gson.fromJson(result,
							SearchResponceForGSON.class);

					if (searchResponceForGSON.getData().getSearchResultList()
							.size() > 0) {
						setAdapter();
					} else {
						LimoUtil.toast("No Search Result found",
								SearchContact.this);
					}
				}
				break;
			case 2:
				if (result != "Exception") {
					// :{"Status":"Success","Data":null,"Message":["Your request has been sent successfully"]}

					try {
						JSONObject json = new JSONObject(result);
						String Status = json.getString("Status");

						if (Status.equalsIgnoreCase("Success")) {
						//	searchList.remove(position);
						//	searchContactAdapter.notifyDataSetChanged();
							searchList.get(position).setIsPending(true);
						}
						
						//searchList.remove(position);
					
						searchContactAdapter.notifyDataSetInvalidated();
						searchContactAdapter.notifyDataSetChanged();
						
						

						ExistingContactsPojo existingContactsPojo = new ExistingContactsPojo();
						existingContactsPojo.setContactRequestId(searchList.get(position).getContactRequestId());
						existingContactsPojo.setDateJoined(searchList.get(position).getDateJoined());
						existingContactsPojo.setEmail(searchList.get(position).getEmail());
						existingContactsPojo.setFirstName(searchList.get(position).getFirstName());
						existingContactsPojo.setLastName(searchList.get(position).getLastName());
						
						existingContactsPojo.setIsBlocked(false);
						existingContactsPojo.setIsNotified(searchList.get(position).isIsNotified());
						existingContactsPojo.setIsPending(searchList.get(position).isIsPending());
						
						existingContactsPojo.setLocDescription(searchList.get(position).getLocDescription());
						existingContactsPojo.setMobileNo(searchList.get(position).getMobileNo());
						//existingContactsPojo.setPhotoContent(searchList.get(position).getP);
						existingContactsPojo.setPhotoName(searchList.get(position).getPhotoName());
						existingContactsPojo.setPhotoUrl(searchList.get(position).getPhotoUrl());
						existingContactsPojo.setRegoNo(searchList.get(position).getRegoNo());
						
						existingContactsPojo.setStatus(searchList.get(position).getStatus());
						existingContactsPojo.setUserKey(searchList.get(position).getUserKey());
						existingContactsPojo.setVehicleType(searchList.get(position).getVehicleType());
					
						
						ContactFragment.filterContactsPojos.add(existingContactsPojo);
						
						
						
						
						String message = json.getJSONArray("Message")
								.getString(0);
						SearchContactAdapter.mImageFetcher.clearCache();
						LimoUtil.toast(message, SearchContact.this);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				break;

			default:
				break;
			}
		}

		//
	}

	public static void removeFromContactDetails1(int pos) {
		searchList.remove(pos);
		searchContactAdapter.notifyDataSetChanged();
	}

	private void setAdapter() {
		// TODO Auto-generated method stub
		searchList = searchResponceForGSON.getData().getSearchResultList();
		searchContactAdapter = new SearchContactAdapter(SearchContact.this,
				searchList);
		listview.setAdapter(searchContactAdapter);
	}

	
	
	public void addButtonclick(ViewHolder holder, int position) {
		// TODO Auto-generated method stub
		this.position = position;
		JSONObject json = new JSONObject();
		try {

			json.put("RequestByUserKey",
					new UserPefrencePOJO().getUserKey(SearchContact.this));

			json.put("RequestedToUserKey",
					searchContactAdapter.getItem(position).getUserKey());
			new CustomAsynTaskJSONPOST(SearchContact.this, 2,
					"Sending request...", LimoUtil.URL_Contacts, json)
					.execute("");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
