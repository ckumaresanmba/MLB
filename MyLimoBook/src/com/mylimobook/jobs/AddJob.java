package com.mylimobook.jobs;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.gson.Gson;
import com.mylimobook.AirportActivity;
import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.SuburbActivity;
import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.pojos.AirportsPojo;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;

public class AddJob extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	Button button_back, button_save, button_addjob1;

	EditText editText_firstName, editText_lastName, editText_mobile,
			edittext_Passengers, edittext_babysit, editText_Notes,
			edittext_Amount;
	TextView textview_datetime, textview_eventType;
	Switch switchReturn, switch_displayJob, switchSpecialNeed;
	//Spinner Spinner_pick1, Spinner_to;

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
			fromStreetSuburb = "", fromLandmark = "", fromPostalcode = "",fromStreetSuburbForPOST="",
			fromState = "";
	public static String ToStreetNo = "", ToStreetName = "",
			ToStreetSuburb = "", ToLandmark = "", ToPostalcode = "",ToStreetSuburbForPOST="",
			ToState = "";

	public static AirportsPojo FromAirportsPojoSelected, ToAirportPojoSelected;

	// ----------------
	public static EventTypeResponcePOJO eventTypeResponcePOJO;

	TextView textview_from_address, textview_To_address;
	
	
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
		fromStreetSuburbForPOST = "";
		fromLandmark = "";
		fromPostalcode = "";
		fromState = "";
		ToStreetNo = "";
		ToStreetName = "";
		ToStreetSuburb = "";
		ToStreetSuburbForPOST="";
		ToLandmark = "";
		ToPostalcode = "";
		ToState = "";

		FromAirportsPojoSelected = null;
		ToAirportPojoSelected = null;
	}



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_job);
		textview_from_address = (TextView) findViewById(R.id.textview_from_address);
		textview_To_address = (TextView) findViewById(R.id.textview_To_address);
		
		textview_from_address.setTag(0+"");
		textview_To_address.setTag(0+"");
		textview_from_address.setOnClickListener(this);
		textview_To_address.setOnClickListener(this);
		
		
		TableRow rowFrom=(TableRow) findViewById(R.id.rowFrom);
		rowFrom.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out
						.println("AddJob.onCreate(...).new OnClickListener() {...}.onClick()");
			}
		});
		
		
		
		PICKORDESTINATION = 0;

		// Use for pick up and destination address
		FromFlightNo = "";
		ToFlightNO = "";
		fromAirportPOS = -1;
		ToAirportPOS = -1;

		fromStreetNo = "";
		fromStreetName = "";
		fromStreetSuburb = "";
		fromStreetSuburbForPOST="";
		fromLandmark = "";
		fromPostalcode = "";
		fromState = "";
		ToStreetNo = "";
		ToStreetName = "";
		ToStreetSuburb = "";
		ToStreetSuburbForPOST="";
		ToLandmark = "";
		ToPostalcode = "";
		ToState = "";

		FromAirportsPojoSelected = null;
		ToAirportPojoSelected = null;
		
		datePicker = new DatePicker(AddJob.this);
		timePicker = new TimePicker(AddJob.this);

		new CustomAsynTaskGET(AddJob.this, 1, "Loading...")
				.execute(LimoUtil.URL_JOB_GET_EVENT);
		initView();
	
		fromPicker();
		
	}

	private void fromPicker() {
		System.out.println("AddJob.fromPicker()");
		// TODO Auto-generated method stub
		/*LinearLayout linFromspin= (LinearLayout) findViewById(R.id.linFromspin);
		linFromspin.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			
				}
				return false;
			}
		});*/
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
						editText_mobile.setText(str.substring(0, 4) + " " + str.substring(4));
						editText_mobile.setSelection(editText_mobile.getText().length());

						System.out.println(editText_mobile.getText());
					} else if (start == 8) {
						str = s + "";
						editText_mobile.setText(str.substring(0, 8) + " " + str.substring(8));
						editText_mobile.setSelection(editText_mobile.getText().length());
						System.out.println(editText_mobile.getText());

					}

					// PhoneNumberUtils.formatNumber(ed_mobile.getText(),
					// PhoneNumberUtils.FORMAT_NANP);

					if (s.length() ==4) {
						editText_mobile.setText(s + " ");
						editText_mobile.setSelection(editText_mobile.getText().length());

						System.out.println(editText_mobile.getText());

					} else if (s.length() == 8) {
						editText_mobile.setText(s + " ");
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
		//Spinner_pick = (Spinner) findViewById(R.id.Spinner_pick);
		//Spinner_to = (Spinner) findViewById(R.id.Spinner_to);

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
						}
