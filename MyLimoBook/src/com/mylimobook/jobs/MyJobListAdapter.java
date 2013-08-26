package com.mylimobook.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.mylimobook.JobDetails;
import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.pojos.AcceptedUsers;
import com.mylimobook.pojos.MyJobs;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.CustomAsynTaskJSON_PUT_forFragment;

public class MyJobListAdapter extends BaseAdapter {

	private ArrayList<MyJobs> list;
	Activity context;
	LayoutInflater mLayInflarer;
	ViewGroup viewGroup;

	Fragment fragment;

	public MyJobListAdapter(Activity context, Fragment fragment,
			ArrayList<MyJobs> myjobList, ViewGroup container) {
		this.list = myjobList;
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
	public MyJobs getItem(int arg0) {
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

		holder.textView_money.setText("$" + list.get(position).getExpAmt());
		String toaddress = "";
		if (list.get(position).getPSub() == null)// airport
		{
			toaddress =" "+list.get(position).getPFlightNo() + ","
					+ list.get(position).getPAirName();

			holder.textView_to.setCompoundDrawablesWithIntrinsicBounds(R.drawable.airoplane, 0, 0, 0);
		} else {
			toaddress = " "+list.get(position).getPSub();
			holder.textView_to.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_small, 0, 0, 0);
		}

		holder.textView_to.setText(toaddress);

		String dAddress = "";
		if (list.get(position).getDSub() == null)// airport
		{
			dAddress = " "+list.get(position).getDFlightNo() + ","
					+ list.get(position).getDAirName();
			holder.textView_destination.setCompoundDrawablesWithIntrinsicBounds(R.drawable.airoplane, 0, 0, 0);
			

		} else {
			dAddress = " "+list.get(position).getDSub();
			holder.textView_destination.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home_small, 0, 0, 0);
			
		}
		holder.textView_destination.setText(dAddress);

		// holder.textView_extra.setText("");
		 holder.textView_amount_1.setText("");

		/*
		 * 100 Open 110 Offloaded 120 Accepted 130 Confirmed 150 Swapped 200
		 * Cancelled
		 */
		// holder.textView_Button.setText(list.get(position).getJobStatusId()+"");
		switch (list.get(position).getJobStatusId()) {
		case 100:
			holder.textView_Button.setText("Offload");
			holder.textView_Button.setBackground(context.getResources().getDrawable(R.drawable.button_blue_small));
			holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
			
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
			
			if (list.get(position).getDSub() == null)// airport
			{
				holder.textView_Button.setBackground(context.getResources().getDrawable(R.drawable.offload_airport_mid));
				
				holder.textView_Button.setPadding(40, 0, 3, 0);
			} else {
				
				holder.textView_Button.setBackground(context.getResources().getDrawable(R.drawable.offload_home_small));
				holder.textView_Button.setPadding(40, 0, 3, 0);
			}
			
			break;
		case 110:
			holder.textView_Button.setText("Offloaded");
		
		//	holder.imageview_status.setImageDrawable(context.getResources().getDrawable(R.drawable.t_up));
			if(list.get(position).getAccUserCount()>0)
			{
				holder.textView_Button.setText("Confirm ("+list.get(position).getAccUserCount()+")");
				holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
				holder.textView_Button.setBackground(context.getResources().getDrawable(R.drawable.button_green_small));
				
			}
			else
			{
				holder.textView_Button.setText("Offloaded");
				holder.textView_Button.setBackground(null);
				holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
			}
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
			break;
		case 120:
			holder.textView_Button.setText("Accepted");
		
			//holder.textView_Button.setTextColor(context.getResources().getColor(R.color.purple)); 
			holder.textView_Button.setBackground(null);
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
			break;
		case 130:
			holder.textView_Button.setText("Confirmed");
			
		//	holder.textView_Button.setTextColor(Color.GREEN);
			holder.textView_Button.setBackground(null);
			if(list.get(position).getCUname()!=null)
			{
				holder.textView_extra.setText("To - "+list.get(position).getCUname()+" - "+list.get(position).getCRgNo());
				holder.textView_extra.setVisibility(View.VISIBLE);
			}
			
			break;
		case 150:
			holder.textView_Button.setText("Swapped");
	
			holder.textView_Button.setBackground(null);
		//	holder.textView_Button.setTextColor(context.getResources().getColor(R.color.orange));
			
			holder.textView_Button.setBackground(null);
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
			break;
		case 200:
			holder.textView_Button.setText("Cancelled");
		
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
			holder.textView_Button.setBackground(null);
			//holder.textView_Button.setTextColor(context.getResources().getColor(R.color.red));
			
			
			break;

		default:
			
			holder.textView_extra.setText("");
			holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
			holder.textView_extra.setVisibility(View.GONE);
			break;
		}
		holder.textView_Button.setTextColor(context.getResources().getColor(R.color.black));
/*
		if (list.get(position).getRecAmt() == null)
		{
			holder.textView_amount_1.setVisibility(View.GONE);

		} else {

			holder.textView_amount_1.setVisibility(View.VISIBLE);
			holder.textView_amount_1.setText("$ "
					+ list.get(position).getRecAmt());
		}
		*/
		
