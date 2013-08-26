package com.mylimobook.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.mylimobook.api.WebAPIRequest;

public class CustomAsynTaskGET extends AsyncTask<String, Void, String> {

	ProgressDialog pd;
	AsyncTaskCompleteListener callback;
	Activity act;
	int taskId;
	String progressMsg="";
	

	public CustomAsynTaskGET(Activity activity ,int taskID,String progressMsg)
	{
		callback= (AsyncTaskCompleteListener)(activity);
		this.taskId=taskID;
		this.act=activity;
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
	
		return new WebAPIRequest().performGet(params[0]);
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		callback.onTaskComplete(result,taskId);
	}
}