*/
					}
				});
		editText_Notes.setVisibility(View.VISIBLE);
		// button_addjob.setOnClickListener(this);
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
//				if (arg2 == 2) {
//					PICKORDESTINATION = 1;
//					startActivityForResult(new Intent(AddJob.this,
//							SuburbActivity.class), ID_FROM_SUBURB);
//
//				}
//
//				else if (arg2 == 1) {
//
//					PICKORDESTINATION = 1;
//					startActivityForResult(new Intent(AddJob.this,
//							AirportActivity.class), ID_FROM_AIRPORT);
//				}
//				
//				else
//				{
//					textview_from_address.setText("");
//					textview_from_address.setVisibility(View.GONE);
//				}
//			//	ignoreOldSelectionByReflection(Spinner_pick);
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//			/*	LimoUtil.toast(Spinner_pick.getSelectedItemPosition() + "",
//						AddJob.this);*/
//			}
//		});
//		Spinner_to.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				if (arg2 == 2) {
//
//					PICKORDESTINATION = 2;
//					startActivityForResult(new Intent(AddJob.this,
//							SuburbActivity.class), ID_To_SUBURB);
//				}
//
//				else if (arg2 == 1) {
//					PICKORDESTINATION = 2;
//					startActivityForResult(new Intent(AddJob.this,
//							AirportActivity.class), ID_TO_AIRPORT);
//				}
//				else
//				{
//					textview_To_address.setText("");
//					textview_To_address.setVisibility(View.GONE);
//		
//				}
//				//ignoreOldSelectionByReflection(Spinner_to);
//				
//				
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});

		
		
		
		/*textview_To_address.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				textview_To_address.setSelected(true);
				textview_To_address.setFocusableInTouchMode(true);

				return false;
			}
		});
		textview_from_address.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				textview_from_address.setSelected(true);
				textview_from_address.setFocusableInTouchMode(true);

				return false;
			}
		});*/
		
	}

	/*private void ignoreOldSelectionByReflection(Spinner spin) {
		try {
			Class<?> c = spin.getClass().getSuperclass().getSuperclass()
					.getSuperclass();
			Field reqField = c.getDeclaredField("mOldSelectedPosition");
			reqField.setAccessible(true);
			reqField.setInt(spin, -1);
		} catch (Exception e) {
			Log.d("Exception Private", "ex", e);
			// TODO: handle exception
		}
	}*/

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ID_FROM_SUBURB) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
				//Spinner_pick.setSelection(0);
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
					fromStreetSuburbForPOST = data.getStringExtra("S7");

					fromAirportPOS = -1;
					FromFlightNo = "";

					String fromAddress = fromStreetNo + "," + fromStreetName
							+ ","  + fromLandmark + ","+ fromStreetSuburbForPOST + ","
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
			//	Spinner_to.setSelection(0);
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
					ToStreetSuburbForPOST= data.getStringExtra("S7");
					ToAirportPOS = -1;
					ToFlightNO = "";

					String toAddress = ToStreetNo + "," + ToStreetName + ","
							 + ToLandmark + ","+ ToStreetSuburbForPOST + ","
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
					FromAirportsPojoSelected = eventTypeResponcePOJO.getData()
							.getAirportsPojoList().get(fromAirportPOS);
					FromFlightNo = data.getStringExtra("FlightNo");
					// airportName=data.getStringExtra("airportPOS");

					fromStreetNo = "";
					fromStreetName = "";
					fromStreetSuburb = "";
					fromStreetSuburbForPOST = "";
					fromLandmark = "";
					fromPostalcode = "";
					fromState = "";

					String fromAddress = FromFlightNo + ","
							+ FromAirportsPojoSelected.getAirportName();
					textview_from_address.setText(fromAddress);
				
					textview_from_address.setTag(1+"");
				}

			}

		} else if (requestCode == ID_TO_AIRPORT) {
			if (resultCode == Activity.RESULT_CANCELED) {
				// textview_pick.setText("");
				//Spinner_to.setSelection(0);
				textview_To_address.setText("");
				textview_To_address.setTag(0+"");
			

			} else if (resultCode == Activity.RESULT_OK) {

				if (data != null) {
					ToAirportPOS = data.getIntExtra("airportPOS", -1);
					ToAirportPojoSelected = eventTypeResponcePOJO.getData()
							.getAirportsPojoList().get(ToAirportPOS);
					ToFlightNO = data.getStringExtra("FlightNo");
					// airportName=data.getStringExtra("airportPOS");

					ToStreetNo = "";
					ToStreetName = "";
					ToStreetSuburb = "";
					ToStreetSuburbForPOST="";
					ToLandmark = "";
					ToPostalcode = "";
					ToState = "";

					String ToAddress = ToFlightNO + ","
							+ ToAirportPojoSelected.getAirportName();
					textview_To_address.setText(ToAddress);
					
	
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
			                          				

			                          				AlertDialog.Builder alertAddress = new AlertDialog.Builder(AddJob.this);
			                          				alertAddress.setTitle("Select from address.");
			                          				alertAddress.setSingleChoiceItems(fromToAddressPOPUPList,Integer.parseInt((String)textview_from_address.getTag()),
			                          						new DialogInterface.OnClickListener() {
			                          							public void onClick(DialogInterface dialog, int item) {
			                          								// Do something with the selection
			                          								
			                          								if (item == 2) {
			                          									PICKORDESTINATION = 1;
			                          									startActivityForResult(new Intent(AddJob.this,
			                          											SuburbActivity.class), ID_FROM_SUBURB);

			                          								}

			                          								else if (item == 1) {

			                          									PICKORDESTINATION = 1;
			                          									startActivityForResult(new Intent(AddJob.this,
			                          											AirportActivity.class), ID_FROM_AIRPORT);
			                          								}
			                          								
			                          								else
			                          								{
			                          									textview_from_address.setText("");
			                          									
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
				                          				

				                          				AlertDialog.Builder alertToAddress = new AlertDialog.Builder(AddJob.this);
				                          				alertToAddress.setTitle("Select from address.");
				                          				alertToAddress.setSingleChoiceItems(toToAddressPOPUPList,
				                          						Integer.parseInt((String)textview_To_address.getTag()),
				                          						new DialogInterface.OnClickListener() {
				                          							public void onClick(DialogInterface dialog, int item) {
				                          								// Do something with the selection
				                          								if (item == 2) {
				                          						PICKORDESTINATION = 2;
				                          						startActivityForResult(new Intent(AddJob.this,
				                          																SuburbActivity.class), ID_To_SUBURB);
				                          													}
				                          									
				                          													else if (item == 1) {
				                          														PICKORDESTINATION = 2;
				                          														startActivityForResult(new Intent(AddJob.this,
				                          																AirportActivity.class), ID_TO_AIRPORT);
				                          													}
				                          													else
				                          													{
				                          														textview_To_address.setText("");
				                          														textview_To_address.setVisibility(View.GONE);
				                          											
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
		 * startActivityForResult(new Intent(AddJob.this, SuburbActivity.class),
		 * 11);
		 * 
		 * } }); AlertDialog alert1 = builder1.create(); alert1.show();
		 * 
		 * break; case R.id.textview_to:
		 * 
		 * break;
		 */
//			
//case R.id.textview_To_address:
//			
//			final CharSequence[] fromToAddressPOPUPList = {"Please Select","Airport","Suburb"};
//			                          				
//
//			                          				AlertDialog.Builder alertAddress = new AlertDialog.Builder(this);
//			                          				alertAddress.setTitle("Select destination address.");
//			                          				alertAddress.setSingleChoiceItems(fromToAddressPOPUPList,0,
//			                          						new DialogInterface.OnClickListener() {
//			                          							public void onClick(DialogInterface dialog, int item) {
//			                          								// Do something with the selection
//			                          								
//			                          								if (item == 2) {
//			                          									PICKORDESTINATION = 1;
//			                          									startActivityForResult(new Intent(AddJob.this,
//			                          											SuburbActivity.class), ID_FROM_SUBURB);
//
//			                          								}
//
//			                          								else if (item == 1) {
//
//			                          									PICKORDESTINATION = 1;
//			                          									startActivityForResult(new Intent(AddJob.this,
//			                          											AirportActivity.class), ID_FROM_AIRPORT);
//			                          								}
//			                          								
//			                          								else
//			                          								{
//			                          									textview_from_address.setText("");
//			                          									
//			                          								}
//			                          								
//			                          								dialog.dismiss();
//			                          							}
//			                          						});
//			                          				AlertDialog alertFrom = alertAddress.create();
//			                          				alertFrom.show();
//			                          			
//			
//			
//			break;
			
	

			
		case R.id.textview_eventType:

			if (eventTypeResponcePOJO != null) {
				final CharSequence[] evetntdes = new CharSequence[eventTypeResponcePOJO
						.getData().getEventTypes().size()+1];
				for (int i = 0; i < eventTypeResponcePOJO.getData()
						.getEventTypes().size()+1; i++) {
					if(i==0)
					{
						evetntdes[i] = " ";
					}
					else
					{
						evetntdes[i] = eventTypeResponcePOJO.getData()
								.getEventTypes().get(i-1).getDescription();
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
									.setText(eventTypeResponcePOJO
											.getData().getEventTypes()
											.get(item-1).getDescription());
							textview_eventType.setTag(eventTypeResponcePOJO
									.getData().getEventTypes().get(item-1));
								}
								
								dialog.dismiss();
							}
						});
				AlertDialog alertEvent = builderEvent.create();
				alertEvent.show();
			} else {
				//LimoUtil.toast("NULL", AddJob.this);
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
				editText_mobile.getText().toString().trim().replaceAll(" ", "").length()<10 ) {
			String text=  editText_mobile.getText().toString().trim().replaceAll(" ", "");
			if(text .length()<10)
				editText_mobile.setError("Minimum 10 digit");
			editText_mobile.requestFocus();

		}
	
		
		
		else if (textview_eventType.getText().toString().trim().length() == 0) {

			alertDialog(false, "Event type Required");
		} else if (textview_datetime.getText().toString().trim().length() == 0) {

			alertDialog(false, "Date & Time Required");
		} else if (textview_datetime.getText().toString().trim().length() == 0) {

			alertDialog(false, "Date & Time Required");
		} else if (textview_from_address.getText().toString().trim().length() ==0) {

			alertDialog(false, "Select pickup");
		} else if (textview_To_address.getText().toString().trim().length() ==0) {

			alertDialog(false, "Select destination");
		} else if (edittext_Passengers.getText().toString().trim().length() == 0) {

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
				json.put("MobileNo", editText_mobile.getText().toString().replace(" ",""));

				String dateReceivedFromUser = textview_datetime.getTag()
						.toString();
				//jayd
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
				

				json.put("PickupTypeId",(String) textview_from_address.getTag());
				json.put("IsDisplay", switch_displayJob.isChecked());

				if (((String)textview_from_address.getTag()).equalsIgnoreCase("1"))// Airport from
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
					json.put("Pickup_Address", fromStreetName);
					json.put("Pickup_Suburb", fromStreetSuburbForPOST);
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
					json.put("Dest_Address", ToStreetName);
					json.put("Dest_Suburb", ToStreetSuburbForPOST);
					json.put("Dest_PostalCode", ToPostalcode);
					json.put("Dest_Landmark", ToLandmark);
					json.put("Dest_State", LimoUtil.tempState);

				}

				json.put("Amount", Double.parseDouble(edittext_Amount.getText()
						.toString()));
				json.put("UserKey",
						new UserPefrencePOJO().getUserKey(AddJob.this));
				json.put("SpecialNeeds", switchSpecialNeed.isChecked());
				/*if (switchSpecialNeed.isChecked()) {
					json.put("Notes", editText_Notes.getText().toString() + "");

				}*/
				json.put("Notes", editText_Notes.getText().toString() + "");
				new AsyncTask<String, Integer, String>() {

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub

						return new WebAPIRequest().performPostwithjson(
								LimoUtil.URL_JOB_GET_EVENT, json);
					}

					protected void onPostExecute(String result) {

						if (result.equalsIgnoreCase("Exception") == false) {
							if (LimoUtil.JOB_FRAGMENT_ACTIVITY != null)
								LimoUtil.JOB_FRAGMENT_ACTIVITY.myJobRefresh();
						}
					}

				}.execute("");
				/*
				 * new CustomAsynTaskJSONPOST(AddJob.this, 2, "Adding job...",
				 * LimoUtil.URL_JOB_GET_EVENT, json).execute("");
				 */
				this.finish();
				// Toast.makeText(AddJob.this, text, duration)

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

		LinearLayout lin = new LinearLayout(AddJob.this);
		lin.setOrientation(LinearLayout.VERTICAL);

		datePicker = new DatePicker(AddJob.this);
		datePicker.setCalendarViewShown(false);
		timePicker = new TimePicker(AddJob.this);
		int hour = timePicker.getCurrentHour();
		timePicker.setCurrentHour(hour + 1);
		lin.addView(datePicker);
		lin.addView(timePicker);
		AlertDialog.Builder alert = new AlertDialog.Builder(AddJob.this);

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
			//	10th Aug 2013 12:45 PM.
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
		AlertDialog.Builder alert = new AlertDialog.Builder(AddJob.this);
		alert.setMessage(string);
		if (isSucess) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							AddJob.this.finish();
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

		if(result.equalsIgnoreCase("Exception")==false)
		{
			switch (taskId) {
			case 1:// Event

				// LimoUtil.toast(result, AddJob.this);

				Gson gson = new Gson();
				eventTypeResponcePOJO = gson.fromJson(result,
						EventTypeResponcePOJO.class);
				break;
			case 2:// saveJob

				// LimoUtil.toast(result, AddJob.this);

				/*
				 * {"Status":"Success", "Data":null,
				 * "Message":["A job has been add successfully"] }
				 */

				if (result != "Exception") {

					try {
						JSONObject jsonobjet = new JSONObject(result);

						String status = jsonobjet.getString("Status");
						String msg = jsonobjet.getJSONArray("Message").getString(0);

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

}
