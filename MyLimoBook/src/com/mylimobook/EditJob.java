package com.mylimobook;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.jobs.AddJob;
import com.mylimobook.jobs.EventTypesPOJO;
import com.mylimobook.pojos.AirportsPojo;
import com.mylimobook.pojos.EditJobRes;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;

public class EditJob extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	boolean isSpinerFromResponce = true;
	Button button_back, button_save, button_addjob1;

	EditText editText_firstName, editText_lastName, editText_mobile,
			edittext_Passengers, edittext_babysit, editText_Notes,
			edittext_Amount;
	TextView textview_datetime, textview_eventType;
	Switch switchReturn, switch_displayJob, switchSpecialNeed;
	//Spinner Spinner_pick, Spinner_to;

	private final int ID_FROM_AIRPORT = 11;
	private final int ID_FROM_SUBURB = 12;

	private final int ID_TO_AIRPORT = 13;
	private final int ID_To_SUBURB = 14;

	public static int PICKORDESTINATION = 0;

	// Use for pick up and destination address
	public static String FromFlightNo = "", ToFlightNO = "";
	public static int fromAirportPOS = -1;
	public static int ToAirportPOS = -1;

	public static String fromStreetNo = "", fromStreetName = "",
			fromStreetSuburb = "",fromStreetSuburbPOST="", fromLandmark = "", fromPostalcode = "",
			fromState = "";
	public static String ToStreetNo = "", ToStreetName = "",
			ToStreetSuburb = "", ToLandmark = "", ToPostalcode = "",ToStreetSuburbPOST="",
			ToState = "";

	public static AirportsPojo FromAirportsPojoSelected, ToAirportPojoSelected;
	TextView textview_from_address, textview_To_address;
	int jobid;
	// ----------------
	// private JobInfoResponce jobInfoResponce;

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		PICKORDESTINATION = 0;

		// Use for pick up and destination address
		FromFlightNo = "";
		ToFlightNO = "";
		fromAirportPOS = -1;
		ToAirportPOS = -1;

		fromStreetNo = "";
		fromStreetName = "";
		fromStreetSuburb = "";
		fromStreetSuburbPOST = "";
		fromLandmark = "";
		fromPostalcode = "";
		fromState = "";
		ToStreetNo = "";
		ToStreetName = "";
		ToStreetSuburb = "";
		ToStreetSuburbPOST="";
		ToLandmark = "";
		ToPostalcode = "";
		ToState = "";

		FromAirportsPojoSelected = null;
		ToAirportPojoSelected = null;
	}

	public static EditJobRes editJobRes;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_job);
		textview_from_address = (TextView) findViewById(R.id.textview_from_address);
		textview_To_address = (TextView) findViewById(R.id.textview_To_address);
		
		textview_from_address.setTag(0+"");
		textview_To_address.setTag(0+"");
		textview_from_address.setOnClickListener(this);
		textview_To_address.setOnClickListener(this);
		
		if (LimoUtil.isNetworkAvailable(this) == false) {
			LimoUtil.toast(
					getString(R.string.CheckYourInternetConnection), this);
		}

		Intent i = getIntent();

		jobid = i.getIntExtra("JobId", -1);

		String str = LimoUtil.URL_JOB_GET_INFo + "?JobId=" + jobid;
		new CustomAsynTaskGET(EditJob.this, 1, "Loading...").execute(str);

		PICKORDESTINATION = 0;

		// Use for pick up and destination address
		FromFlightNo = "";
		ToFlightNO = "";
		fromAirportPOS = -1;
		ToAirportPOS = -1;

		fromStreetNo = "";
		fromStreetName = "";
		fromStreetSuburb = "";
		fromStreetSuburbPOST = "";
		fromLandmark = "";
		fromPostalcode = "";
		fromState = "";
		ToStreetNo = "";
		ToStreetName = "";
		ToStreetSuburbPOST="";
		ToStreetSuburb = "";
		ToLandmark = "";
		ToPostalcode = "";
		ToState = "";

		FromAirportsPojoSelected = null;
		ToAirportPojoSelected = null;

		datePicker = new DatePicker(EditJob.this);
		timePicker = new TimePicker(EditJob.this);

		initView();


	}

	private void initView() {
		// TODO Auto-generated method stub

		button_back = (Button) findViewById(R.id.button_back);
		button_save = (Button) findViewById(R.id.button_save);
		// button_addjob = (Button) findViewById(R.id.button_addjob);

		editText_firstName = (EditText) findViewById(R.id.editText_firstName);
		editText_lastName = (EditText) findViewById(R.id.editText_lastName);
		editText_mobile = (EditText) findViewById(R.id.editText_mobile);
		editText_mobile.addTextChangedListener(new TextWatcher() {
			String str = "";
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

				if (count != 0) {
					if (start == 4) {
						str = s + "";
						editText_mobile.setText(str.substring(0, 4) + "-" + str.substring(4));
						editText_mobile.setSelection(editText_mobile.getText().length());

						System.out.println(editText_mobile.getText());
					} else if (start == 8) {
						str = s + "";
						editText_mobile.setText(str.substring(0, 8) + "-" + str.substring(8));
						editText_mobile.setSelection(editText_mobile.getText().length());
						System.out.println(editText_mobile.getText());

					}

					// PhoneNumberUtils.formatNumber(ed_mobile.getText(),
					// PhoneNumberUtils.FORMAT_NANP);

					if (s.length() ==4) {
						editText_mobile.setText(s + "-");
						editText_mobile.setSelection(editText_mobile.getText().length());

						System.out.println(editText_mobile.getText());

					} else if (s.length() == 8) {
						editText_mobile.setText(s + "-");
						editText_mobile.setSelection(editText_mobile.getText().length());

					}

				}

			
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

		
		edittext_Passengers = (EditText) findViewById(R.id.edittext_Passengers);
		edittext_babysit = (EditText) findViewById(R.id.edittext_babysit);

		textview_datetime = (TextView) findViewById(R.id.textview_datetime);
			switchReturn = (Switch) findViewById(R.id.switchReturn);
		switch_displayJob = (Switch) findViewById(R.id.switch_displayJob);
		editText_Notes = (EditText) findViewById(R.id.editText_Notes);
		edittext_Amount = (EditText) findViewById(R.id.edittext_Amount);

		switchSpecialNeed = (Switch) findViewById(R.id.switchSpecialNeed);
		switchSpecialNeed
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						/*if (isChecked) {
							editText_Notes.setVisibility(View.VISIBLE);
							editText_Notes.requestFocus();
						} else {
							editText_Notes.setVisibility(View.GONE);
						}*/

					}
				});

		// button_addjob.setOnClickListener(this);
		editText_Notes.setVisibility(View.VISIBLE);
		button_save.setOnClickListener(this);
		button_back.setOnClickListener(this);
		textview_datetime.setOnClickListener(this);
		// textview_pick.setOnClickListener(this);
		// textview_to.setOnClickListener(this);

		textview_eventType = (TextView) findViewById(R.id.textview_eventType);
		textview_eventType.setOnClickListener(this);
		
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//				this, R.array.pickupJOb, R.layout.simple_spinner_item);
//		adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//		Spinner_pick.setAdapter(adapter);
//		Spinner_to.setAdapter(adapter);
//
//		Spinner_pick.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				if (isSpinerFromResponce == false) {
//					if (arg2 == 2) {
//						PICKORDESTINATION = 1;
//
//						// boolean
//						// fromEditJob=getIntent().getBooleanExtra("FROMEDITJOB",
//						// false);
//						Intent intent = new Intent(EditJob.this,
//								SuburbActivity.class);
//						intent.putExtra("FROMEDITJOB", true);
//						startActivityForResult(intent, ID_FROM_SUBURB);
//
//					}
//
//					else if (arg2 == 1) {
//
//						PICKORDESTINATION = 1;
//						Intent intent = new Intent(EditJob.this,
//								AirportActivity.class);
//						intent.putExtra("FROMEDITJOB", true);
//
//						startActivityForResult(intent, ID_FROM_AIRPORT);
//					}
//
//					else {
//						textview_from_address.setText("");
//						textview_from_address.setVisibility(View.GONE);
//					}
//					ignoreOldSelectionByReflection(Spinner_pick);
//				}
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//				/*
//				 * LimoUtil.toast(Spinner_pick.getSelectedItemPosition() + "",
//				 * EditJob.this);
//				 */
//			}
//		});
//		Spinner_to.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				isSpinerFromResponce = false;
//				return false;
//			}
//		});
//		Spinner_pick.setOnTouchListener(new OnTouchListener() {
//
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				// TODO Auto-generated method stub
//				isSpinerFromResponce = false;
//				return false;
//			}
//		});
//		Spinner_to.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				if (isSpinerFromResponce == false) {
//					if (arg2 == 2) {
//
//						PICKORDESTINATION = 2;
//
//						Intent intent = new Intent(EditJob.this,
//								SuburbActivity.class);
//						intent.putExtra("FROMEDITJOB", true);
//						startActivityForResult(intent, ID_To_SUBURB);
//					}
//
//					else if (arg2 == 1) {
//						PICKORDESTINATION = 2;
//						Intent intent = new Intent(EditJob.this,
//								AirportActivity.class);
//						intent.putExtra("FROMEDITJOB", true);
//						startActivityForResult(intent, ID_TO_AIRPORT);
//					} else {
//						textview_To_address.setText("");
//						
//					}
//
//				
//				}
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//
	
	}

	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ID_FROM_SUBURB) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
				textview_from_address.setTag(0+"");
				textview_from_address.setText("");


			} else if (resultCode == Activity.RESULT_OK) {

				if (data != null) {
					fromStreetNo = data.getStringExtra("S1");

					fromStreetName = data.getStringExtra("S2");
					fromStreetSuburb = data.getStringExtra("S3");
					fromLandmark = data.getStringExtra("S4");
					fromPostalcode = data.getStringExtra("S5");
					fromState = data.getStringExtra("S6");
					fromStreetSuburbPOST =data.getStringExtra("S7");
					
					fromAirportPOS = -1;
					FromFlightNo = "";

					String fromAddress = fromStreetNo + "," + fromStreetName + "," + fromLandmark + ","
							+ "," + fromStreetSuburbPOST
							+ fromState + "," + fromPostalcode;
					textview_from_address.setText(fromAddress);
					textview_from_address.setVisibility(View.VISIBLE);
					// textview_pick.setText(streetNo + " " + streetName + " "/+
					// streetSuburb);
				
					textview_from_address.setTag(2+"");
				}

			}

		}
		if (requestCode == ID_To_SUBURB) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
				//Spinner_to.setSelection(0);
				textview_To_address.setTag(0+"");
				textview_To_address.setText("");
			} else if (resultCode == Activity.RESULT_OK) {

				if (data != null) {
					ToStreetNo = data.getStringExtra("S1");
					ToStreetName = data.getStringExtra("S2");
					ToStreetSuburb = data.getStringExtra("S3");
					ToLandmark = data.getStringExtra("S4");
					ToPostalcode = data.getStringExtra("S5");
					ToState = data.getStringExtra("S6");
					ToStreetSuburbPOST = data.getStringExtra("S7");

					ToAirportPOS = -1;
					ToFlightNO = "";

					String toAddress = ToStreetNo + "," + ToStreetName + ","
							+ ToStreetSuburb + "," + ToLandmark + ","
							+ ToPostalcode + "," + ToState;

					textview_To_address.setText(toAddress);
					
					textview_To_address.setTag(2+"");
				}

			}

		} else if (requestCode == ID_FROM_AIRPORT) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
			//	Spinner_pick.setSelection(0);
				textview_from_address.setText("");
				textview_from_address.setTag(0+"");
			} else if (resultCode == Activity.RESULT_OK) {

				if (data != null) {
					fromAirportPOS = data.getIntExtra("airportPOS", -1);
					FromAirportsPojoSelected = editJobRes.getData()
							.getAirports().get(fromAirportPOS);
					FromFlightNo = data.getStringExtra("FlightNo");
					// airportName=data.getStringExtra("airportPOS");

					fromStreetNo = "";
					fromStreetName = "";
					fromStreetSuburb = "";
					fromStreetSuburbPOST = "";
					fromLandmark = "";
					fromPostalcode = "";
					fromState = "";

					String fromAddress = FromFlightNo + ","
							+ FromAirportsPojoSelected.getAirportName();
					textview_from_address.setText(fromAddress);
					textview_from_address.setVisibility(View.VISIBLE);
					
					
					textview_from_address.setTag(1+"");
				}

			}

		} else if (requestCode == ID_TO_AIRPORT) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
				textview_To_address.setText("");
				textview_To_address.setTag(0+"");

			} else if (resultCode == Activity.RESULT_OK) {

				if (data != null) {
					ToAirportPOS = data.getIntExtra("airportPOS", -1);
					ToAirportPojoSelected = editJobRes.getData().getAirports()
							.get(ToAirportPOS);
					ToFlightNO = data.getStringExtra("FlightNo");
					// airportName=data.getStringExtra("airportPOS");

					ToStreetNo = "";
					ToStreetName = "";
					ToStreetSuburb = "";
					ToStreetSuburbPOST = "";

					ToLandmark = "";
					ToPostalcode = "";
					ToState = "";

					String ToAddress = ToFlightNO + ","
							+ ToAirportPojoSelected.getAirportName();
					textview_To_address.setText(ToAddress);
					textview_To_address.setVisibility(View.VISIBLE);
				
	
					textview_To_address.setTag(1+"");
				}

			}

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		
		
		
		
		case R.id.textview_from_address:
			// TODO Auto-generated method stub
			
			

			final CharSequence[] fromToAddressPOPUPList = {"Please Select","Airport","Suburb"};
			                          				

			                          				AlertDialog.Builder alertAddress = new AlertDialog.Builder(EditJob.this);
			                          				alertAddress.setTitle("Select from address.");
			                          				alertAddress.setSingleChoiceItems(fromToAddressPOPUPList,Integer.parseInt((String)textview_from_address.getTag()),
			                          						new DialogInterface.OnClickListener() {
			                          							public void onClick(DialogInterface dialog, int item) {
			                          								// Do something with the selection
			                          								
			                          								if (item == 2) {
			                          									PICKORDESTINATION = 1;
			                          			
			                          									// boolean
			                          									// fromEditJob=getIntent().getBooleanExtra("FROMEDITJOB",
			                          									// false);
			                          									Intent intent = new Intent(EditJob.this,
			                          											SuburbActivity.class);
			                          									intent.putExtra("FROMEDITJOB", true);
			                          									startActivityForResult(intent, ID_FROM_SUBURB);
			                          			
			                          								}
			                          			
			                          								else if (item == 1) {
			                          			
			                          									PICKORDESTINATION = 1;
			                          									Intent intent = new Intent(EditJob.this,
			                          											AirportActivity.class);
			                          									intent.putExtra("FROMEDITJOB", true);
			                          			
			                          									startActivityForResult(intent, ID_FROM_AIRPORT);
			                          								}
			                          			
			                          								else {
			                          									textview_from_address.setText("");
			                          									textview_from_address.setVisibility(View.GONE);
			                          								}
			                          								
			                          								
			                          							
			                          								textview_from_address.setTag(item+"");
			                          								dialog.dismiss();
			                          							}
			                          						});
			                          				AlertDialog alertFrom = alertAddress.create();
			                          				alertFrom.show();
		break;
	
		
		
		case R.id.textview_To_address:
			// TODO Auto-generated method stub
			
			

				final CharSequence[] toToAddressPOPUPList = {"Please Select","Airport","Suburb"};
				                          				

				                          				AlertDialog.Builder alertToAddress = new AlertDialog.Builder(EditJob.this);
				                          				alertToAddress.setTitle("Select from address.");
				                          				alertToAddress.setSingleChoiceItems(toToAddressPOPUPList,
				                          						Integer.parseInt((String)textview_To_address.getTag()),
				                          						new DialogInterface.OnClickListener() {
				                          							public void onClick(DialogInterface dialog, int item) {
				                          								// Do something with the selection
				                          								if (item == 2) {
				                          									
				                          															PICKORDESTINATION = 2;
				                          									
				                          															Intent intent = new Intent(EditJob.this,
				                          																	SuburbActivity.class);
				                          															intent.putExtra("FROMEDITJOB", true);
				                          															startActivityForResult(intent, ID_To_SUBURB);
				                          														}
				                          									
				                          														else if (item == 1) {
				                          															PICKORDESTINATION = 2;
				                          															Intent intent = new Intent(EditJob.this,
				                          																	AirportActivity.class);
				                          															intent.putExtra("FROMEDITJOB", true);
				                          															startActivityForResult(intent, ID_TO_AIRPORT);
				                          														} else {
				                          															textview_To_address.setText("");
				                          															
				                          														}
				                          								textview_To_address.setTag(item+"");
				                          								dialog.dismiss();
				                          							}
				                          						});
				                          				AlertDialog alertTo = alertToAddress.create();
				                          				alertTo.show();
			break;
		
		case R.id.button_back:
			onBackPressed();
			break;
		case R.id.button_save:

			addJob();

			break;
		case R.id.button_addjob:

			break;
		case R.id.textview_datetime:
			datePicker();
			break;
		/*
		 * case R.id.textview_pick:
		 * 
		 * final CharSequence[] makeTypeItems = { "Airports", "Suburbs" };
		 * 
		 * AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
		 * builder1.setTitle("Please Select."); builder1.setItems(makeTypeItems,
		 * new DialogInterface.OnClickListener() { public void
		 * onClick(DialogInterface dialog, int item) { // Do something with the
		 * selection
		 * 
		 * startActivityForResult(new Intent(EditJob.this,
		 * SuburbActivity.class), 11);
		 * 
		 * } }); AlertDialog alert1 = builder1.create(); alert1.show();
		 * 
		 * break; case R.id.textview_to:
		 * 
		 * break;
		 */
		case R.id.textview_eventType:

			if (editJobRes != null) {
				final CharSequence[] evetntdes = new CharSequence[editJobRes
						.getData().getEventTypes().size()+1];
				for (int i = 0; i < editJobRes.getData().getEventTypes().size()+1; i++) {
					if(i==0)
					{
						evetntdes[i] = " ";
					
					}else
					{
						evetntdes[i] = editJobRes.getData().getEventTypes().get(i-1)
								.getDescription();
					}
					
				}

				AlertDialog.Builder builderEvent = new AlertDialog.Builder(this);
				builderEvent.setTitle("Select Event");
				builderEvent.setSingleChoiceItems(evetntdes,0,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
								// Do something with the selection
								if(item==0)
								{
									textview_eventType
									.setText("");
									textview_eventType.setTag(null);
								}
								else
								{
									textview_eventType
									.setText(editJobRes
											.getData().getEventTypes()
											.get(item-1).getDescription());
							textview_eventType.setTag(editJobRes
									.getData().getEventTypes().get(item-1));
								}
								
								dialog.dismiss();
							}
						});
				AlertDialog alertEvent = builderEvent.create();
				alertEvent.show();
				
				
				
			} else {
				// LimoUtil.toast("NULL", EditJob.this);
			}

			break;

		default:
			break;
		}
	}

	private void addJob() {
		// TODO Auto-generated method stub

		if (editText_firstName.getText().toString().trim().length() == 0) {

			editText_firstName.setError("Required");
			editText_firstName.requestFocus();
		} else if (editText_lastName.getText().toString().trim().length() == 0) {

			editText_lastName.setError("Required");
			editText_lastName.requestFocus();
		} 
		else if (editText_mobile.getText().toString().trim().length()== 0 ) {// this is temp
			
				editText_mobile.setError("Required");
				editText_mobile.requestFocus();

		}
		
		else if (editText_mobile.getText().toString().trim().length() != 0 &&
				editText_mobile.getText().toString().trim().replaceAll("-", "").length()<10 ) {
			String text=  editText_mobile.getText().toString().trim().replaceAll("-", "");
			if(text .length()<10)
				editText_mobile.setError("Minimum 10 digit");
			editText_mobile.requestFocus();

		}
		
		else if (textview_eventType.getText().toString().trim().length() == 0) {// this is temp

			alertDialog(false, "Event type Required");
		} else if (textview_datetime.getText().toString().trim().length() == 0) {

			alertDialog(false, "Date & Time Required");
		} else if (textview_datetime.getText().toString().trim().length() == 0) {

			alertDialog(false, "Date & Time Required");
		} else if (textview_from_address.getText().toString().trim().length() ==0) {

			alertDialog(false, "Select pickup");
		} else if (textview_To_address.getText().toString().trim().length() ==0) {

			alertDialog(false, "Select destination");
		}else if (edittext_Passengers.getText().toString().trim().length() == 0) {

			editText_lastName.setError("Required");
			editText_lastName.requestFocus();
		} else if (edittext_Passengers.getText().toString().trim().length() == 0) {

			editText_lastName.setError("Required");
			editText_lastName.requestFocus();
		} else if (edittext_Amount.getText().toString().trim().length() == 0) {

			edittext_Amount.setError("Required");
			edittext_Amount.requestFocus();
		} else {

			final JSONObject json = new JSONObject();
			try {
				json.put("FirstName", editText_firstName.getText().toString());

				json.put("LastName", editText_lastName.getText().toString());
				json.put("MobileNo", editText_mobile.getText().toString().replace("-",""));
				String dateReceivedFromUser = textview_datetime.getTag()
						.toString();
				DateFormat userDateFormat = new SimpleDateFormat(
						"dd/MMM/yyyy  h:mm a");
				DateFormat dateFormatNeeded = new SimpleDateFormat(
						"MM/dd/yyyy HH:mm:ss");
				Date date;
				try {
					date = userDateFormat.parse(dateReceivedFromUser);
					String convertedDate = dateFormatNeeded.format(date);
					json.put("BookingDateStartTime", convertedDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				json.put("IsReturnPickUp", switchReturn.isChecked());
				json.put("NoOfpassengers", edittext_Passengers.getText()
						.toString());
				json.put("NoOfBaBySheets", edittext_babysit.getText()
						.toString());
				if(textview_eventType.getText().toString().trim().length()>0)
				{
					json.put("EventTypeId", ((EventTypesPOJO) textview_eventType
							.getTag()).getEventTypeId());
				}

				json.put("PickupTypeId", (String) textview_from_address.getTag());
				json.put("IsDisplay", switch_displayJob.isChecked());

				if(((String)textview_from_address.getTag()).equalsIgnoreCase("1"))// Airport from
				{
					// public int? { get; set; } //If pickup type is Airport,
					// required
					// public string { get; set; } //If pickup type is Airport,
					// required
					if (FromAirportsPojoSelected != null) {
						json.put("Pkup_AirportId",
								FromAirportsPojoSelected.getAirportId());
						json.put("Pkup_FlightNo", FromFlightNo);
					}

				}
				if (((String)textview_from_address.getTag()).equalsIgnoreCase("2"))// Subpurbs from
				{

					json.put("Pickup_StreetNo", fromStreetNo);
					json.put("Pickup_State", LimoUtil.tempState);
					json.put("Pickup_Address", "");
					json.put("Pickup_Suburb", fromStreetSuburbPOST);
					json.put("Pickup_PostalCode", fromPostalcode);
					json.put("Pickup_Landmark", fromLandmark);

				}

				// Destination

				json.put("DestinationTypeId",
						(String) textview_To_address.getTag());

				if (((String)textview_To_address.getTag()).equalsIgnoreCase("1"))// Airport to
				{
					// public int? { get; set; } //If pickup type is Airport,
					// required
					// public string { get; set; } //If pickup type is Airport,
					// required
					if (ToAirportPojoSelected != null) {
						json.put("Destn_AirportId",
								ToAirportPojoSelected.getAirportId());
						json.put("Destn_FlightNo", ToFlightNO);
					}

				}
				if (((String)textview_To_address.getTag()).equalsIgnoreCase("2"))// / Subpurbs
				{

					json.put("Destn_StreetNo", ToStreetNo);
					json.put("Dest_Address", "");
					json.put("Dest_Suburb", ToStreetSuburbPOST);
					json.put("Dest_PostalCode", ToPostalcode);
					json.put("Dest_Landmark", ToLandmark);
					json.put("Dest_State", LimoUtil.tempState);

				}

				json.put("Amount", Double.parseDouble(edittext_Amount.getText()
						.toString()));
				json.put("JobId", jobid);

				json.put("UserKey",
						new UserPefrencePOJO().getUserKey(EditJob.this));
				json.put("SpecialNeeds", switchSpecialNeed.isChecked());
			/*	if (switchSpecialNeed.isChecked()) {
					

				}*/
				json.put("Notes", editText_Notes.getText().toString() + "");
				new AsyncTask<String, Integer, String>() {

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub

						return new WebAPIRequest().perform_PUT_twithjson(
								LimoUtil.URL_JOB_GET_INFo, json);
					}

					protected void onPostExecute(String result) {

						if (result.equalsIgnoreCase("Exception") == false) {
							if (LimoUtil.JOB_FRAGMENT_ACTIVITY != null)
								LimoUtil.JOB_FRAGMENT_ACTIVITY.myJobRefresh();
						}
					}

				}.execute("");
				/*
				 * new CustomAsynTaskJSONPOST(EditJob.this, 2, "Adding job...",
				 * LimoUtil.URL_JOB_GET_EVENT, json).execute("");
				 */
				this.finish();
				if (LimoUtil.JOB_DETAILS != null) {
					LimoUtil.JOB_DETAILS.finish();

				}
				// Toast.makeText(EditJob.this, text, duration)

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	DatePicker datePicker;
	TimePicker timePicker;

	private void datePicker() {
		// TODO Auto-generated method stub

		LinearLayout lin = new LinearLayout(EditJob.this);
		lin.setOrientation(LinearLayout.VERTICAL);

		datePicker = new DatePicker(EditJob.this);
		datePicker.setCalendarViewShown(false);
		timePicker = new TimePicker(EditJob.this);
		int hour = timePicker.getCurrentHour();
		timePicker.setCurrentHour(hour + 1);
		lin.addView(datePicker);
		lin.addView(timePicker);
		AlertDialog.Builder alert = new AlertDialog.Builder(EditJob.this);

		alert.setTitle("Set Date and Time");

		alert.setView(lin);

		alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				Calendar calender = Calendar.getInstance();
				calender.set(datePicker.getYear(), datePicker.getMonth(),
						datePicker.getDayOfMonth(),
						timePicker.getCurrentHour(),
						timePicker.getCurrentMinute());
				/*
				 * SimpleDateFormat sdf = new SimpleDateFormat(
				 * "dd/MM/yyyy hh:mm:ss");
				 */
				SimpleDateFormat sdf = new SimpleDateFormat(
						"dd/MMM/yyyy  h:mm a");
				SimpleDateFormat sdfForOnlyShow = new SimpleDateFormat(
						"MMM yyyy  h:mm a");
				try {

					// Date date = firstFormatter .parse(theDate );
					// SimpleDateFormat sd = new
					// SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
					String strdate = sdf.format(calender.getTime());
					String strdateDisplay = sdfForOnlyShow.format(calender.getTime());
					String strDisplayDate=LimoUtil.setNumberFormat(datePicker.getDayOfMonth())+" "+strdateDisplay;
					
					textview_datetime.setText(strDisplayDate);
					textview_datetime.setTag(strdate);
					
				} catch (Exception e) {
					// TODO: handle exception e.printStackTrace();
				}

			}
		});

		alert.setNegativeButton("Cancel", null);
		alert.show();
	}

	private void alertDialog(boolean isSucess, String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(EditJob.this);
		alert.setMessage(string);
		if (isSucess) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							EditJob.this.finish();
						}
					});
		} else {
			alert.setPositiveButton("OK", null);
		}
		alert.show();

	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		if (result.equalsIgnoreCase("Exception") == false) {
			switch (taskId) {
			case 1:// Event

				// LimoUtil.toast(result, EditJob.this);

				Gson gson = new Gson();
				editJobRes = gson.fromJson(result, EditJobRes.class);
				Ui1();
				break;
			case 2:// saveJob

				// LimoUtil.toast(result, EditJob.this);

				/*
				 * {"Status":"Success", "Data":null,
				 * "Message":["A job has been add successfully"] }
				 */

				if (result != "Exception") {

					try {
						JSONObject jsonobjet = new JSONObject(result);

						String status = jsonobjet.getString("Status");
						String msg = jsonobjet.getJSONArray("Message")
								.getString(0);

						if (status.equalsIgnoreCase("Success")) {
							alertDialog(true, msg);
						} else {
							alertDialog(false, msg);

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

	}

	private void Ui1() {
		// TODO Auto-generated method stub

		editText_firstName
				.setText(editJobRes.getData().getJob().getFirstName());
		editText_lastName.setText(editJobRes.getData().getJob().getLastName());
		editText_mobile.setText(LimoUtil.convertMobileFormat(editJobRes.getData().getJob().getMobileNo()));
		edittext_Amount.setText(editJobRes.getData().getJob().getAmount() + "");
		edittext_Passengers.setText(editJobRes.getData().getJob()
				.getNoOfpassengers()
				+ "");
		edittext_babysit.setText(editJobRes.getData().getJob()
				.getNoOfBaBySheets()
				+ "");

		for (int i = 0; i < editJobRes.getData().getEventTypes().size(); i++) {
			if (editJobRes.getData().getEventTypes().get(i).getEventTypeId() == editJobRes
					.getData().getJob().getEventTypeId()) {
				textview_eventType.setTag(editJobRes.getData().getEventTypes()
						.get(i));

				textview_eventType.setText(editJobRes.getData().getEventTypes()
						.get(i).getDescription());
				break;
			}
		}

		switch_displayJob.setChecked(editJobRes.getData().getJob()
				.isIsDisplay());
		switchReturn.setChecked(editJobRes.getData().getJob()
				.isIsReturnPickUp());
		switchSpecialNeed.setChecked(editJobRes.getData().getJob()
				.isSpecialNeeds());

		editText_Notes.setText(editJobRes.getData().getJob().getNotes());

		if (editJobRes.getData().getJob().getPickup_Suburb() == null)// pickup
																		// airport
		{

			for (int i = 0; i < editJobRes.getData().getAirports().size(); i++) {

				if (editJobRes.getData().getAirports().get(i).getAirportId() == editJobRes
						.getData().getJob().getPkup_AirportId()) {
					FromAirportsPojoSelected = editJobRes.getData()
							.getAirports().get(i);
					FromFlightNo = editJobRes.getData().getJob()
							.getPkup_FlightNo();
					fromAirportPOS = i;

					fromStreetNo = "";
					fromStreetName = "";
					fromStreetSuburb = "";
					fromStreetSuburbPOST= "";
						fromLandmark = "";
					fromPostalcode = "";
					fromState = "";

					String fromAddress = FromFlightNo + ","
							+ FromAirportsPojoSelected.getAirportName();
					textview_from_address.setText(fromAddress);
					textview_from_address.setVisibility(View.VISIBLE);
					textview_from_address.setTag(1+"");
					break;
				}
			}

		} else {

			fromLandmark = editJobRes.getData().getJob().getPickup_Landmark();
			fromStreetNo = editJobRes.getData().getJob().getPickup_StreetNo();
			fromState = editJobRes.getData().getJob().getPickup_State();
			fromStreetSuburbPOST = editJobRes.getData().getJob().getPickup_Suburb();
			fromStreetName = editJobRes.getData().getJob().getPickup_Address();
			fromPostalcode = editJobRes.getData().getJob()
					.getPickup_PostalCode();
			fromAirportPOS = -1;
			FromFlightNo = "";
			fromStreetSuburb = fromStreetSuburbPOST+", "+fromState+"("+fromPostalcode+")";
			String fromAddress = fromStreetNo + "," + fromStreetName + ","+ fromLandmark + ","
					+ fromStreetSuburbPOST  + fromState
					+ "," + fromPostalcode;
			textview_from_address.setText(fromAddress);
			textview_from_address.setVisibility(View.VISIBLE);
			textview_from_address.setTag(2+"");
		}

		if (editJobRes.getData().getJob().getDest_Suburb() == null)// destination
																	// airport
		{

			for (int i = 0; i < editJobRes.getData().getAirports().size(); i++) {

				if (editJobRes.getData().getAirports().get(i).getAirportId() == editJobRes
						.getData().getJob().getDestn_AirportId()) {
					ToAirportPojoSelected = editJobRes.getData().getAirports()
							.get(i);
					ToFlightNO = editJobRes.getData().getJob()
							.getDestn_FlightNo();
					ToAirportPOS = i;

					ToStreetNo = "";
					ToStreetName = "";
					ToStreetSuburb = "";
					ToStreetSuburbPOST = "";
					
					ToLandmark = "";
					ToPostalcode = "";
					ToState = "";

					String ToAddress = ToFlightNO + ","
							+ ToAirportPojoSelected.getAirportName();
					textview_To_address.setText(ToAddress);
					textview_To_address.setVisibility(View.VISIBLE);
					//Spinner_to.setSelection(1, true);
					textview_To_address.setTag(1+"");
					break;
				}
			}

		} else {

			ToLandmark = editJobRes.getData().getJob().getDest_Landmark();
			ToStreetNo = editJobRes.getData().getJob().getDestn_StreetNo();
			ToState = editJobRes.getData().getJob().getDest_State();
			ToStreetSuburbPOST = editJobRes.getData().getJob().getDest_Suburb();
			ToStreetName = editJobRes.getData().getJob().getDest_Address();
			ToPostalcode = editJobRes.getData().getJob().getDest_PostalCode();
			ToStreetSuburb=ToStreetSuburbPOST+", "+ToState+"("+ToPostalcode+")";
				
			ToAirportPOS = -1;
			ToFlightNO = "";

			String toAddress = ToStreetNo + "," + ToStreetName + ","+ ToLandmark + ","
					+ ToStreetSuburbPOST + ","  + ToState + ","
					+ ToPostalcode;
			textview_To_address.setText(toAddress);
			textview_To_address.setVisibility(View.VISIBLE);
			textview_To_address.setTag(2+"");
		}

		String strdate = editJobRes.getData().getJob()
				.getBookingDateStartTime();
		// textView1_title.setText();
		// Date fds = sdf.parse(strdate);

		// 2013-07-31T11:15:00
		Date javaUtilDate = new Date();
		SimpleDateFormat originalFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat targetformat = new SimpleDateFormat(
				"dd/MMM/yyyy  h:mm a");
		
		SimpleDateFormat targetformatFotdisplay = new SimpleDateFormat(
				"MMM yyyy  h:mm a");
		SimpleDateFormat targetformatFotdisplayDate = new SimpleDateFormat(
				"dd");
		
	

		Date date;
		try {
			date = originalFormat.parse(strdate);

			int intday= Integer.parseInt(targetformatFotdisplayDate.format(date));
		
			textview_datetime.setText(LimoUtil.setNumberFormat(intday)+" "+targetformatFotdisplay.format(date));

			textview_datetime.setTag(targetformat.format(date));

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
