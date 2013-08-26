package com.mylimobook.contacts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.adapter.ExistingContactsAdapter;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.pojos.SelectedContactDetailsPOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;

public class ContactFragment extends Fragment implements
		AsyncTaskCompleteListener {

	static ListView listview;
	ImageButton clearable_button_clear;
	EditText textView1_search;
	ProgressBar progressBar1;
	ArrayList<ExistingContactsPojo> existingContactsPojos;
	public static ArrayList<ExistingContactsPojo> filterContactsPojos;
	 static ExistingContactsAdapter adapter;
	 ViewGroup container;

	 @Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		listview=null;
		adapter=null;
		
		
	}
	 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.container=container;
		View viewMain = inflater.inflate(
				com.mylimobook.R.layout.contactfragment, null);

		listview = (ListView) viewMain.findViewById(R.id.listView_contactList);
		textView1_search = (EditText) viewMain
				.findViewById(R.id.textView1_search);
		progressBar1 = (ProgressBar) viewMain.findViewById(R.id.progressBar1);
		progressBar1.setVisibility(View.GONE);
		clearable_button_clear = (ImageButton) viewMain
				.findViewById(R.id.clearable_button_clear);
		clearable_button_clear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textView1_search.setText("");
			}
		});
		textView1_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				performSearch(textView1_search.getText().toString());
				/*if (count != 0 && count != (s.length() - 1)) {
					if (s.length() >= 1) {
						performSearch(textView1_search.getText().toString());
					}
				}*/
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		textView1_search
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView v, int actionId,
							KeyEvent event) {
						if (actionId == EditorInfo.IME_ACTION_SEARCH) {
							performSearch(textView1_search.getText().toString());
							return true;
						}
						return false;
					}

				});

		String status = LimoUtil.contactResponce
				.getStatus();
		if (status != null && status.equalsIgnoreCase("Success")) {
			existingContactsPojos = LimoUtil.contactResponce
					.getData().getExistingContacts();

			filterContactsPojos = existingContactsPojos;
			sortExistingContact();
			adapter = new ExistingContactsAdapter(ContactFragment.this,
					filterContactsPojos, container);
			listview.setAdapter(adapter);
		}

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				/*ExistingContactsPojo ds = (ExistingContactsPojo) arg0
						.getItemAtPosition(arg2);

				Gson gson = new Gson();
				SelectedContactDetailsPOJO selectedContactDetailsPOJO;
				String str = gson.toJson(ds);

				Intent intent = new Intent(getActivity(), ContactDetails.class);
				intent.putExtra("OBJECT", str);
				intent.putExtra("ISFROM", 1);
				tempPosForRemove=arg2;
				startActivityForResult(intent, 1);*/

			}
		});

		return viewMain;
	}
	
	
	public void listItemClick(int pos)
	{
		ExistingContactsPojo ds = (ExistingContactsPojo) filterContactsPojos
				.get(pos);

		Gson gson = new Gson();
		SelectedContactDetailsPOJO selectedContactDetailsPOJO;
		String str = gson.toJson(ds);

		Intent intent = new Intent(getActivity(), ContactDetails.class);
		intent.putExtra("OBJECT", str);
		intent.putExtra("ISFROM", 1);
		intent.putExtra("POS", pos);
		tempPosForRemove=pos;
		startActivityForResult(intent, 1);
	}

	int tempPosForRemove;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("req "+requestCode +" resltcode "+resultCode );
		if(requestCode==1)
		{
			if(resultCode==Activity.RESULT_OK)
			{
				filterContactsPojos.remove(tempPosForRemove);
				adapter.notifyDataSetChanged();
				//LimoUtil.toast("remove "+tempPosForRemove,getActivity());
			}
		}
	}
	public static android.support.v4.app.Fragment newInstance() {
		ContactFragment fragment = new ContactFragment();

		return fragment;
	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		System.out.println("ContactFragment.onTaskComplete()");
	}

	private void performSearch(final String string) {
		// TODO Auto-generated method stub
	/*	for (int i = 0; i < existingContactsPojos.size(); i++) {
			String strname=existingContactsPojos.get(i).getFirstName().toLowerCase();
			if (strname
					.startsWith(string.toLowerCase())) {
				filterContactsPojos.add(existingContactsPojos.get(i));
			}
		}
		adapter.notifyDataSetChanged();*/
		new MyTask().execute(string);
		
		/*new AsyncTask<String, Void, String>() {

		}.execute("");*/
	}
	
	
	private class MyTask extends AsyncTask<String, Void, String>
	{

		protected void onPreExecute() {
			progressBar1.setVisibility(View.VISIBLE);
			filterContactsPojos=new ArrayList<ExistingContactsPojo>();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			// filterContactsPojos.add
			for (int i = 0; i < existingContactsPojos.size(); i++) {
				String strname=existingContactsPojos.get(i).getFirstName().toLowerCase();
				if (strname
						.startsWith(params[0])) {
					filterContactsPojos.add(existingContactsPojos.get(i));
				}
			}
			return null;
		}

		protected void onPostExecute(String result) {
			progressBar1.setVisibility(View.GONE);
			sortExistingContact();
			adapter = new ExistingContactsAdapter(ContactFragment.this,
					filterContactsPojos, container);
			listview.setAdapter(adapter);

			adapter.notifyDataSetChanged();
		}
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("ContactFragment.onResume()");
		if(adapter!=null	)
		{
			sortExistingContact();
			adapter.notifyDataSetChanged();
		}
	
	}
	
	
	@SuppressWarnings("unchecked")
	public  void sortExistingContact()
	{
		/*Person p = new Person("Bruce", "Willis");
	    Person p1  = new Person("Tom", "Hanks");
	    Person p2 = new Person("Nicolas","Cage");
	    Person p3 = new Person("John","Travolta");*/

	  
	    Collections.sort(filterContactsPojos, new Comparator(){

	        public int compare(Object o1, Object o2) {
	            ExistingContactsPojo p1 = (ExistingContactsPojo) o1;
	            ExistingContactsPojo p2 = (ExistingContactsPojo) o2;
	           return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
	        }

	    });
	    
	
		adapter = new ExistingContactsAdapter(ContactFragment.this,
				filterContactsPojos, container);
		listview.setAdapter(adapter);

		adapter.notifyDataSetChanged();
	}
}
