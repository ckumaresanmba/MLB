package com.mylimobook;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskJSONPOST;

public class FeedbackActivity extends Activity implements
		AsyncTaskCompleteListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		final EditText editText_feedback = (EditText) findViewById(R.id.editText_feedback);

		((Button) findViewById(R.id.button_back))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						onBackPressed();
					}
				});
		
		Button button_send = (Button) findViewById(R.id.button_send);
		button_send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				if (editText_feedback.getText().toString().trim().length() == 0) {
					editText_feedback.setError(getString(R.string.Required));
					editText_feedback.requestFocus();
				} else {

					JSONObject json = new JSONObject();
					try {

						json.put("UserKey", new UserPefrencePOJO()
								.getUserKey(FeedbackActivity.this));
						json.put("Description", editText_feedback.getText()
								.toString());

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					new CustomAsynTaskJSONPOST(FeedbackActivity.this, 1,
							"Sending...", LimoUtil.URL_FEEDBACK, json).execute("");

					editText_feedback.clearFocus();
					LimoUtil.hideKeyboard(editText_feedback,
							FeedbackActivity.this);
				}
			}
		});
	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub

		if (result.equalsIgnoreCase("Exception")) {
			LimoUtil.toast(getString(R.string.errorMessage),
					FeedbackActivity.this);
		} else {
		//	LimoUtil.toast(result, FeedbackActivity.this);

			/*
			 * Status = "Success" | Data = NULL | Message => Array (1) | ( | |
			 * ['0'] = "Your feedback has send successfully" | )
			 */

			try {
				JSONObject json = new JSONObject(result);
				String status = json.getString("Status");
				if (status.equalsIgnoreCase("Success")) {
					String msg = json.getJSONArray("Message").optString(0);
					alertDialog(true, msg);

				} else {
					String msg = json.getJSONArray("Message").optString(0) + "";
					alertDialog(false, msg);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void alertDialog(boolean isSucess, String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(
				FeedbackActivity.this);
		alert.setMessage(string);
		if (isSucess) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							FeedbackActivity.this.finish();
						}
					});
		} else {
			alert.setPositiveButton("OK", null);
		}
		alert.show();

	}

}
