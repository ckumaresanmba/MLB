package com.mylimobook;

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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.jobs.MyJobFragment;
import com.mylimobook.pojos.AcceptedUsers;
import com.mylimobook.pojos.ContactResponce;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.pojos.JobInfoResponce;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;
import com.mylimobook.task.CustomAsynTaskJSON_PUT;

public class JobDetails extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	TextView button_back, textView1_title, textView_subTitle;
	ImageButton button_up, button_down, imageButton_refresh,image_buttonEdit;
	ImageView imageView_returnPickup;

	TextView textView_pickup_line1, textView_pickup_addressline2,
			TextView_destinationLine1, TextView_destinationLine2, tv_amt,
			textView_amount_1;

	Button buttonDetails, button_Status, buttonNotes, buttonPayment,
			buttonMore;

	TableLayout table__details, table_status, table_more, table_payment,
			table_Notes;

	// Detail text
	TextView cust_name, tv_mobile, tv_Passengers, tv_babyseat, tv_speedNeed;

	// Status text
	TextView tv_eventType, tv_notes;

	Button button_offload, button_accept, button_confirm, buttonSwap,
			button_cancel;

	TextView TextView_bootom_info;

	int position = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_details);
		initView();

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		position = getIntent().getIntExtra("POS", 0);
		// LimoUtil.toast(position + "", JobDetails.this);
		refreshAll();
	}

	private void setDateonTitleandsubTextview() {
		// TODO Auto-generated method stub

		// Calendar cal = Calendar.getInstance();
		// SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String strdate = jobInfoResponce.getData().getJobInfo().getBookStDt();
		// textView1_title.setText();
		// Date fds = sdf.parse(strdate);

		// 2013-07-31T11:15:00
		Date javaUtilDate = new Date();
		SimpleDateFormat originalFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat targetFormatTitle = new SimpleDateFormat(
				"MMM h:mm a");
		
		SimpleDateFormat targetFormatFORDATE = new SimpleDateFormat(
				"d");
		
		
		
		
		
		
		SimpleDateFormat targetFormatSubTitle = new SimpleDateFormat(
				"MMM EEE h:mm a");
		Date date;
		try {
			date = originalFormat.parse(strdate);

			
			
			
			int dateNumber= Integer.parseInt(targetFormatFORDATE.format(date));
			
			String title=	LimoUtil.setNumberFormat(dateNumber)+" "+targetFormatTitle.format(date);
			String subtitle=	LimoUtil.setNumberFormat(dateNumber)+" "+targetFormatSubTitle.format(date);
			textView1_title.setText(title);
			textView_subTitle.setText(subtitle);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		
	}

	private void initView() {
		// TODO Auto-generated method stub

		button_back = (TextView) findViewById(R.id.button_back);
		button_back.setOnClickListener(this);

		textView1_title = (TextView) findViewById(R.id.textView1_title);
		image_buttonEdit = (ImageButton) findViewById(R.id.image_buttonEdit);
		image_buttonEdit.setOnClickListener(this);
		textView_subTitle = (TextView) findViewById(R.id.textView_subTitle);

		button_up = (ImageButton) findViewById(R.id.button_up);
		button_up.setOnClickListener(this);

		button_down = (ImageButton) findViewById(R.id.button_down);
		imageButton_refresh = (ImageButton) findViewById(R.id.imageButton_refresh);

		button_down.setOnClickListener(this);
		imageButton_refresh.setOnClickListener(this);

		imageView_returnPickup = (ImageView) findViewById(R.id.imageView_returnPickup);

		textView_pickup_line1 = (TextView) findViewById(R.id.textView_pickup_line1);
		textView_pickup_addressline2 = (TextView) findViewById(R.id.textView_pickup_addressline2);
		TextView_destinationLine1 = (TextView) findViewById(R.id.TextView_destinationLine1);
		TextView_destinationLine2 = (TextView) findViewById(R.id.TextView_destinationLine2);
		tv_amt = (TextView) findViewById(R.id.tv_amt);
		textView_amount_1 = (TextView) findViewById(R.id.textView_amount_1);

		buttonDetails = (Button) findViewById(R.id.buttonDetails);
		button_Status = (Button) findViewById(R.id.button_Status);
		buttonNotes = (Button) findViewById(R.id.buttonNotes);
		buttonPayment = (Button) findViewById(R.id.buttonPayment);
		buttonMore = (Button) findViewById(R.id.buttonMore);

		buttonDetails.setOnClickListener(this);
		button_Status.setOnClickListener(this);
		buttonNotes.setOnClickListener(this);
		buttonPayment.setOnClickListener(this);

		buttonMore.setOnClickListener(this);

		table__details = (TableLayout) findViewById(R.id.table__details);

		table_status = (TableLayout) findViewById(R.id.table_status);

		table_more = (TableLayout) findViewById(R.id.table_more);
		table_payment = (TableLayout) findViewById(R.id.table_payment);
		table_Notes = (TableLayout) findViewById(R.id.table_Notes);

		setbuttonEnable(true);
		hideAlltable();
		buttonDetails.setEnabled(false);
		table__details.setVisibility(View.VISIBLE);

		button_offload = (Button) findViewById(R.id.button_offload);
		button_accept = (Button) findViewById(R.id.button_accept);
		button_confirm = (Button) findViewById(R.id.button_confirm);
		buttonSwap = (Button) findViewById(R.id.buttonSwap);
		button_cancel = (Button) findViewById(R.id.button_cancel);

		TextView_bootom_info = (TextView) findViewById(R.id.TextView_bootom_info);
		cust_name = (TextView) findViewById(R.id.cust_name);
		tv_mobile = (TextView) findViewById(R.id.tv_mobile);
		tv_Passengers = (TextView) findViewById(R.id.tv_Passengers);
		tv_babyseat = (TextView) findViewById(R.id.tv_babyseat);
		tv_speedNeed = (TextView) findViewById(R.id.tv_speedNeed);

		tv_eventType = (TextView) findViewById(R.id.tv_eventType);
		tv_notes = (TextView) findViewById(R.id.tv_notes);

		button_offload.setOnClickListener(this);
		button_accept.setOnClickListener(this);
		buttonSwap.setOnClickListener(this);
		button_cancel.setOnClickListener(this);
		button_confirm.setOnClickListener(this);

		button_offload.setVisibility(View.GONE);
		button_accept.setVisibility(View.GONE);
		button_confirm.setVisibility(View.GONE);
		buttonSwap.setVisibility(View.GONE);
		button_cancel.setVisibility(View.GONE);
		TextView_bootom_info.setVisibility(View.GONE);

	}

	private void hideAlltable() {
		// TODO Auto-generated method stub
		table__details.setVisibility(View.GONE);
		table_status.setVisibility(View.GONE);
		table_more.setVisibility(View.GONE);
		table_payment.setVisibility(View.GONE);
		table_Notes.setVisibility(View.GONE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_back:
			this.finish();

			break;
		case R.id.image_buttonEdit:

			Intent i = new Intent(JobDetails.this, EditJob.class);
			// Gson gson = new Gson();
			// String str=gson.toJson(jobInfoResponce);
			i.putExtra("JobId", jobInfoResponce.getData().getJobInfo()
					.getJobId());
			
			LimoUtil.JOB_DETAILS=this;
			startActivity(i);

			break;
		case R.id.button_up:

			// privious
			if (position > 0) {
				position--;
				refreshAll();
				button_down.setBackground(getResources().getDrawable(R.drawable.down_unselect));
				if(position==0)
				{
					button_up.setBackground(getResources().getDrawable(R.drawable.up_select));
				}
			}
		
			break;
		case R.id.button_down:
			// next
			if (position < (MyJobFragment.list.size() - 1)) {
				position++;
				refreshAll();
				button_up.setBackground(getResources().getDrawable(R.drawable.up_unselect));
				
				if(position == (MyJobFragment.list.size() - 1))
				{
					button_down.setBackground(getResources().getDrawable(R.drawable.down_select));
				}
			}

			break;
		case R.id.imageButton_refresh:

			refreshAll();
			break;
		case R.id.buttonDetails:
			setbuttonEnable(true);
			v.setEnabled(false);
			hideAlltable();
			table__details.setVisibility(View.VISIBLE);

			break;
		case R.id.button_Status:
			setbuttonEnable(true);
			v.setEnabled(false);

			hideAlltable();
			table_status.setVisibility(View.VISIBLE);

			break;
		case R.id.buttonNotes:
			setbuttonEnable(true);
			v.setEnabled(false);
			hideAlltable();
			table_Notes.setVisibility(View.VISIBLE);

			break;
		case R.id.buttonPayment:
			setbuttonEnable(true);
			v.setEnabled(false);
			hideAlltable();
			table_payment.setVisibility(View.VISIBLE);

			break;
		case R.id.buttonMore:
			setbuttonEnable(true);
			v.setEnabled(false);
			hideAlltable();
			table_more.setVisibility(View.VISIBLE);

			break;

		case R.id.button_offload:

			/*
			 * public class JobStatusChange { public Guid UserKey { get; set; }
			 * // Required public Guid? ContactUserKey { get; set; } // Not
			 * required public int JobId { get; set; } // Required public int?
			 * JobStatusId { get; set; } // Required and JobStatusId should be
			 * 110 }
			 */

			offLoadJob();

			break;

		case R.id.button_accept:
			acceptJob();
			break;
		case R.id.buttonSwap:
			swapJob();
			break;
		case R.id.button_cancel:
			cancelJob();
			break;
		case R.id.button_confirm:
			confirmWithPOPup();
			break;

		default:
			break;
		}
	}

	private void cancelJob() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		AlertDialog.Builder alert = new AlertDialog
				.Builder(JobDetails.this);
		alert.setMessage("Click Yes to cancel this job.");
		alert.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				JSONObject json = new JSONObject();
				try {
					json.put("UserKey", new UserPefrencePOJO().getUserKey(JobDetails.this));
					// json.put("ContactUserKey", new
					// UserPefrencePOJO().getUserKey(context)); json.put("UserKey", new
					// UserPefrencePOJO().getUserKey(context));
					json.put("JobId", jobInfoResponce.getData().getJobInfo().getJobId());
					json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_CANCELLED);

					new CustomAsynTaskJSON_PUT(JobDetails
							.this, 2, "Loading...",
							LimoUtil.URL_JOB_GET, json).execute("");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		alert.setNegativeButton("No", null);
		alert.show()
		;
		
	
	}

	private void swapJob() {
		// TODO Auto-generated method stub
		
		
		/*
		 * JSONObject json = new JSONObject(); try { json.put("UserKey", new
		 * UserPefrencePOJO().getUserKey(this)); json.put("ContactUserKey",
		 * jobInfoResponce.getData().getJobInfo().getc
		 * .get(position).getUserKey()); json.put("JobId",
		 * jobInfoResponce.getData().getJobInfo().getJobId());
		 * json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_SWAPPED);
		 * 
		 * new CustomAsynTaskJSON_PUT(this, 2, "Loading...",
		 * LimoUtil.URL_JOB_GET, json).execute("");
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	
		
		
		
		
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
			
			swapDialog(filterContactsPojos);
		}
		else
		{
			new CustomAsynTaskGET(JobDetails.this, 2, "Loading...")
			.execute(LimoUtil.URL_Contacts
					+ "?"
					+ "UserKey="
					+ new UserPefrencePOJO()
							.getUserKey(JobDetails.this));
			
			
			new AsyncTask<String , Integer, String>()
			{
				ProgressDialog pd;
				protected void onPreExecute() {
					pd=ProgressDialog.show(JobDetails.this, "", "Contact list fetching...");
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
						swapDialog();
						
						
						swapDialog(LimoUtil.contactResponce.getData().getExistingContacts());
					}
					
				}

			
			}.execute(LimoUtil.URL_Contacts
					+ "?"
					+ "UserKey="
					+ new UserPefrencePOJO()
							.getUserKey(JobDetails.this));
		
	}
		
		
		
	}

	private void swapDialog(final ArrayList<ExistingContactsPojo> filterContactsPojos) {
		// TODO Auto-generated method stub
		
		String[] choiceList = new String[filterContactsPojos
		 								.size()];

		 						for (int j = 0; j < filterContactsPojos.size(); j++) {
		 							choiceList[j] = filterContactsPojos.get(j).getFirstName()+"  "+filterContactsPojos.get(j).getLastName();
		 						
		 						}

		AlertDialog.Builder alert = new AlertDialog.Builder(
				JobDetails.this);
		alert.setTitle("Select user to swap job");

		alert.setSingleChoiceItems(choiceList, 0, null);
		alert.setPositiveButton("Confirm",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						// TODO Auto-generated method stub
						int selectedPosition = ((AlertDialog) dialog)
								.getListView()
								.getCheckedItemPosition();
						System.out.println(selectedPosition);

						JSONObject json = new JSONObject();
					/*	public Guid UserKey { get; set; } // Required
						public Guid? ContactUserKey { get; set; } // Required
						public int JobId { get; set; } // Required
						public int? JobStatusId { get; set; } // Required and JobStatusId should be 150
				*/		try {
							json.put(
									"UserKey",
									new UserPefrencePOJO()
											.getUserKey(JobDetails.this));
							json.put(
									"ContactUserKey",
									filterContactsPojos.get(
											selectedPosition)
											.getUserKey());
							json.put("JobId", jobInfoResponce
									.getData().getJobInfo()
									.getJobId());
							json.put(
									"JobStatusId",
									LimoUtil.JOB_STATUS_ID_FOR_SWAPPED);

							new CustomAsynTaskJSON_PUT(
									JobDetails.this, 10,
									"Loading...",
									LimoUtil.URL_JOB_GET, json)
									.execute("");

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
		alert.setNegativeButton("Cancel", null);
		alert.show();

		
	}

	private void swapDialog() {
		// TODO Auto-generated method stub
		
	}
	private void confirmWithPOPup() {
		// TODO Auto-generated method stub
		/*
		 * Url: base url/api/ Job Action Verb: Get Input: Yes UserKey– Required
		 * JobId = Required
		 */

		new AsyncTask<String, Integer, String>() {

			ProgressDialog pd;

			protected void onPreExecute() {
				pd = ProgressDialog.show(JobDetails.this, "", "");
			}

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub

				String url = LimoUtil.URL_JOB_GET + "?UserKey="
						+ new UserPefrencePOJO().getUserKey(JobDetails.this)
						+ "&JobId="
						+ jobInfoResponce.getData().getJobInfo().getJobId();

				return new WebAPIRequest().performGet(url);
			}

			protected void onPostExecute(String result) {
				pd.dismiss();

				try {
					JSONObject json = new JSONObject(result);
					String status = json.getString("Status");
					if (status.equalsIgnoreCase("Success")) {
						final ArrayList<AcceptedUsers> aceAcceptedUsers = new ArrayList<AcceptedUsers>();

						JSONArray jsonary = json.getJSONObject("Data")
								.getJSONArray("AcceptedUsers");
						for (int i = 0; i < jsonary.length(); i++) {
							aceAcceptedUsers.add(new AcceptedUsers(jsonary
									.getJSONObject(i).getString("Name"),
									jsonary.getJSONObject(i).getString(
											"UserKey")));
						}

						String[] choiceList = new String[aceAcceptedUsers
								.size()];

						for (int j = 0; j < aceAcceptedUsers.size(); j++) {
							choiceList[j] = aceAcceptedUsers.get(j).getName();
							System.out.println(j + " "
									+ aceAcceptedUsers.get(j).getName());
						}

						AlertDialog.Builder alert = new AlertDialog.Builder(
								JobDetails.this);
						alert.setTitle("Select user to confirm job");

						alert.setSingleChoiceItems(choiceList, 0, null);
						alert.setPositiveButton("Confirm",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										int selectedPosition = ((AlertDialog) dialog)
												.getListView()
												.getCheckedItemPosition();
										System.out.println(selectedPosition);

										/*
										 * public Guid UserKey { get; set; } //
										 * Required public Guid? ContactUserKey
										 * { get; set; } // Required public int
										 * JobId { get; set; } // Required
										 * public int? JobStatusId { get; set; }
										 * // Required and JobStatusId should be
										 */

										JSONObject json = new JSONObject();
										try {
											json.put(
													"UserKey",
													new UserPefrencePOJO()
															.getUserKey(JobDetails.this));
											json.put(
													"ContactUserKey",
													aceAcceptedUsers.get(
															selectedPosition)
															.getUserKey());
											json.put("JobId", jobInfoResponce
													.getData().getJobInfo()
													.getJobId());
											json.put(
													"JobStatusId",
													LimoUtil.JOB_STATUS_ID_FOR_CONFIRMED);

											new CustomAsynTaskJSON_PUT(
													JobDetails.this, 10,
													"Loading...",
													LimoUtil.URL_JOB_GET, json)
													.execute("");

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

	private void acceptJob() {
		// TODO Auto-generated method stub
		/*
		 * JSONObject json = new JSONObject(); try { json.put("UserKey", new
		 * UserPefrencePOJO().getUserKey(this));
		 * json.put("ContactUserKey",MyJobFragment.list
		 * .get(position).getUserKey());
		 * 
		 * json.put("JobId", jobInfoResponce.getData().getJobInfo().getJobId());
		 * json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_ACCEPTED);
		 * 
		 * new CustomAsynTaskJSON_PUT(this,2, "Loading...",
		 * LimoUtil.URL_JOB_GET, json).execute("");
		 * 
		 * } catch (JSONException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	private void offLoadJob() {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		try {
			json.put("UserKey", new UserPefrencePOJO().getUserKey(this));
			// json.put("ContactUserKey", new
			// UserPefrencePOJO().getUserKey(context)); json.put("UserKey", new
			// UserPefrencePOJO().getUserKey(context));
			json.put("JobId", jobInfoResponce.getData().getJobInfo().getJobId());
			json.put("JobStatusId", LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD);

			new CustomAsynTaskJSON_PUT(this, 2, "Loading...",
					LimoUtil.URL_JOB_GET, json).execute("");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void refreshAll() {
		// TODO Auto-generated method stub
		// LimoUtil.toast(position + "", JobDetails.this);
		String str = LimoUtil.URL_JOB_GET_INFo + "?Userkey="
				+ new UserPefrencePOJO().getUserKey(JobDetails.this)
				+ "&JobId=" + MyJobFragment.list.get(position).getJobId();

		new CustomAsynTaskGET(JobDetails.this, 1, "Loading...").execute(str);
		
		
	}

	private void setbuttonEnable(boolean enabled) {
		
		buttonDetails.setEnabled(enabled);
		button_Status.setEnabled(enabled);
		buttonNotes.setEnabled(enabled);
		buttonPayment.setEnabled(enabled);
		buttonMore.setEnabled(enabled);

	}

	JobInfoResponce jobInfoResponce;

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub

		if (result.equalsIgnoreCase("Exception") == false) {
			/*
			 * switch (taskId) { case 1:// JobInfo Result
			 * 
			 * Gson gson = new Gson(); jobInfoResponce = gson.fromJson(result,
			 * JobInfoResponce.class); updateUI();
			 * 
			 * break; case 2:// offload Job
			 * 
			 * 
			 * Gson gson = new Gson(); jobInfoResponce = gson.fromJson(result,
			 * JobInfoResponce.class); updateUI();
			 * 
			 * break; case 3:// offload Job
			 * 
			 * 
			 * Gson gson = new Gson(); jobInfoResponce = gson.fromJson(result,
			 * JobInfoResponce.class); updateUI();
			 * 
			 * break; default:
			 * 
			 * break; }
			 */
			if (taskId == 1) {
				Gson gson = new Gson();
				jobInfoResponce = gson.fromJson(result, JobInfoResponce.class);
				updateUI();
			}

			else {
				try {

					// {"Status":"Error","Data":null,"Message":["Already this job has been cancelled or Job is not offloaded"]}

					JSONObject json = new JSONObject(result);
					String status = "";
					String message = "";
					status = json.optString("Status");
					message = json.getJSONArray("Message").getString(0);
					/*
					 * if(status.equalsIgnoreCase("Success")) {
					 * list.get(position
					 * ).setJobStatusId(LimoUtil.JOB_STATUS_ID_FOR_OFFLOAD);
					 * adapter.notifyDataSetChanged(); }
					 */
					LimoUtil.toast(message, this);
					refreshAll();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {

		}

	}

	boolean isFromResponce=false;
	private void updateUI() {
		isFromResponce=true;
		// TODO Auto-generated method stub
		
		if(jobInfoResponce.getData().getJobInfo().getJobStatusId()==LimoUtil.JOB_STATUS_ID_FOR_OPEN )
		{
			image_buttonEdit.setVisibility(View.VISIBLE);
			
		}
		else
		{
			
		}
		textView1_title.setText(jobInfoResponce.getData().getJobInfo()
				.getBookStDt());
		textView_subTitle.setText(jobInfoResponce.getData().getJobInfo()
				.getBookStDt());
		if (jobInfoResponce.getData().getJobInfo().isIsRet()) {
			imageView_returnPickup.setImageDrawable(getResources().getDrawable(
					R.drawable.return_arrow));
		} else {
			imageView_returnPickup.setImageDrawable(getResources().getDrawable(
					R.drawable.single_arrow));

		}

		String toaddress1 = "";
		String toaddress2 = "";
		if (jobInfoResponce.getData().getJobInfo().getPSub() == null)// airport
		{
			toaddress1 = jobInfoResponce.getData().getJobInfo().getPAirName();
			toaddress2 = jobInfoResponce.getData().getJobInfo().getPFlightNo()+","+ jobInfoResponce.getData().getJobInfo().getPTerName()
					+ ","
					+ jobInfoResponce.getData().getJobInfo().getPAirName();
		} else {
			toaddress1 = jobInfoResponce.getData().getJobInfo().getPSub();
			toaddress2 = jobInfoResponce.getData().getJobInfo().getPSub();

		}

		textView_pickup_line1.setText(toaddress1);
		textView_pickup_addressline2.setText(toaddress2);

		String Daddress1 = "";
		String Daddress2 = "";
		if (jobInfoResponce.getData().getJobInfo().getDSub() == null)// airport
		{
			Daddress1 = jobInfoResponce.getData().getJobInfo().getDAirName();
		/*	Daddress2 = jobInfoResponce.getData().getJobInfo().getDAirName()
					+ ","
					+ jobInfoResponce.getData().getJobInfo().getDAirName();*/
			
			Daddress2 = jobInfoResponce.getData().getJobInfo().getDFlightNo()+","+ jobInfoResponce.getData().getJobInfo().getDTerName()
					+ ","
					+ jobInfoResponce.getData().getJobInfo().getDAirName();
		} else {
			Daddress1 = jobInfoResponce.getData().getJobInfo().getDSub();
			Daddress2 = jobInfoResponce.getData().getJobInfo().getDSub();

		}

		TextView_destinationLine1.setText(Daddress1);
		TextView_destinationLine2.setText(Daddress2);

		tv_amt.setText("$" + jobInfoResponce.getData().getJobInfo().getJbAmt()
				+ "");
		cust_name.setText(jobInfoResponce.getData().getJobInfo().getCustName()
				+ "");
		tv_mobile.setText(jobInfoResponce.getData().getJobInfo().getMobile()
				+ "");

		tv_Passengers.setText(jobInfoResponce.getData().getJobInfo()
				.getNoOfPass()
				+ "");
		tv_babyseat.setText(jobInfoResponce.getData().getJobInfo()
				.getNoOfBSeats()
				+ "");
		if (jobInfoResponce.getData().getJobInfo().getNotes() == null) {
			tv_speedNeed.setText("No");
		}

		else {
			tv_speedNeed.setText("Yes");
		}

		tv_eventType.setText(jobInfoResponce.getData().getJobInfo().getEvnTpe()
				+ "");
		if (jobInfoResponce.getData().getJobInfo().getNotes() != null) {
			tv_notes.setText(jobInfoResponce.getData().getJobInfo().getNotes());
		}

		
		switch (jobInfoResponce.getData().getJobInfo().getJobStatusId()) {
		case 100:
			// this job is open
			/*
			 * Offload Swap Cancel
			 */

			button_offload.setVisibility(View.VISIBLE);
			
			if (jobInfoResponce.getData().getJobInfo().getDSub() == null)// airport
			{
				button_offload.setBackground(getResources().getDrawable(R.drawable.offload_airport_mid));
			} else {
				button_offload.setBackground(getResources().getDrawable(R.drawable.offload_home_mid));
			}
		
			if(jobInfoResponce.getData().getJobInfo().isIsDisplayJob())
			{
				buttonSwap.setVisibility(View.VISIBLE);
			}
			button_cancel.setVisibility(View.VISIBLE);
			TextView_bootom_info.setVisibility(View.GONE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.GONE);
			break;
		case 110:// Offloaded
			// holder.textView_Button.setText("Offloaded");
			/*
			 * Confirm (with number count of contacts accepting job) Swap Cancel
			 */
			button_offload.setVisibility(View.GONE);
			buttonSwap.setVisibility(View.GONE); //update 8 aug  as kumar says
			button_cancel.setVisibility(View.VISIBLE);
			TextView_bootom_info.setVisibility(View.GONE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.VISIBLE);
			// with number count of contacts accepting job
			break;
		case 120:// Accepted

			button_offload.setVisibility(View.GONE);
			buttonSwap.setVisibility(View.GONE);
			button_cancel.setVisibility(View.GONE);
			TextView_bootom_info.setVisibility(View.GONE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.GONE);

			break;
		case 130:// Confirmed
			button_offload.setVisibility(View.GONE);
			buttonSwap.setVisibility(View.GONE);
			button_cancel.setVisibility(View.GONE);
			TextView_bootom_info.setVisibility(View.VISIBLE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.GONE);

			TextView_bootom_info
					.setText("Confirmed – "
							+ jobInfoResponce.getData().getJobInfo()
									.getCUname() + " – "
							+ jobInfoResponce.getData().getJobInfo().getCRgNo());

			break;

		case 150:// 150 Swapped
			button_offload.setVisibility(View.GONE);
			buttonSwap.setVisibility(View.GONE);
			button_cancel.setVisibility(View.GONE);
			TextView_bootom_info.setVisibility(View.VISIBLE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.GONE);
			TextView_bootom_info.setText("Swapped To – "+ jobInfoResponce.getData().getJobInfo()
									.getCUname() + " – "
							+ jobInfoResponce.getData().getJobInfo().getCRgNo());
			break;
		case 200:// Cancelled
			button_offload.setVisibility(View.GONE);
			buttonSwap.setVisibility(View.GONE);
			button_cancel.setVisibility(View.GONE);
			TextView_bootom_info.setVisibility(View.VISIBLE);
			button_accept.setVisibility(View.GONE);
			button_confirm.setVisibility(View.GONE);
			TextView_bootom_info.setText("Cancelled");
			break;

		default:
			break;
		}
		setDateonTitleandsubTextview();
	
		/*
		 * 100 Open 110 Offloaded 120 Accepted 130 Confirmed 150 Swapped 200
		 * Cancelled
		 */
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		System.out.println("JobDetails.onActivityResult()");
	}
}
