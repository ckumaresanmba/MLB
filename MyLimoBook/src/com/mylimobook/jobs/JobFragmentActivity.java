package com.mylimobook.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import android.annotation.SuppressLint;
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
import com.mylimobook.pojos.MyJobResponcePOJO;
import com.mylimobook.pojos.MyJobs;
import com.mylimobook.pojos.NetworkJobResponcePOJO;
import com.mylimobook.pojos.NetworkJobs;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;
import com.viewpagerindicator.TabPageIndicator;

public class JobFragmentActivity extends FragmentActivity implements
		OnClickListener, AsyncTaskCompleteListener {
	private static final String[] CONTENT = new String[] { "My Jobs",
			"Network Jobs" };

	FragmentPagerAdapter adapter;
	ViewPager pager;
	ImageButton imagebutton_AddJob, imageButton1_refresh;

	public static ArrayList<MyJobs> myjobList;
	public static ArrayList<NetworkJobs> networkJobList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_fragment);

		adapter = new GoogleMusicAdapter(getSupportFragmentManager());

		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		imagebutton_AddJob = (ImageButton) findViewById(R.id.imagebutton_AddJob);
		imagebutton_AddJob.setOnClickListener(this);

		imageButton1_refresh = (ImageButton) findViewById(R.id.imageButton1_refresh);
		imageButton1_refresh.setOnClickListener(this);

		ImageButton imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
		imageButton_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				JobFragmentActivity.this.finish();
				// startActivity(new Intent(JobFragmentActivity.this ,
				// DashBoardActivity.class));
			}
		});

		if (myjobList == null) {
			System.out.println("myjobList is null");
			myJobRefresh();
		}
		if (networkJobList == null) {
			System.out.println("network is null");

			netWorkJobRefresh();
		}

	}

	public void myJobRefresh() {
		// TODO Auto-generated method stub
		String url = LimoUtil.URL_JOB_GET + "?UserKey="
				+ new UserPefrencePOJO().getUserKey(JobFragmentActivity.this)
				+ "&JobTypeId=" + LimoUtil.mYJOB_TYPE_ID + "&PageSize="
				+ LimoUtil.mYPAGE_SIZE + "&PageIndex=" + LimoUtil.mYPAGE_INDEX;
		new CustomAsynTaskGET(JobFragmentActivity.this, 1, "Fetching jobs...")
				.execute(url);
	}

	private void netWorkJobRefresh() {
		// TODO Auto-generated method stub

		String url = LimoUtil.URL_JOB_GET + "?UserKey="
				+ new UserPefrencePOJO().getUserKey(JobFragmentActivity.this)
				+ "&JobTypeId=" + LimoUtil.Network_JOB_TYPE_ID + "&PageSize="
				+ LimoUtil.Network_PAGE_SIZE + "&PageIndex="
				+ LimoUtil.Network_PAGE_INDEX;
		new CustomAsynTaskGET(JobFragmentActivity.this, 2, "Fetching jobs...")
				.execute(url);

	}

	class GoogleMusicAdapter extends FragmentPagerAdapter {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			switch (position) {
			case 0: // Zero Position means Myjob Fragment
				return MyJobFragment.newInstance();
			case 1: // Zero Position means Myjob Fragment
				return NetworkFragment.newInstance();

			}

			return MyJobFragment.newInstance();
		}

		@SuppressLint("DefaultLocale")
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.imagebutton_AddJob:

			LimoUtil.JOB_FRAGMENT_ACTIVITY = this;
			startActivity(new Intent(JobFragmentActivity.this, AddJob.class));

			break;
		case R.id.imageButton1_refresh:

			myJobRefresh();
			netWorkJobRefresh();
			break;

		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		LimoUtil.JOB_FRAGMENT_ACTIVITY = null;
	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		switch (taskId) {
		case 1:
			if (result.equalsIgnoreCase("Exception") == false) {
				// LimoUtil.toast(result, context)

				Gson gson = new Gson();
				MyJobResponcePOJO responce = gson.fromJson(result,
						MyJobResponcePOJO.class);

				if (responce.getStatus().equalsIgnoreCase("Success")) {

					myjobList = responce.getData().getMyJobs();

					sortDatewiseJob(myjobList);
					adapter = new GoogleMusicAdapter(
							getSupportFragmentManager());

					TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
					indicator.setViewPager(pager, 0);
					pager.setAdapter(adapter);

				}

			}

			break;

		case 2:
			if (result != "Exception") {// network job parsing
				Gson gson = new Gson();
				NetworkJobResponcePOJO responce = gson.fromJson(result,
						NetworkJobResponcePOJO.class);

				if (responce.getStatus().equalsIgnoreCase("Success")) {
					networkJobList = responce.getData().getNetworkJobs();

					adapter = new GoogleMusicAdapter(
							getSupportFragmentManager());

					pager.setAdapter(adapter);
					TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
					indicator.setViewPager(pager);

				}

			}

			break;

		default:
			break;
		}
	}

	private void sortDatewiseJob(ArrayList<MyJobs> myjobList12) {
		// TODO Auto-generated method stub
		
		

		final SimpleDateFormat originalFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");

	Collections.sort(myjobList12,
				new Comparator<MyJobs>() {

					@SuppressWarnings("deprecation")
					@Override
					public int compare(MyJobs lhs,
							MyJobs rhs) {

					
							try {
								if ( originalFormat.parse(lhs.getBookStDt()).getTime() >  originalFormat.parse(rhs.getBookStDt()).getTime())

									return -1;

								else if ( originalFormat.parse(lhs.getBookStDt()).getTime() ==  originalFormat.parse(rhs.getBookStDt()).getTime())

									return 0;
								else
									return 1;
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								return 1;
							}
						

					}

				});

		
		
	}
}
