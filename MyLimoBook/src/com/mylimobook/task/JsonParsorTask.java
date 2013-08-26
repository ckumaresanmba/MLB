package com.mylimobook.task;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.mylimobook.api.WebAPIRequest;

public class JsonParsorTask extends AsyncTask<String, Void, String> {

	ProgressDialog pd;
	AsyncTaskCompleteListener callback;
	Activity act;
	int taskId;
	public JsonParsorTask(Activity activity ,int taskID)
	{
		callback= (AsyncTaskCompleteListener)(activity);
		this.taskId=taskID;
		this.act=activity;
	}
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd=ProgressDialog.show(act, "", "Loading...");
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
