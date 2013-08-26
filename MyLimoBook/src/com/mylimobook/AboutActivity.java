package com.mylimobook;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mylimobook.api.WebAPIRequest;

public class AboutActivity extends Activity {

	
	String DocumentContent=" ";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		final ProgressBar  progressBar=(ProgressBar) findViewById(R.id.progressBar1);

		final TextView textView_about=(TextView) findViewById(R.id.textView_about);
		textView_about.setText("");
		((Button) findViewById(R.id.button_back)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		new AsyncTask<String, Integer, String>()
		{

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				
				return new WebAPIRequest().performGet(LimoUtil.URL_SETTINGS+"?DocumentTypeId="+LimoUtil.About_DocumentTypeId);
			}
			protected void onPostExecute(String result) {
				progressBar.setVisibility(View.GONE);
				if(result.equalsIgnoreCase("Exception"))
				{
					textView_about.setText(R.string.errorMessage);
				}
				else
				{
					JSONObject json;
					try {
						json = new JSONObject(result);
						String str=json.getString("Status");
						DocumentContent=json.getJSONObject("Data").optString("DocumentContent");
						textView_about.setText(DocumentContent);
						
						
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					

							
				}
				
			}
		}.execute("");
		
	}
}