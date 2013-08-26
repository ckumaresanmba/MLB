package com.mylimobook.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.pojos.NetworkJobs;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.CustomAsynTaskJSON_PUT_forFragment;

public class NetworkJobListAdapter extends BaseAdapter {

	private ArrayList<NetworkJobs> list;
	Context context;
	LayoutInflater mLayInflarer;
	ViewGroup viewGroup;

	Fragment fragment;

	public NetworkJobListAdapter(Context context, Fragment fragment,
			ArrayList<NetworkJobs> networklist, ViewGroup container) {
		this.list = networklist;
		this.context = context;
		mLayInflarer = LayoutInflater.from(context);
		viewGroup = container;
		this.fragment = fragment;
	}

	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public NetworkJobs getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayInflarer.inflate(R.layout.job_row_new, null);

			holder.textView_time = (TextView) convertView
					.findViewById(R.id.textView_time);

			holder.textView_money = (TextView) convertView
					.findViewById(R.id.textView_money);

			holder.textView_to = (TextView) convertView
					.findViewById(R.id.textView_to);

			holder.textView_destination = (TextView) convertView
					.findViewById(R.id.textView_destination);

			holder.textView_extra = (TextView) convertView
					.findViewById(R.id.textView_extra);

			holder.textView_amount_1 = (TextView) convertView
					.findViewById(R.id.textView_amount_1);

			holder.textView_Button = (TextView) convertView
					.findViewById(R.id.textView_Button);

			holder.imageview_status = (ImageView) convertView
					.findViewById(R.id.imageview_status);

			
			holder.table_layout = (TableLayout) convertView
					.findViewById(R.id.table_layout);

			
			
			
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		
		
		// 2013-07-31T11:15:00
		Date javaUtilDate = new Date();
		SimpleDateFormat originalFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat targetFormat = new SimpleDateFormat("h:mm a");
		Date date;
		try {
			date = originalFormat.parse(list.get(position).getBookStDt());
			System.out.println(originalFormat.format(date));
			holder.textView_time.setText(targetFormat.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		holder.textView_money.setText("$ " + list.get(position).getAmount());
		String toaddress = "";
		if (list.get(position).getPSub() == null)// airport
		{
			toaddress = list.get(position).getPFlightNo() + ","
					+ list.get(position).getPAirName();

		} else {
			toaddress = list.get(position).getPSub();
		}

		holder.textView_to.setText(toaddress);

		String dAddress = "";
		if (list.get(position).getDSub() == null)// airport
		{
			dAddress = list.get(position).getDFlightNo() + ","
					+ list.get(position).getDAirName();

		} else {
			dAddress = list.get(position).getDSub();
		}
		holder.textView_destination.setText(dAddress);

		// holder.textView_extra.setText("");
		// holder.textView_amount_1.setText("");

		/*
		 * 100 Open 110 Offloaded 120 Accepted 130 Confirmed 150 Swapped 200
		 * Cancelled
		 */
		// holder.textView_Button.setText(list.get(position).getJobStatusId()+"");
		
		
		     if(list.get(position).isIsCovered())
		    {
		       
		    	holder.textView_Button.setText("Covered");
		    	holder.textView_Button.setEnabled(false);
		    	holder.textView_Button.setBackground(null);
		    	holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
		    
		      }
		    else if(list.get(position).isIsAccepted()){
		    	holder.textView_Button.setText("Accepted");
		    	holder.textView_Button.setEnabled(false);
		    	holder.textView_Button.setBackground(null);
		    	holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
		    	
		    	
		    }
		    else{
		    	holder.textView_Button.setText("Accept");
		    	holder.textView_Button.setBackground(context.getResources().getDrawable(R.drawable.accept_small));
		    	android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams)holder.textView_Button.getLayoutParams(); 
		    	params.gravity = Gravity.RIGHT;
		    	holder.textView_Button.setLayoutParams(params);
		    	holder.textView_Button.setEnabled(true);
		     	holder.textView_Button.setTextColor(context.getResources().getColor(R.color.white));
		}
		
		holder.imageview_status.setVisibility(View.GONE);
		holder.textView_amount_1.setVisibility(View.GONE);
		
	
		holder.textView_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Toast.makeText(context, "" + position, 0).show();

				if(holder.textView_Button.getText().toString().equalsIgnoreCase("Accept"))
				{
					/*Url: Base Url/api/Job
					Action Verb: PUT
					Input: Yes
					public class JobStatusChange
					{
					public Guid UserKey { get; set; } // Required
					public Guid? ContactUserKey { get; set; } // Required
					public int JobId { get; set; } // Required
					public int? JobStatusId { get; set; } // Required and JobStatusId should be 120
					}
					*/
					
					JSONObject json = new  JSONObject();
					try {
						json.put("UserKey", new UserPefrencePOJO().getUserKey(context));
						json.put("JobId",list.get(position).getJobId());
						json.put("JobStatusId",LimoUtil.JOB_STATUS_ID_FOR_ACCEPTED);
						json.put("ContactUserKey",list.get(position).getUserKey());
						
						new CustomAsynTaskJSON_PUT_forFragment(fragment.getActivity(), fragment, 1, "Loading...",
								LimoUtil.URL_JOB_GET, json, position).execute("");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			

				// context.startActivity(new Intent(context, JobDetails.class));
			}

			
		});
		return convertView;
	}

	public static class ViewHolder {
		/*
		 * TextView textView_amt1, tv_name, tv_address, textView_day, tv_date,
		 * tv_time, but1_for_confirm_offload; LinearLayout linlistmidle;
		 */

		TextView textView_time, textView_money, textView_to,
				textView_destination, textView_extra, textView_amount_1,
				textView_Button;
		ImageView imageview_status;
		
		TableLayout table_layout;

	}

	

}
