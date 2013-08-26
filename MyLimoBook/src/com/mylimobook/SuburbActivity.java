package com.mylimobook;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mylimobook.db.DataBaseHelper;
import com.mylimobook.jobs.AddJob;

public class SuburbActivity extends Activity implements OnClickListener {

	AutoCompleteTextView AutoCompleteTextView_Suburb;
	ArrayList<DBROWPOJO> dbList1;
	// HashMap<Integer, DBROWPOJO> dbHas;

	EditText editText_StreetNo, editText_StreetName, editText_Landmark;
	TextView editText_PostalCode,TextView_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_suburb);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
	
		dbList1 = new ArrayList<DBROWPOJO>();
		// dbHas = new HashMap<Integer, DBROWPOJO>();
		DataBaseHelper dbhelper = new DataBaseHelper(SuburbActivity.this);
		dbhelper.openDataBase();
		Cursor cursor = dbhelper.getAlldataFromDb("NSW");
		// System.out.println();
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++) {
			// dbList.add();

			dbList1.add(

			new DBROWPOJO(cursor.getInt(0), cursor.getString(1), cursor
					.getString(2), cursor.getString(3), cursor.getString(4),
					cursor.getString(5)));
			cursor.moveToNext();
		}
		cursor.close();
		dbhelper.close();
		prepareMyList();
		
		ArrayAdapter<String> adapter= new ArrayAdapter<String>(SuburbActivity.this, R.layout.simple_spinner_dropdown_item, myList);
		// adapter = new MyBaseAdapter();

		// CustomerAdapter customerAdapter= new
		// CustomerAdapter(SuburbActivity.this,android.R.layout.simple_dropdown_item_1line,
		// dbList1);

		AutoCompleteTextView_Suburb = (AutoCompleteTextView) findViewById(R.id.AutoCompleteTextView_Suburb);

		AutoCompleteTextView_Suburb.setThreshold(2);
		AutoCompleteTextView_Suburb.setAdapter(adapter);

		editText_PostalCode = (TextView) findViewById(R.id.editText_PostalCode);
		TextView_state = (TextView) findViewById(R.id.TextView_state);
			
		AutoCompleteTextView_Suburb
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> adapterView,
							View arg1, int position, long arg3) {
						// TODO Auto-generated method stub
						/*DBROWPOJO str = (DBROWPOJO) adapterView
								.getItemAtPosition(position);*/
						
						String str=(String) adapterView.getItemAtPosition(position);
						for (int i = 0; i < myList.size(); i++) {
							if(str.equalsIgnoreCase(dbList1.get(i).getSuburbDesc()+", "+dbList1.get(i).getState()+"("+dbList1.get(i).getPostalcode()+")"))
							{
								AutoCompleteTextView_Suburb.setTag(dbList1.get(i).getSuburbDesc());
								editText_PostalCode.setText(dbList1.get(i).getPostalcode()+"");
								TextView_state.setText(dbList1.get(i).getState()+"");
								editText_StreetNo.requestFocus();
							}
						}
						

					}
				});
		Button button_back = (Button) findViewById(R.id.button_back);
		Button button_save_suburbs = (Button) findViewById(R.id.button_save_suburbs);

		button_save_suburbs.setOnClickListener(this);
		button_back.setOnClickListener(this);

		editText_StreetNo = (EditText) findViewById(R.id.editText_StreetNo);
		editText_StreetName = (EditText) findViewById(R.id.editText_StreetName);
		editText_Landmark = (EditText) findViewById(R.id.editText_Landmark);
		AutoCompleteTextView_Suburb.setOnFocusChangeListener(new OnFocusChangeListener() {
		    public void onFocusChange(View v, boolean hasFocus) {
		        if(hasFocus) {
		        	//AutoCompleteTextView_Suburb.showDropDown();
		        	//g0-8\LimoUtil.hideKeyboard(AutoCompleteTextView_Suburb, SuburbActivity.this);7]pio
		        }
		    }
		});
		
		
		boolean fromEditJob=getIntent().getBooleanExtra("FROMEDITJOB", false);
		// set old data
		TextView textviewTitle=(TextView) findViewById(R.id.textView1);
		if(fromEditJob)
		{
			if(EditJob.PICKORDESTINATION==1)
			{
				textviewTitle.setText("Pickup Suburb ");
				editText_PostalCode.setText(EditJob.fromPostalcode);
				AutoCompleteTextView_Suburb.setText(EditJob.fromStreetSuburb);
				TextView_state.setText(EditJob.fromState);
				editText_StreetNo.setText(EditJob.fromStreetNo);
				editText_StreetName.setText(EditJob.fromStreetName);
				editText_Landmark.setText(EditJob.fromLandmark);
				
				
				
			}
			else
			{
				textviewTitle.setText("Destination Suburb");
				editText_PostalCode.setText(EditJob.ToPostalcode);
				AutoCompleteTextView_Suburb.setText(EditJob.ToStreetSuburb);
				TextView_state.setText(EditJob.ToState);
				editText_StreetNo.setText(EditJob.ToStreetNo);
				editText_StreetName.setText(EditJob.ToStreetName);
				editText_Landmark.setText(EditJob.ToLandmark);
				
				
			}
		}
		else //from add job
		{
			if(AddJob.PICKORDESTINATION==1)
			{
				textviewTitle.setText("Pickup Suburb ");
				editText_PostalCode.setText(AddJob.fromPostalcode);
				AutoCompleteTextView_Suburb.setText(AddJob.fromStreetSuburb);
				TextView_state.setText(AddJob.fromState);
				editText_StreetNo.setText(AddJob.fromStreetNo);
				editText_StreetName.setText(AddJob.fromStreetName);
				editText_Landmark.setText(AddJob.fromLandmark);
				
				
				
			}
			else
			{
				textviewTitle.setText("Destination Suburb");
				editText_PostalCode.setText(AddJob.ToPostalcode);
				AutoCompleteTextView_Suburb.setText(AddJob.ToStreetSuburb);
				TextView_state.setText(AddJob.ToState);
				editText_StreetNo.setText(AddJob.ToStreetNo);
				editText_StreetName.setText(AddJob.ToStreetName);
				editText_Landmark.setText(AddJob.ToLandmark);
				
				
			}
		}
	
		
		
	}

	ArrayList<String> myList;

	private void prepareMyList() { // prepare your list of words for
	myList = new ArrayList<String>();
		for (int i = 0; i < dbList1.size(); i++) {
			myList.add(dbList1.get(i).getSuburbDesc()+", "+dbList1.get(i).getState()+"("+dbList1.get(i).getPostalcode()+")");
		}
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

			if (editText_StreetNo.getText().toString().trim().length() == 0) {
				editText_StreetNo.setError("Required");
				editText_StreetNo.requestFocus();
			} else if (editText_StreetName.getText().toString().trim().length() == 0) {
				editText_StreetName.setError("Required");
				editText_StreetName.requestFocus();
			}/*else if (editText_Landmark.getText().toString().trim().length() == 0) {
				editText_Landmark.setError("Required");
				editText_Landmark.requestFocus();
			}*/ 
			else if (AutoCompleteTextView_Suburb.getText().toString()
					.length() == 0) {
				AutoCompleteTextView_Suburb.setError("Required");
				AutoCompleteTextView_Suburb.requestFocus();
			} else if (editText_PostalCode.getText().toString().trim().length() == 0) {
				LimoUtil.alertDialog("Suburb select from list.", SuburbActivity.this);
			} else {
				Intent intent = new Intent();
				intent.putExtra("S1", editText_StreetNo.getText().toString());
				intent.putExtra("S2", editText_StreetName.getText().toString());
				intent.putExtra("S3", AutoCompleteTextView_Suburb.getText()
						.toString());
				intent.putExtra("S4", editText_Landmark.getText().toString()+"");
				intent.putExtra("S5", editText_PostalCode.getText().toString());
				intent.putExtra("S6", TextView_state.getText().toString());
				intent.putExtra("S7", (String)AutoCompleteTextView_Suburb.getTag());

				setResult(Activity.RESULT_OK, intent);
				this.finish();
			}

			break;

		default:
			break;
		}
	}

	/*
	 * class MyBaseAdapter extends BaseAdapter implements Filterable { private
	 * LayoutInflater mInflater;
	 * 
	 * MyBaseAdapter() { mInflater = mInflater =
	 * LayoutInflater.from(SuburbActivity.this); }
	 * 
	 * @Override public int getCount() { // TODO Auto-generated method stub
	 * return dbHas.size(); }
	 * 
	 * @Override public Object getItem(int arg0) { // TODO Auto-generated method
	 * stub return dbHas.get(new Integer(arg0)); }
	 * 
	 * @Override public long getItemId(int arg0) { // TODO Auto-generated method
	 * stub return 0; }
	 * 
	 * // , dbHas
	 * 
	 * @Override public View getView(int arg0, View convertView, ViewGroup arg2)
	 * { // TODO Auto-generated method stub ViewHolder holder; if (convertView
	 * == null) { convertView = mInflater.inflate(
	 * android.R.layout.simple_dropdown_item_1line, null); holder = new
	 * ViewHolder();
	 * 
	 * holder.textview = (TextView) convertView;
	 * 
	 * convertView.setTag(holder);
	 * 
	 * } else { holder = (ViewHolder) convertView.getTag(); }
	 * 
	 * return convertView; }
	 * 
	 * 
	 * 
	 * @Override public android.widget.Filter getFilter() { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * }
	 * 
	 * static class ViewHolder { TextView textview;
	 * 
	 * }
	 */

	// /////--------------------

	public class CustomerAdapter extends ArrayAdapter<DBROWPOJO> {
		private final String MY_DEBUG_TAG = "CustomerAdapter";
		private ArrayList<DBROWPOJO> items;
		private ArrayList<DBROWPOJO> itemsAll;
		private ArrayList<DBROWPOJO> suggestions;
		private int viewResourceId;

		public CustomerAdapter(Context context, int viewResourceId,
				ArrayList<DBROWPOJO> items) {
			super(context, viewResourceId, items);
			this.items = items;
			this.itemsAll = (ArrayList<DBROWPOJO>) items.clone();
			this.suggestions = new ArrayList<DBROWPOJO>();
			this.viewResourceId = viewResourceId;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(viewResourceId, null);
			}
			DBROWPOJO dbrowpojo = items.get(position);
			if (dbrowpojo != null) {
				TextView customerNameLabel = (TextView) v;
				if (customerNameLabel != null) {
					// Log.i(MY_DEBUG_TAG,
					// "getView Customer Name:"+customer.getName());
					customerNameLabel.setText(dbrowpojo.getSuburbDesc());
				}
			}
			return v;
		}

		@Override
		public android.widget.Filter getFilter() {
			return nameFilter;
		}

		android.widget.Filter nameFilter = new android.widget.Filter() {
			public String convertResultToString(Object resultValue) {
				String str = ((DBROWPOJO) (resultValue)).getSuburbDesc();
				return str;
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				if (constraint != null) {
					suggestions.clear();
					for (DBROWPOJO customer : itemsAll) {
						if (customer
								.getSuburbDesc()
								.toLowerCase()
								.startsWith(constraint.toString().toLowerCase())) {
							suggestions.add(customer);
						}
					}
					FilterResults filterResults = new FilterResults();
					filterResults.values = suggestions;
					filterResults.count = suggestions.size();
					return filterResults;
				} else {
					return new FilterResults();
				}
			}

			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				@SuppressWarnings("unchecked")
				ArrayList<DBROWPOJO> filteredList = (ArrayList<DBROWPOJO>) results.values;
				if (results != null && results.count > 0) {
					clear();
					for (DBROWPOJO c : filteredList) {
						add(c);
					}
					if (results != null && results.count > 0) {
						notifyDataSetChanged();
					}
				}
			}
		};

	}
}
