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
import com.mylimobook.pojos.NetworkJobs;
import com.mylimobook.task.AsyncTaskCompleteListenerPosition;

public class NetworkFragment extends Fragment implements
		AsyncTaskCompleteListenerPosition {
	public static ArrayList<NetworkJobs> networklist = new ArrayList<NetworkJobs>();
	NetworkJobListAdapter adapter;

	@Override
	public boolean getUserVisibleHint() {
		// TODO Auto-generated method stub
		System.out.println("MyJobFragment.getUserVisibleHint()");
		return super.getUserVisibleHint();
	}

	private void textvieTitleUpdate() {
		// TODO Auto-generated method stub

		String DATE_FORMAT_NOW = "MMM EEEE";

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);

		int i = cal.get(Calendar.DAY_OF_MONTH);
		String dateformate = LimoUtil.setNumberFormat(i) + " "
				+ sdf.format(cal.getTime());

		String str = dateformate + "   ( " + networklist.size() + " Jobs )";

		if (textView1 != null) {
			textView1.setText(str);

		}

	}

	@Override
	public View getView() {
		// TODO Auto-generated method stub
		System.out.println("MyJobFragment.getView()");
		return super.getView();
	}

	TextView textView1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		System.out.println("MyJobFragment.onCreateView()");

		View viewMain = inflater.inflate(
				com.mylimobook.R.layout.networkjobfragment, null);
		// View viewMain = inflater.inflate(R.layout.quate_fragmemt, null);
		// initView(viewMain);

		textView1 = (TextView) viewMain.findViewById(R.id.textView1);
		textvieTitleUpdate();
		ListView listview = (ListView) viewMain.findViewById(R.id.listView1);

		if (((JobFragmentActivity) getActivity()).networkJobList != null) {
			networklist = ((JobFragmentActivity) getActivity()).networkJobList;

			if (networklist != null) {
				adapter = new NetworkJobListAdapter(getActivity(), this,
						networklist, container);
				listview.setAdapter(adapter);

				FrameLayout frame = (FrameLayout) viewMain
						.findViewById(R.id.frame);
				if (networklist.size() == 0) {
					frame.setVisibility(View.VISIBLE);
				} else {
					frame.setVisibility(View.GONE);
				}

			}

		}

		return viewMain;
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

	@Override
	public void onTaskComplete(String result, int taskId, int position) {
		// TODO Auto-generated method stub
		if (result.equalsIgnoreCase("Exception") == false)
		// task id 1 for accept job
		{/*
		 * Status = "Error" | Data = NULL | Message => Array (1) | ( | | ['0'] =
		 * "You has been accepted a job successfully" | )
		 */
			try {
				JSONObject json = new JSONObject(result);
				String status = "";
				String message = "";
				status = json.optString("Status");
				message = json.getJSONArray("Message").getString(0);
				if (status.equalsIgnoreCase("Success")) {
					networklist.get(position).setIsAccepted(true);
					adapter.notifyDataSetChanged();
				}
				// LimoUtil.toast(message, this.getActivity());
				textvieTitleUpdate();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static android.support.v4.app.Fragment newInstance() {
		NetworkFragment fragment = new NetworkFragment();

		/*
		 * StringBuilder builder = new StringBuilder(); for (int i = 0; i < 20;
		 * i++) { builder.append(content).append(" "); }
		 * builder.deleteCharAt(builder.length() - 1); fragment.mContent =
		 * builder.toString();
		 */

		return fragment;
	}

}
