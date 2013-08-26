package com.mylimobook.contacts;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.adapter.PendingContactAdapter;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.pojos.PendingContacts;
import com.mylimobook.task.AsyncTaskCompleteListenerPosition;

public class PendingContactFragment extends Fragment implements
		AsyncTaskCompleteListenerPosition {

	ListView listview;
	static ArrayList<PendingContacts> pendingContact ;
	static PendingContactAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		

		View viewMain = inflater.inflate(
				com.mylimobook.R.layout.pending_contact_fragment, null);
		// View viewMain = inflater.inflate(R.layout.quate_fragmemt, null);
		// initView(viewMain);
		listview = (ListView) viewMain
				.findViewById(R.id.listView_pendingcontact);

		// (ContactFragmentActivity)getActivity())
		// (ContactFragmentActivity)getActivity() d =new
		// ContactFragmentActivity();

		String status = LimoUtil
				.contactResponce
				.getStatus();
		if (status != null && status.equalsIgnoreCase("Success")) {
			pendingContact=LimoUtil
					.contactResponce
					.getData().getPendingContacts();
			 adapter = new PendingContactAdapter(
					PendingContactFragment.this, pendingContact, container);
			listview.setAdapter(adapter);
			ContactFragmentActivity.CONTENT[1]="Requests"+" ("+pendingContact.size()+")";
		}

		return viewMain;
	}
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public static android.support.v4.app.Fragment newInstance() {
		PendingContactFragment fragment = new PendingContactFragment();

		return fragment;
	}

	public static  void returnAndRefreshFromContactDetails(int pos,boolean isAccept)
	{
	
		if(isAccept)
		{
			ExistingContactsPojo existingContactsPojo = new ExistingContactsPojo();
			existingContactsPojo.setContactRequestId(pendingContact.get(pos).getContactRequestId());
			existingContactsPojo.setDateJoined(pendingContact.get(pos).getDateJoined());
			existingContactsPojo.setEmail(pendingContact.get(pos).getEmail());
			existingContactsPojo.setFirstName(pendingContact.get(pos).getFirstName());
			existingContactsPojo.setLastName(pendingContact.get(pos).getLastName());
			
			existingContactsPojo.setIsBlocked(false);
			existingContactsPojo.setIsNotified(pendingContact.get(pos).getIsNotified());
			existingContactsPojo.setIsPending(false);
			
			existingContactsPojo.setLocDescription(pendingContact.get(pos).getLocDescription());
			existingContactsPojo.setMobileNo(pendingContact.get(pos).getMobileNo());
			existingContactsPojo.setPhotoContent(pendingContact.get(pos).getPhotoContent());
			existingContactsPojo.setPhotoName(pendingContact.get(pos).getPhotoName());
			existingContactsPojo.setPhotoUrl(pendingContact.get(pos).getPhotoUrl());
			existingContactsPojo.setRegoNo(pendingContact.get(pos).getRegoNo());
			
			existingContactsPojo.setStatus(pendingContact.get(pos).getStatus());
			existingContactsPojo.setUserKey(pendingContact.get(pos).getUserKey());
			existingContactsPojo.setVehicleType(pendingContact.get(pos).getVehicleType());
		
			
			ContactFragment.filterContactsPojos.add(existingContactsPojo);
			
		
			
		}
		System.out.println("))))))))))))))))))))))))");
		System.out.println("Size "+PendingContactFragment.pendingContact.size());
		System.out.println("pos "+pos);
		pendingContact.remove(pos);
		adapter.notifyDataSetChanged();
		ContactFragmentActivity.CONTENT[1]="Requests"+" ("+pendingContact.size()+")";
		
	}

	
	
	
	
	
	@Override
	public void onTaskComplete(String result, int taskId, int position) {
		// TODO Auto-generated method stub
		switch (taskId) {
		case 2:
			if (result != "Exception") {

				//LimoUtil.toast(result, getActivity());

			//{"Status":"Error","Data":null,"Message":["Contact doesn't exist"]}
				
				try {
					JSONObject json= new JSONObject(result);
					
					String status=json.getString("Status");
					String msg=json.getJSONArray("Message").getString(0);
					
					//alertDialog(msg, status,position);
					
					if(status.equals("Success"))
					{
						LimoUtil.toast(msg, PendingContactFragment.this.getActivity());
						pendingContact.remove(position);
						adapter.notifyDataSetChanged();
						ContactFragmentActivity.CONTENT[1]="Requests"+" ("+pendingContact.size()+")";
					
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case 3:
			if (result != "Exception") {

				//LimoUtil.toast(result, getActivity());
				try {
					JSONObject json= new JSONObject(result);
					
					String status=json.getString("Status");
					String msg=json.getJSONArray("Message").getString(0);
					
					if(status.equals("Success"))
					{
						LimoUtil.toast(msg, PendingContactFragment.this.getActivity());
						pendingContact.remove(position);
						adapter.notifyDataSetChanged();
						ContactFragmentActivity.CONTENT[1]="Requests"+" ("+pendingContact.size()+")";
					
					}
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
	
	

	public   void alertDialog1(String string, String status,final int position) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
		alert.setMessage(string);
		if(status.equals("Success"))
		{
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					pendingContact.remove(position);
					adapter.notifyDataSetChanged();
					ContactFragmentActivity.CONTENT[1]="Requests"+" ("+pendingContact.size()+")";
					
				}
			});
		}
		else
		{
			alert.setPositiveButton("OK", null);
		}
		
		alert.show();

	}
	
}
