package com.mylimobook.jobs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.pojos.MyJobs;
import com.mylimobook.task.AsyncTaskCompleteListenerPosition;

public class MyJobFragment extends Fragment implements AsyncTaskCompleteListenerPosition{
	public static ArrayList<MyJobs> list= new ArrayList<MyJobs>();
	MyJobListAdapter adapter;
	
	@Override
	public boolean getUserVisibleHint() {
		// TODO Auto-generated method stub
		System.out.println("MyJobFragment.getUserVisibleHint()");
		
		return super.getUserVisibleHint();
	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		System.out.println("MyJobFragment.getView()");
		return super.getView();
	}
	
	
	TextView textView_title;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
			
		
		System.out.println("MyJobFragment.onCreateView()");
	
		View viewMain= inflater.inflate(com.mylimobook.R.layout.myjobfragment,null);
		//View viewMain = inflater.inflate(R.layout.quate_fragmemt, null);
		//initView(viewMain);
		
		textView_title= (TextView)viewMain.findViewById(R.id.textView1);
		
		textvieTitleUpdate();
		
		ListView listview= (ListView)viewMain.findViewById(R.id.listView1);
		
		if(((JobFragmentActivity)getActivity()).myjobList!=null)
		{
		 list = ((JobFragmentActivity)getActivity()).myjobList;
			
			if(list!=null)
			{
			adapter=	new MyJobListAdapter(getActivity(),this,list, container);
				listview.setAdapter( adapter);
				
				FrameLayout frame= (FrameLayout) viewMain.findViewById(R.id.frame);
				if(list.size()==0)
				{
					frame.setVisibility(View.VISIBLE);
				}
				else
				{
					frame.setVisibility(View.GONE);
				}
				
			}
			
		}
		
		
		return viewMain;
		}

	private void textvieTitleUpdate() {
		// TODO Auto-generated method stub
		
		
		String DATE_FORMAT_NOW = "MMM EEEE";

		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		
		
		
		int count=0;
		
		for (int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getJobStatusId()==LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD)
			{
				count++;
			}
			
		}
		int i=	cal.get(Calendar.DAY_OF_MONTH);
		String dateformate= LimoUtil.setNumberFormat(i)+" "+sdf.format(cal.getTime());
	
		
		String str=dateformate+"   ( "+ list.size()+" - "+getString(R.string.uparrowText)+" "+count+" )";
	
	
	
		if(textView_title!=null)
		{	textView_title.setText(str);
			
		}
	
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("MyJobFragment.onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		System.out.println("MyJobFragment.onDestroyView()");
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		System.out.println("MyJobFragment.onDetach()");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("MyJobFragment.onPause()");
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("MyJobFragment.onResume()");
		textvieTitleUpdate();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("MyJobFragment.onStart()");
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("MyJobFragment.onStop()");
	}

	public static android.support.v4.app.Fragment newInstance() {
		MyJobFragment fragment = new MyJobFragment();

		/*
		 * StringBuilder builder = new StringBuilder(); for (int i = 0; i < 20;
		 * i++) { builder.append(content).append(" "); }
		 * builder.deleteCharAt(builder.length() - 1); fragment.mContent =
		 * builder.toString();
		 */

		return fragment;
	}

	@Override
	public void onTaskComplete(String result, int taskId, int position) {
		// TODO Auto-generated method stub
		if(result.equalsIgnoreCase("Exception")==false)
		{
			switch (taskId) {
			case 2:
				try {
					JSONObject json = new JSONObject(result);
					String status="";
					String message="";
					status = json.optString("Status");
					message=json.getJSONArray("Message").getString(0);
				//:{"Status":"Success","Data":null,"Message":["No contacts to offload this jobs"]}

					if(status.equalsIgnoreCase("Success")  && message.startsWith("No contacts")==false)
							{
								list.get(position).setJobStatusId(LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD);
								adapter.notifyDataSetChanged();
							}
					LimoUtil.toast(message, MyJobFragment.this.getActivity());
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 3:// Cancel job
				try {
					JSONObject json = new JSONObject(result);
					String status="";
					String message="";
					status = json.optString("Status");
					message=json.getJSONArray("Message").getString(0);
					if(status.equalsIgnoreCase("Success"))
							{
								list.get(position).setJobStatusId(LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD);
								adapter.notifyDataSetChanged();
							}
					LimoUtil.toast(message, MyJobFragment.this.getActivity());
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:// ConfirmJob job
				try {
					JSONObject json = new JSONObject(result);
					String status="";
					String message="";
					status = json.optString("Status");
					message=json.getJSONArray("Message").getString(0);
					if(status.equalsIgnoreCase("Success"))
							{
								list.get(position).setJobStatusId(LimoUtil.JOB_STATUS_ID_FOR_CONFIRMED);
								adapter.notifyDataSetChanged();
							}
					LimoUtil.toast(message, MyJobFragment.this.getActivity());
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;


			default:
				break;
			}
			
			
		}
	}

}
