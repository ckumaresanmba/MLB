package com.mylimobook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mylimobook.jobs.AddJob;
import com.mylimobook.pojos.AirportsPojo;

public class AirportActivity extends Activity implements OnClickListener {
	Spinner Spinner_airport;
	EditText editText_FlightNo;
	TextView textView_terminal;
	ArrayList<AirportsPojo> listAirPort ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_airport);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		Button button_back = (Button) findViewById(R.id.button_back);
		button_back.setOnClickListener(this);
		Button button_save_suburbs = (Button) findViewById(R.id.button_save_suburbs);
		button_save_suburbs.setOnClickListener(this);
		editText_FlightNo = (EditText) findViewById(R.id.editText_FlightNo);
		textView_terminal=(TextView) findViewById(R.id.textView_terminal);
		boolean fromEditJob=getIntent().getBooleanExtra("FROMEDITJOB", false);
		if(fromEditJob)
		{
			
			
			if (EditJob.editJobRes != null) {
				Spinner_airport = (Spinner) findViewById(R.id.Spinner_airport);

				listAirPort = EditJob.editJobRes
						.getData().getAirports();
				ArrayList<String> list = new ArrayList<String>();
				list.add("Please Select");
				for (int i = 0; i < listAirPort.size(); i++) {
					list.add(listAirPort.get(i).getAirportName()+","+listAirPort.get(i).getTerminalName());

					System.out.println("A"+listAirPort.get(i).getAirportName()+","+listAirPort.get(i).getTerminalName());		
					}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						AirportActivity.this, R.layout.simple_spinner_item, list);
				/*
				 * = ArrayAdapter.createFromResource( this, R.array.pickupJOb,
				 * R.layout.simple_spinner_item);
				 */adapter
						.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
				Spinner_airport.setAdapter(adapter);

				Spinner_airport
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {

								if(arg2>0)
								{
									textView_terminal.setText(listAirPort.get(arg2-1).getTerminalName());
									
									editText_FlightNo.requestFocus();
									LimoUtil.showKeyboard(editText_FlightNo, AirportActivity.this);
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub

								
							}
						});
			}

			TextView textviewTitle = (TextView) findViewById(R.id.textView1);
			if (EditJob.PICKORDESTINATION == 1) {
				textviewTitle.setText("Pickup Airport ");

				editText_FlightNo.setText(EditJob.FromFlightNo);

				if (EditJob.fromAirportPOS >= 0) {
					Spinner_airport.setSelection(EditJob.fromAirportPOS+1);
				}

			} else {
				if (EditJob.ToAirportPOS >= 0) {
					Spinner_airport.setSelection(EditJob.ToAirportPOS+1);
				}

				textviewTitle.setText("Destination Airport");
				editText_FlightNo.setText(EditJob.ToFlightNO);

			}
		}
			
	
		else
		{
			
			if (AddJob.eventTypeResponcePOJO != null) {
				Spinner_airport = (Spinner) findViewById(R.id.Spinner_airport);

				listAirPort = AddJob.eventTypeResponcePOJO
						.getData().getAirportsPojoList();
				ArrayList<String> list = new ArrayList<String>();
				list.add("Please Select");
				for (int i = 0; i < listAirPort.size(); i++) {
					list.add(listAirPort.get(i).getAirportName()+","+listAirPort.get(i).getTerminalName());

				}
				
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						AirportActivity.this, R.layout.simple_spinner_item, list);
				/*
				 * = ArrayAdapter.createFromResource( this, R.array.pickupJOb,
				 * R.layout.simple_spinner_item);
				 */adapter
						.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
				Spinner_airport.setAdapter(adapter);

				Spinner_airport
						.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0,
									View arg1, int arg2, long arg3) {

								if(arg2>0)
								{
									textView_terminal.setText(listAirPort.get(arg2-1).getTerminalName());
									editText_FlightNo.requestFocus();
									LimoUtil.showKeyboard(editText_FlightNo, AirportActivity.this);
								
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub

								
							}
						});
			}

			TextView textviewTitle = (TextView) findViewById(R.id.textView1);
			if (AddJob.PICKORDESTINATION == 1) {
				textviewTitle.setText("Pickup Airport ");

				editText_FlightNo.setText(AddJob.FromFlightNo);

				if (AddJob.fromAirportPOS >= 0) {
					Spinner_airport.setSelection(AddJob.fromAirportPOS+1);
				}

			} else {
				if (AddJob.ToAirportPOS >= 0) {
					Spinner_airport.setSelection(AddJob.ToAirportPOS+1);
				}

				textviewTitle.setText("Destination Airport");
				editText_FlightNo.setText(AddJob.ToFlightNO);

			}
		}
		 // Spawn a thread that triggers the Spinner to open after 5 seconds...

		new Handler().postDelayed(new Runnable() {

			public void run() {
				
				// TODO Auto-generated method stub
				if(Spinner_airport!=null)
				Spinner_airport.performClick();
			}
		}, 200);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_back:
			setResult(Activity.RESULT_CANCELED);
			onBackPressed();
			break;
		case R.id.button_save_suburbs:

			if (Spinner_airport.getSelectedItemPosition() != 0) {
				if (editText_FlightNo.getText().toString().length() > 0) {
					Intent intent = new Intent();
					intent.putExtra("airportPOS",
							Spinner_airport.getSelectedItemPosition() - 1);
					intent.putExtra("FlightNo", editText_FlightNo.getText()
							.toString());

					setResult(Activity.RESULT_OK, intent);
					this.finish();

				} else {

					editText_FlightNo.setError("Required");
					editText_FlightNo.requestFocus();
				}
			}
			/*
			 * }
			 * 
			 * if (ed.getText().toString().trim().length() == 0) {
			 * editText_StreetNo.setError("Required");
			 * editText_StreetNo.requestFocus(); } else if
			 * (editText_StreetName.getText().toString().trim().length() == 0) {
			 * editText_StreetName.setError("Required");
			 * editText_StreetName.requestFocus(); } else if
			 * (AutoCompleteTextView_Suburb.getText().toString().trim()
			 * .length() == 0) {
			 * AutoCompleteTextView_Suburb.setError("Required");
			 * AutoCompleteTextView_Suburb.requestFocus(); } else { Intent
			 * intent = new Intent(); intent.putExtra("S1",
			 * editText_StreetNo.getText().toString()); intent.putExtra("S2",
			 * editText_StreetName.getText().toString()); intent.putExtra("S3",
			 * AutoCompleteTextView_Suburb.getText() .toString());
			 * 
			 * setResult(Activity.RESULT_OK, intent); this.finish(); }
			 */
			break;

		default:
			break;
		}
	}

}
