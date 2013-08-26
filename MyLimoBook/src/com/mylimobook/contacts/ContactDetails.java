package com.mylimobook.contacts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.SearchContact;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.pojos.PendingContacts;
import com.mylimobook.pojos.SearchResultPojo;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskJSONPOST;
import com.mylimobook.task.CustomAsynTaskJSON_PUT;
import com.mylimobook.util.ImageCache.ImageCacheParams;
import com.mylimobook.util.ImageFetcher;

public class ContactDetails extends FragmentActivity implements
		OnClickListener, AsyncTaskCompleteListener {

	private static final String IMAGE_CACHE_DIR_THUMB = null;

	TextView TextView_firstName, textviewLocation1, TextView_email,
			TextView_Mobile, TextView_location_regNo, TextView_VehicleType,
			TextView_dateJOin, textView_contactName,textView1_bottomtext;

	Button button_back, button1, button2; // button_Right, button_accept,
											// button_reject;
	LinearLayout lin_for_pending_coontact;

	ImageView imageView_profile;
	boolean isAccept=false;
	SearchResultPojo searchResultPojo;
	/*
	 * private static final int SECRENFROM_ADDCONTCAT=1; private static final
	 * int SECRENFROM_PENDINGCONTACT=1; private static final int
	 * SECRENFROM_ADDCONTCAT=1;
	 */
	JSONObject json;

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.button_back:
			this.finish();
			break;
		case R.id.button1:
			switch (selectionId) {
			case 1:// Contactlist so it is remove contact.

				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setMessage("Click Yes to remove the user from your Contacts?");
				alert.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								json = new JSONObject();
								try {

									json.put(
											"RequestByUserKey",
											new UserPefrencePOJO()
													.getUserKey(ContactDetails.this));
									json.put("RequestedToUserKey",
											existingContactsPojo.getUserKey());
									json.put("StatusId",
											LimoUtil.STATUSID_REMOVE_CONTACT);

									new CustomAsynTaskJSON_PUT(
											ContactDetails.this, 1,
											"Loading...",
											LimoUtil.URL_Contacts, json)
											.execute("");

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						});
				alert.setNegativeButton("No", null);
				alert.show();

				break;

			default:
				break;
			}
			break;
		case R.id.button2:// Block contact

			if(button2.getText().toString().equalsIgnoreCase("Block"))
			{
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setMessage("Click Yes to block the user from your Contacts?");
				alert.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub

								json = new JSONObject();
								try {

									json.put(
											"RequestByUserKey",
											new UserPefrencePOJO()
													.getUserKey(ContactDetails.this));
									json.put("RequestedToUserKey",
											existingContactsPojo.getUserKey());
									json.put("StatusId",
											LimoUtil.STATUSID_BLOCK_CONTACT);

									new CustomAsynTaskJSON_PUT(ContactDetails.this,
											2, "Loading...", LimoUtil.URL_Contacts,
											json).execute("");

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				alert.setNegativeButton("No", null);
				alert.show();

			}
			else
			{
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setMessage("Click Yes to Unblock the user from your Contacts?");
				alert.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub

								json = new JSONObject();
								try {

									json.put(
											"RequestByUserKey",
											new UserPefrencePOJO()
													.getUserKey(ContactDetails.this));
									json.put("RequestedToUserKey",
											existingContactsPojo.getUserKey());
									json.put("StatusId",
											LimoUtil.STATUSID_UNBLOCK_CONTACT);

									new CustomAsynTaskJSON_PUT(ContactDetails.this,
											2, "Loading...", LimoUtil.URL_Contacts,
											json).execute("");

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						});
				alert.setNegativeButton("No", null);
				alert.show();

			}
			
			break;
		default:
			break;
		}

	}

	int selectionId = 0;// 1 from contact ,2 from pending contact 3 for search
						// contact;
	ExistingContactsPojo existingContactsPojo;
	PendingContacts pendingContacts;
	int pos;

	ImageFetcher mImageFetcher;
	ImageCacheParams cacheParams;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_contact_details);
		textView1_bottomtext=(TextView) findViewById(R.id.textView1_bottomtext);
		
		textView1_bottomtext.setVisibility(View.GONE);
		
		imageView_profile=(ImageView) findViewById(R.id.imageView_profile);
		
		
		
		
		cacheParams = new ImageCacheParams(ContactDetails.this,
				IMAGE_CACHE_DIR_THUMB);

		mImageFetcher = new ImageFetcher(ContactDetails.this, 120);


		mImageFetcher.addImageCache(getSupportFragmentManager(), cacheParams);

		TextView_firstName = (TextView) findViewById(R.id.TextView_firstName);
		textView_contactName = (TextView) findViewById(R.id.textView_contactName);
		textviewLocation1 = (TextView) findViewById(R.id.textviewLocation1);

		TextView_email = (TextView) findViewById(R.id.TextView_email);

		TextView_Mobile = (TextView) findViewById(R.id.TextView_Mobile);

		TextView_location_regNo = (TextView) findViewById(R.id.TextView_location_regNo);

		TextView_VehicleType = (TextView) findViewById(R.id.TextView_VehicleType);

		TextView_dateJOin = (TextView) findViewById(R.id.TextView_dateJOin);

		button_back = (Button) findViewById(R.id.button_back);
		button_back.setOnClickListener(this);
		button1 = (Button) findViewById(R.id.button1);

		button2 = (Button) findViewById(R.id.button2);
	

		Intent intent = getIntent();

		if (intent != null) {
			selectionId = intent.getIntExtra("ISFROM", 0);

			if (selectionId == 1) {//Existing contacts
				pos = intent.getIntExtra("POS", -1);
		//		String exString = intent.getStringExtra("OBJECT");
		//		Gson gson = new Gson();
				existingContactsPojo = ContactFragment.filterContactsPojos.get(pos);
				textView_contactName.setText(""
						+ existingContactsPojo.getFirstName() + " "
						+ existingContactsPojo.getLastName());
				TextView_firstName.setText(""
						+ existingContactsPojo.getFirstName() + " "
						+ existingContactsPojo.getLastName());
				textviewLocation1.setText(""
						+ existingContactsPojo.getLocDescription() + " "
						+ existingContactsPojo.getRegoNo());

				TextView_email.setText(existingContactsPojo.getEmail());
				TextView_Mobile
						.setText(LimoUtil
								.convertMobileFormat(existingContactsPojo
										.getMobileNo()));
				TextView_location_regNo.setText(""
						+ existingContactsPojo.getLocDescription() + " "
						+ existingContactsPojo.getRegoNo());
				TextView_VehicleType.setText(existingContactsPojo
						.getVehicleType());

				TextView_dateJOin.setText(newFormatodDate(existingContactsPojo
						.getDateJoined()));

				/*
				 * imageView_profile=(ImageView)
				 * findViewById(R.id.imageView_profile);
				 * existingContactsPojo.getPhotoUrl()
				 */

				cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache
															// to
				

				mImageFetcher
						.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);

				if (existingContactsPojo.getPhotoUrl() != null) {
					mImageFetcher.loadImage(existingContactsPojo.getPhotoUrl(),	imageView_profile);

				}

				if (existingContactsPojo.isIsPending()) {
					textView1_bottomtext.setText("Request Sent");
					textView1_bottomtext.setVisibility(View.VISIBLE);
			
					button1.setVisibility(View.GONE);
					button2.setVisibility(View.GONE);
				}
				else
				{
					if (existingContactsPojo.isIsBlocked()) {
						button2.setText("Unblock");
						
						button2.setBackground(getResources().getDrawable(R.drawable.unblock_contact));
						button2.setVisibility(View.VISIBLE);
						//button1.setText("Remove");
						button1.setBackground(getResources().getDrawable(R.drawable.remove_contact));
						
						button1.setOnClickListener(this);
						//button1.setBackground(background)
						button2.setOnClickListener(this);
						button1.setVisibility(View.VISIBLE);
						
						textView1_bottomtext.setVisibility(View.GONE);
					}
					else
					{
						

						//button1.setText("Remove");
						button1.setBackground(getResources().getDrawable(R.drawable.remove_contact));
						
						button1.setOnClickListener(this);
						//button2.setText("Block");
						
						button2.setBackground(getResources().getDrawable(R.drawable.block_contact));
						
						button2.setOnClickListener(this);
						
						button1.setVisibility(View.VISIBLE);
						button2.setVisibility(View.VISIBLE);
						textView1_bottomtext.setVisibility(View.GONE);
						
					}
				}
				
				
			} else if (selectionId == 2)// pending contact
			{
				
				pos = intent.getIntExtra("POS", -1);
			
				pendingContacts = PendingContactFragment.pendingContact.get(pos);

				textView_contactName.setText(""
						+ pendingContacts.getFirstName() + " "
						+ pendingContacts.getLastName());
				TextView_firstName.setText("" + pendingContacts.getFirstName()
						+ " " + pendingContacts.getLastName());
				textviewLocation1.setText(""
						+ pendingContacts.getLocDescription() + " "
						+ pendingContacts.getRegoNo());

				TextView_email.setText(pendingContacts.getEmail());
				TextView_Mobile.setText(LimoUtil
						.convertMobileFormat(pendingContacts.getMobileNo()));
				TextView_location_regNo.setText(""
						+ pendingContacts.getLocDescription() + " "
						+ pendingContacts.getRegoNo());
				TextView_VehicleType.setText(pendingContacts.getVehicleType());
				
				TextView_dateJOin.setText(newFormatodDate(pendingContacts
						.getDateJoined()));

				button1.setVisibility(View.VISIBLE);
			
				button1.setText("");
				button1.setBackground(getResources().getDrawable(R.drawable.accept_contact));
				
				button1.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Accept
						JSONObject json = new JSONObject();
						try {

							//[8:01:00 AM] Ramasamy NP: RequestedToUserKey is currently login user 22 Aug
							json.put("RequestByUserKey", pendingContacts
									.getUserKey());
							json.put("RequestedToUserKey",
									new UserPefrencePOJO().getUserKey(ContactDetails.this));
							json.put("StatusId", LimoUtil.STATUSID_ACCEPT_CONTACT);

							isAccept=true;
							new CustomAsynTaskJSON_PUT(ContactDetails.this, 
									2, "Accepting...", LimoUtil.URL_Contacts, json
								).execute("");

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				button2.setVisibility(View.VISIBLE);
				
				button2.setText("");
				
				button2.setBackground(getResources().getDrawable(R.drawable.reject_contact));
				button2.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Reject
						
						
						JSONObject json = new JSONObject();
						try {
							//[8:01:00 AM] Ramasamy NP: RequestedToUserKey is currently login user 22 Aug
							
							json.put("RequestByUserKey",pendingContacts
									.getUserKey());
							
							json.put("RequestedToUserKey",
							new UserPefrencePOJO().getUserKey(ContactDetails.this));
							json.put("StatusId", LimoUtil.STATUSID_REJECT_CONTACT);
							isAccept=false;
							new CustomAsynTaskJSON_PUT(ContactDetails.this, 
									2, "Rejecting...", LimoUtil.URL_Contacts, json).execute("");

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache
															// to
				

				mImageFetcher
						.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);

				if (pendingContacts.getPhotoUrl() != null) {
					mImageFetcher.loadImage(pendingContacts.getPhotoUrl(),	imageView_profile);

				}
				
			} else if (selectionId == 3)// Search contact
			{
				pos = intent.getIntExtra("POS", -1);
				
				searchResultPojo=SearchContact.searchList.get(pos);
			/*	String exString = intent.getStringExtra("OBJECT");
				Gson gson1 = new Gson();
				searchResultPojo = gson1.fromJson(exString,
						SearchResultPojo.class);*/

				textView_contactName.setText(""
						+ searchResultPojo.getFirstName() + " "
						+ searchResultPojo.getLastName());
				TextView_firstName.setText("" + searchResultPojo.getFirstName()
						+ " " + searchResultPojo.getLastName());
				textviewLocation1.setText(""
						+ searchResultPojo.getLocDescription() + " "
						+ searchResultPojo.getRegoNo());

				TextView_email.setText(searchResultPojo.getEmail());
				TextView_Mobile.setText(LimoUtil
						.convertMobileFormat(searchResultPojo.getMobileNo()));
				TextView_location_regNo.setText(""
						+ searchResultPojo.getLocDescription() + " "
						+ searchResultPojo.getRegoNo());
				TextView_VehicleType.setText(searchResultPojo.getVehicleType());
				
				TextView_dateJOin.setText(newFormatodDate(searchResultPojo
						.getDateJoined()));
				
				button1.setVisibility(View.VISIBLE);
				button1.setBackground(getResources().getDrawable(
						R.drawable.button));
				button1.setText("");
				

				if(searchResultPojo.isIsPending())
				{
					button1.setVisibility(View.GONE);
					textView1_bottomtext.setVisibility(View.VISIBLE);
					textView1_bottomtext.setText("Request Sent");
					
				}
				else
				{
					button1.setBackground(getResources().getDrawable(R.drawable.addcontact));
					button1.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							// Add or send request for add contact

							addButtonclick();
						}
					});
					
				}
			
					
				
				
				button2.setVisibility(View.GONE);
				mImageFetcher
				.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
				

		if (searchResultPojo.getPhotoUrl() != null) {
			mImageFetcher.loadImage(searchResultPojo.getPhotoUrl(),	imageView_profile);

		}
			}
		} else {
			this.finish();
		}

	}

	private String newFormatodDate(String dateJoined) {
		// TODO Auto-generated method stub
		System.out.println("A " + dateJoined);
		// "2013-08-16T05:42:59.193"
		if (dateJoined != null) {

			SimpleDateFormat oldFormat = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss");

			DateFormat newFormat = new SimpleDateFormat("MMM yyyy");// h:mm a

			SimpleDateFormat targetformatFotdisplayDate = new SimpleDateFormat(
					"dd");

			Date date;
			try {
				date = oldFormat.parse(dateJoined);
				System.out.println("B " + date);

				String convertedDate = newFormat.format(date);
				System.out.println("C " + convertedDate);

				int intday = Integer.parseInt(targetformatFotdisplayDate
						.format(date));

				return LimoUtil.setNumberFormat(intday) + " "
						+ newFormat.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return "";
	}

	public void addButtonclick() {
		// TODO Auto-generated method stub
		// this.position=position;
		JSONObject json = new JSONObject();
		try {
			json.put("RequestByUserKey",
					new UserPefrencePOJO().getUserKey(ContactDetails.this));

			json.put("RequestedToUserKey", searchResultPojo.getUserKey());
			new CustomAsynTaskJSONPOST(ContactDetails.this, 3,
					"Sending request...", LimoUtil.URL_Contacts, json)
					.execute("");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * 
	 * 
	 * 
	 * @Override protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * lin_for_pending_coontact = (LinearLayout)
	 * findViewById(R.id.lin_for_pending_coontact);
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * boolean isPending; if(intent!=null) {
	 * isPending=intent.getBooleanExtra("ISPENDING", true); if(isPending) {
	 * String exString=intent.getStringExtra("OBJECT"); Gson gson= new Gson();
	 * 
	 * ExistingContactsPojo existingContactsPojo=gson.fromJson(exString,
	 * ExistingContactsPojo.class);
	 * lin_for_pending_coontact.setVisibility(View.GONE);
	 * TextView_firstName.setText(""+existingContactsPojo.getFirstName());
	 * TextView_lastname.setText(""+existingContactsPojo.getFirstName());
	 * TextView_email.setText(""+existingContactsPojo.getFirstName());
	 * TextView_mobileNo.setText(""+existingContactsPojo.getFirstName());
	 * TextView_regNo.setText(""+existingContactsPojo.getFirstName());
	 * textview_vehicle_type.setText(""+existingContactsPojo.getFirstName());
	 * 
	 * 
	 * button_Right.setText("Remove");
	 * 
	 * lin_for_pending_coontact = (LinearLayout)
	 * findViewById(R.id.lin_for_pending_coontact);
	 * 
	 * } } else { this.finish(); }
	 * 
	 * }
	 * 
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub
	 * 
	 * switch (v.getId()) { case R.id.button_back: onBackPressed();
	 * 
	 * break;
	 * 
	 * case R.id.button_Right:
	 * 
	 * break;
	 * 
	 * case R.id.button_accept:
	 * 
	 * break;
	 * 
	 * case R.id.button_reject:
	 * 
	 * break;
	 * 
	 * default: break; } }
	 */

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		if (taskId == 1)// Remove contact

		{

			if (result != "Exception") {
				// :{"Status":"Success","Data":null,"Message":["Your request has been sent successfully"]}

				try {
					JSONObject json = new JSONObject(result);
					String Status = json.getString("Status");
					String message = json.getJSONArray("Message").getString(0);

					// TODO Auto-generated method stub
					if (Status.equalsIgnoreCase("Success")) {

						if (selectionId == 2) {//accept request
							System.out.println("))))))))))))))))))))))))");
							System.out.println("Size "+PendingContactFragment.pendingContact.size());
							System.out.println("pos "+pos);
								PendingContactFragment
									.returnAndRefreshFromContactDetails(pos,true);
							ContactDetails.this.finish();
						} else if (selectionId == 3) // Addcontact from
														// searchcontact
						{
							//SearchContact.removeFromContactDetails(pos);
							button1.setVisibility(View.GONE);
							textView1_bottomtext.setVisibility(View.VISIBLE);
							textView1_bottomtext.setText("Request Sent");
							searchResultPojo.setIsPending(true);
						}
						if (selectionId == 1) {
							System.out.println("Remove or block is done");
							setResult(Activity.RESULT_OK);
							ContactDetails.this.finish();
						}
						

					}
					LimoUtil.toast(message, ContactDetails.this);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		if (taskId == 2)// Block contact

		{

			if (result != "Exception") {
				// :{"Status":"Success","Data":null,"Message":["Your request has been sent successfully"]}

				try {
					JSONObject json = new JSONObject(result);
					String Status = json.getString("Status");
					String message = json.getJSONArray("Message").getString(0);
					if (Status.equalsIgnoreCase("Success")) {

						if (selectionId == 2) {//Reject done
							System.out.println("!!!!!!!!!!!!))))))))))))))))))))))))");
							System.out.println("Size "+PendingContactFragment.pendingContact.size());
							System.out.println("pos "+pos);
								PendingContactFragment
									.returnAndRefreshFromContactDetails(pos,isAccept);
							ContactDetails.this.finish();
						} else if (selectionId == 3) // Addcontact from
														// searchcontact
						{
							//SearchContact.removeFromContactDetails(pos);
							ContactDetails.this.finish();
						}
						if (selectionId == 1) {
							System.out.println("Remove or block is done");
							//setResult(Activity.RESULT_OK);
							
							if(existingContactsPojo.isIsBlocked())
							{
								existingContactsPojo.setIsBlocked(false);
							}
							else
							{
								existingContactsPojo.setIsBlocked(true);
								
							}
							if (existingContactsPojo.isIsBlocked()) {
								button2.setText("Unblock");
								//holder.button_add.setBackground(null);
								button2.setVisibility(View.VISIBLE);
								
							}
							else
							{
								

							
								button2.setText("Block");
							
								
							}
						
						}
					

					}
					LimoUtil.toast(message, ContactDetails.this);
					// LimoUtil.alertDialog(message, SearchContact.this);
					// LimoUtil.alertDialog(message, ContactDetails.this);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else if (taskId == 3)// Search contact from search contact

		{

			if (result != "Exception") {
				// :{"Status":"Success","Data":null,"Message":["Your request has been sent successfully"]}

				try {
					JSONObject json = new JSONObject(result);
					String Status = json.getString("Status");
					String message = json.getJSONArray("Message").getString(0);
					if (Status.equalsIgnoreCase("Success")) {

						if (selectionId == 2) {
							PendingContactFragment
									.returnAndRefreshFromContactDetails(pos,false);
							ContactDetails.this.finish();
						} else if (selectionId == 3) // Addcontact from
														// searchcontact
						{
							//SearchContact.removeFromContactDetails(pos);
							button1.setVisibility(View.GONE);
							textView1_bottomtext.setVisibility(View.VISIBLE);
							textView1_bottomtext.setText("Request Sent");
							searchResultPojo.setIsPending(true);
							
							

							ExistingContactsPojo existingContactsPojo = new ExistingContactsPojo();
							existingContactsPojo.setContactRequestId(searchResultPojo.getContactRequestId());
							existingContactsPojo.setDateJoined(searchResultPojo.getDateJoined());
							existingContactsPojo.setEmail(searchResultPojo.getEmail());
							existingContactsPojo.setFirstName(searchResultPojo.getFirstName());
							existingContactsPojo.setLastName(searchResultPojo.getLastName());
							
							existingContactsPojo.setIsBlocked(false);
							existingContactsPojo.setIsNotified(searchResultPojo.isIsNotified());
							existingContactsPojo.setIsPending(searchResultPojo.isIsPending());
							
							existingContactsPojo.setLocDescription(searchResultPojo.getLocDescription());
							existingContactsPojo.setMobileNo(searchResultPojo.getMobileNo());
							//existingContactsPojo.setPhotoContent(searchList.get(position).getP);
							existingContactsPojo.setPhotoName(searchResultPojo.getPhotoName());
							existingContactsPojo.setPhotoUrl(searchResultPojo.getPhotoUrl());
							existingContactsPojo.setRegoNo(searchResultPojo.getRegoNo());
							
							existingContactsPojo.setStatus(searchResultPojo.getStatus());
							existingContactsPojo.setUserKey(searchResultPojo.getUserKey());
							existingContactsPojo.setVehicleType(searchResultPojo.getVehicleType());
						
							
							ContactFragment.filterContactsPojos.add(existingContactsPojo);
							
							
							
						}
						if (selectionId == 1) {
							System.out.println("Remove or block is done");
							setResult(Activity.RESULT_OK);
							ContactDetails.this.finish();
						}
						

					}
					LimoUtil.toast(message, ContactDetails.this);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} else // add contact
		{

			if (result != "Exception") {
				// :{"Status":"Success","Data":null,"Message":["Your request has been sent successfully"]}

				try {
					JSONObject json = new JSONObject(result);
					String Status = json.getString("Status");

					String message = json.getJSONArray("Message").getString(0);
					if (Status.equalsIgnoreCase("Success")) {

						if (selectionId == 2) {
							PendingContactFragment
									.returnAndRefreshFromContactDetails(pos,false);
						} else if (selectionId == 3) // Addcontact from
														// searchcontact
						{
							//SearchContact.removeFromContactDetails(pos);
							button1.setVisibility(View.GONE);
							textView1_bottomtext.setVisibility(View.VISIBLE);
							textView1_bottomtext.setText("Request Sent");
							searchResultPojo.setIsPending(true);
						}
						if (selectionId == 1) {
							System.out.println("Remove or block is done");
							setResult(Activity.RESULT_OK);
						}
						ContactDetails.this.finish();

					}
					LimoUtil.toast(message, ContactDetails.this);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

/*	public void alertDialog1(String string, boolean status) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setMessage(string);
		if (status) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
							 * PendingContactFragment.pendingContact.remove(position
							 * ); adapter.notifyDataSetChanged();
							 

							if (selectionId == 2) {
								PendingContactFragment
										.returnAndRefreshFromContactDetails(pos,false);
							} else if (selectionId == 3) // Addcontact from
															// searchcontact
							{
								SearchContact.removeFromContactDetails(pos);
							}
							if (selectionId == 1) {
								System.out.println("Remove or block is done");
								setResult(Activity.RESULT_OK);
							}
							ContactDetails.this.finish();
						}
					});
		} else {
			alert.setPositiveButton("OK", null);
		}

		alert.show();

	}*/
}
