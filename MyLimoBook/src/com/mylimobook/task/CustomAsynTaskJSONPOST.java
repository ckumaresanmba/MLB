package com.mylimobook.task;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.mylimobook.api.WebAPIRequest;

public class CustomAsynTaskJSONPOST extends AsyncTask<String, Void, String> {

	ProgressDialog pd;
	AsyncTaskCompleteListener callback;
	Activity act;
	int taskId;
	
	String url;
	JSONObject jsonString;

	private String progressMsg="";
	public CustomAsynTaskJSONPOST(Activity activity ,int taskID,String progressMsg,String URL,JSONObject jsonString )
	{
		callback= (AsyncTaskCompleteListener)(activity);
		this.taskId=taskID;
		this.act=activity;
		
		this.url=URL;
		
		this.jsonString=jsonString;
		this.progressMsg=progressMsg;
		
		
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		System.out.println("CustomAsynTaskJSONPOST.onPreExecute()");
		pd=ProgressDialog.show(act, "", progressMsg);
		
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		return new WebAPIRequest().performPostwithjson(url, jsonString);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		callback.onTaskComplete(result,taskId);
	}
}