		holder.textView_amount_1.setVisibility(View.VISIBLE);
		holder.textView_amount_1.setText(LimoUtil.commisionAmount(list.get(position).getExpAmt()));
		
		// down and up arrow logic
	//	if(cUname not null than up arrow)
	//	if(pUname not null than down arrow)
		 if(list.get(position).getParUname()!=null && list.get(position).getParUname().trim().length()>0)
			{
				holder.imageview_status.setVisibility(View.VISIBLE);
				holder.imageview_status.setImageDrawable(context.getResources().getDrawable(R.drawable.t_down));
				holder.textView_extra.setText("By - "+list.get(position).getParUname()+" - "+list.get(position).getParRgNo());
				holder.textView_extra.setVisibility(View.VISIBLE);
			}
		 else if(list.get(position).getCUname()!=null && list.get(position).getCUname().trim().length()>0 )
		{
			holder.imageview_status.setVisibility(View.VISIBLE);
			holder.imageview_status.setImageDrawable(context.getResources().getDrawable(R.drawable.t_up));
			holder.textView_extra.setText("To - "+list.get(position).getCUname()+" - "+list.get(position).getCRgNo());
			holder.textView_extra.setVisibility(View.VISIBLE);
		}
		 
		else {
			holder.imageview_status.setVisibility(View.GONE);
			holder.imageview_status.setImageDrawable(null);
			
			holder.textView_extra.setText("");
			holder.textView_extra.setVisibility(View.GONE);
		}
		
		
		//------------
		
		holder.table_layout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i= new Intent(context, JobDetails.class);
				LimoUtil.JOB_FRAGMENT_ACTIVITY=(JobFragmentActivity) context;
				i.putExtra("POS", position);
				i.putExtra("ISFROMMYJOB", true);
				
