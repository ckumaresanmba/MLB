package com.mylimobook.task;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import com.mylimobook.api.WebAPIRequest;

public class CustomAsynTaskJSON_PUT_forFragment extends AsyncTask<String, Void, String> {

	ProgressDialog pd;
	AsyncTaskCompleteListenerPosition callback;
	Activity act;
	int taskId;
	
	String url;
	JSONObject jsonString;
int position;
	private String progressMsg="";
	public CustomAsynTaskJSON_PUT_forFragment(Activity activity ,
			Fragment fragment,
			int taskID
			,String progressMsg,
			String URL,
			JSONObject jsonString,
			int position )
	{
		callback= (AsyncTaskCompleteListenerPosition)(fragment);
		this.taskId=taskID;
		this.act=activity;
		
		this.url=URL;
		this.position=position;
		this.jsonString=jsonString;
		this.progressMsg=progressMsg;
		
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd=ProgressDialog.show(act, "", progressMsg);
		
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		return new WebAPIRequest().perform_PUT_twithjson(url, jsonString);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		callback.onTaskComplete(result,taskId,position);
	}
}
