package com.mylimobook.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.SearchContact;
import com.mylimobook.pojos.ContactResponce;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;
import com.viewpagerindicator.TabPageIndicator;

public class ContactFragmentActivity extends FragmentActivity implements
		OnClickListener, AsyncTaskCompleteListener {
	 static final String[] CONTENT= new String[] { "Contacts",
			"Requests" };

	ImageButton imagebutton_addContact, imageButton_refresh,imageButton_back;

	ViewPager pager;
	
	FragmentPagerAdapter adapter;
	TabPageIndicator indicator ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_fragment);

		pager = (ViewPager) findViewById(R.id.pager);

		adapter = new GoogleMusicAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);

		indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		imagebutton_addContact = (ImageButton) findViewById(R.id.imagebutton_addContact);
		imagebutton_addContact.setOnClickListener(this);

		imageButton_refresh = (ImageButton) findViewById(R.id.imageButton_refresh);
		imageButton_refresh.setOnClickListener(this);


		imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
		imageButton_back.setOnClickListener(this);

		
		
		getAllContacts();
	}

	private void getAllContacts() {
		// TODO Auto-generated method stub

		new CustomAsynTaskGET(ContactFragmentActivity.this, 1, "Loading...")
				.execute(LimoUtil.URL_Contacts
						+ "?"
						+ "UserKey="
						+ new UserPefrencePOJO()
								.getUserKey(ContactFragmentActivity.this));

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(LimoUtil.contactResponce!=null && LimoUtil.contactResponce.getData()!=null && LimoUtil.contactResponce.getData().getPendingContacts()!=null )
		{
			if(adapter!=null)
			{
				CONTENT[1]="Requests"+" ("+LimoUtil.contactResponce.getData().getPendingContacts().size()+")";
				adapter.notifyDataSetChanged();
			}
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imageButton_refresh:
			getAllContacts();
			indicator.setTextToTab(0);
			
			break;
		case R.id.imagebutton_addContact:

			startActivity(new Intent(ContactFragmentActivity.this,
					SearchContact.class));

			break;
		case R.id.imageButton_back:

			ContactFragmentActivity.this.finish();

			break;
		default:
			break;
		}
	}

	// _____________________________

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			switch (position) {
			case 0: // Zero Position means Contact Fragment
				return ContactFragment.newInstance();
			case 1: // Zero Position means Pending Fragment
				return PendingContactFragment.newInstance();

			}

			return ContactFragment.newInstance();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return CONTENT[position % CONTENT.length].toUpperCase();
		}

		
		@Override
		public int getCount() {
			return CONTENT.length;
		}
	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		switch (taskId) {
		case 1:// Task id 1 get Contact
			if (result != "Exception") {

				Gson gson = new Gson();
				LimoUtil.contactResponce = gson.fromJson(result, ContactResponce.class);

				adapter = new GoogleMusicAdapter(getSupportFragmentManager());
			
				indicator.setViewPager(pager,0);
				pager.setAdapter(adapter);
				CONTENT[1]="Requests"+" ("+LimoUtil.contactResponce.getData().getPendingContacts().size()+")";
				adapter.notifyDataSetChanged();
			}

			break;

		default:
			break;
		}
	}

}