				context.startActivity(i);
			}
		});
		holder.textView_Button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Toast.makeText(context, "" + position, 0).show();

				switch (list.get(position).getJobStatusId()) {
				case 100:

					offloadJob();

					break;
				case 110:

					//accepteThatJob();
					
					
					System.out.println("Confirm click");
					
					confirmWithPOPup();
					break;
				case 120:

					break;
				case 130:

					break;
				case 150:
					//150 means this job is sweeped

					break;
				case 200:

					break;

				default:
					break;
				}

				// context.startActivity(new Intent(context, JobDetails.class));
			}

			private void confirmWithPOPup() {
				// TODO Auto-generated method stub
			/*	Url: base url/api/ Job
				Action Verb: Get
				Input: Yes
				UserKey– Required
				JobId = Required*/

				new AsyncTask<String,Integer,String>() {

					ProgressDialog pd;
					protected void onPreExecute() {
						pd=ProgressDialog.show(context, "", "");
					}
					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						
						String url=LimoUtil.URL_JOB_GET+"?UserKey="+new UserPefrencePOJO().getUserKey(context)+"&JobId="+list.get(position).getJobId();
						
						return new WebAPIRequest().performGet(url);
					}
					protected void onPostExecute(String result) {
						pd.dismiss();
					
						
						try {
							JSONObject json= new JSONObject(result);
							String status=json.getString("Status");
							if(status.equalsIgnoreCase("Success"))
							{	final ArrayList<AcceptedUsers> aceAcceptedUsers= new ArrayList<AcceptedUsers>();
							
								JSONArray jsonary= json.getJSONObject("Data").getJSONArray("AcceptedUsers");
								for (int i = 0; i < jsonary.length(); i++) {
									aceAcceptedUsers.add(new AcceptedUsers(jsonary.getJSONObject(i).getString("Name"),jsonary.getJSONObject(i).getString("UserKey")));
								}
							
								
								String[] choiceList  = new String[aceAcceptedUsers.size()];
					
								for (int j = 0; j < aceAcceptedUsers.size(); j++) {
									choiceList[j]=aceAcceptedUsers.get(j).getName();
									System.out.println(j +" " +aceAcceptedUsers.get(j).getName());
								}
								
								AlertDialog.Builder alert = new AlertDialog.Builder(context);
								alert.setTitle("Select user to confirm job");
								
								alert.setSingleChoiceItems(choiceList, 0, null);
								alert.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										// TODO Auto-generated method stub
										  int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
								             System.out.println(selectedPosition);
								             
								             JSONObject json= new  JSONObject();
								             try {
								            	 json.put("UserKey",new UserPefrencePOJO().getUserKey(context));
								            	 json.put("ContactUserKey",aceAcceptedUsers.get(selectedPosition).getUserKey());
								            	 json.put("JobId",list.get(position).getJobId());
								            	 json.put("JobStatusId",LimoUtil.JOB_STATUS_ID_FOR_CONFIRMED);
								            	// context,fragment, 10, "Loading...", LimoUtil.URL_JOB_GET, json,position
												new CustomAsynTaskJSON_PUT_forFragment(context,fragment,4,"Loading...",LimoUtil.URL_JOB_GET,json,position).execute("");
												
											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
								             
								             
									}
								});
								alert.setNegativeButton("Cancel", null);
								alert.show();
								
								
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
				}.execute("");
				
			}

			private void accepteThatJob() {
				// TODO Auto-generated method stub
				
			}

			
			
			private void cancelJob()
			{
			
				 JSONObject json= new JSONObject();
				  try {
					json.put("UserKey", new UserPefrencePOJO().getUserKey(context));
				//	  json.put("ContactUserKey", new UserPefrencePOJO().getUserKey(context)); json.put("UserKey", new UserPefrencePOJO().getUserKey(context));
					 json.put("JobId", list.get(position).getJobId());
					 json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_CANCELLED);
						  
					  new CustomAsynTaskJSON_PUT_forFragment(fragment.getActivity(),fragment,3,"Loading...",LimoUtil.URL_JOB_GET, json,position).execute("");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			private void offloadJob() {
				// TODO Auto-generated method stub

			/*	AlertDialog.Builder alert = new AlertDialog.Builder(context);
				final ListView listview = new ListView(context);
				alert.setView(listview);
				if (LimoUtil.contactResponce != null
						&& LimoUtil.contactResponce.getData() != null
						&& LimoUtil.contactResponce.getData()
								.getExistingContacts() != null) 
				
				
				{
					final ArrayList<ExistingContactsPojo> filterContactsPojos = LimoUtil.contactResponce
							.getData().getExistingContacts();
					for (int i = 0; i < filterContactsPojos.size(); i++) {
						filterContactsPojos.get(i).setChecked(false);
					}
					
					ExistingContactsAdapter_withCheck adapter = new ExistingContactsAdapter_withCheck(
							fragment, filterContactsPojos, viewGroup);
					
					
				//	LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)listview.getLayoutParams();
					listview.setPadding(10, 10, 10, 10); //substitute parameters for left, top, right, bottom
				
					
					listview.setAdapter(adapter);

					alert.setPositiveButton("Select",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									for (int i = 0; i < filterContactsPojos.size(); i++) {
										if(filterContactsPojos.get(i).isChecked())
										{
											
										}
									}
								}
							});

					alert.setNegativeButton("Cancel", null);
					alert.show();

				}
				else
				{
					new CustomAsynTaskGET(fragment.getActivity(), 2, "Loading...")
					.execute(LimoUtil.URL_Contacts
							+ "?"
							+ "UserKey="
							+ new UserPefrencePOJO()
									.getUserKey(context));
					
					
					new AsyncTask<String , Integer, String>()
					{
						ProgressDialog pd;
						protected void onPreExecute() {
							pd=ProgressDialog.show(context, "", "Contact list fatching...");
						}

						@Override
						protected String doInBackground(String... params) {
							// TODO Auto-generated method stub
							 return new WebAPIRequest().performGet(params[0]);
						}
						protected void onPostExecute(String result) {
							pd.dismiss();
							if (result != "Exception") {

								Gson gson = new Gson();
								LimoUtil.contactResponce = gson.fromJson(result, ContactResponce.class);
								offloadJob();

							}
							
						}
					}.execute(LimoUtil.URL_Contacts
							+ "?"
							+ "UserKey="
							+ new UserPefrencePOJO()
									.getUserKey(context));
				}*/

				
				  JSONObject json= new JSONObject();
				  try {
					json.put("UserKey", new UserPefrencePOJO().getUserKey(context));
				//	  json.put("ContactUserKey", new UserPefrencePOJO().getUserKey(context)); json.put("UserKey", new UserPefrencePOJO().getUserKey(context));
					 json.put("JobId", list.get(position).getJobId());
					 json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD);
						  
					  new CustomAsynTaskJSON_PUT_forFragment(fragment.getActivity(),fragment,2,"Loading...",LimoUtil.URL_JOB_GET, json,position).execute("");
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				 
			}
		});
		
		if(position%2==0)
		{
			convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
		}
		else
		{
			convertView.setBackgroundColor(context.getResources().getColor(R.color.myjob_row_color));
			}
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
