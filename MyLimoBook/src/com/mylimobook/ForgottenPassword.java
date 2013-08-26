package com.mylimobook;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskJSON_PUT;

public class ForgottenPassword extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	Button button_back, button_send;
	EditText editTextChangePsw;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgor_password);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub

		button_back = (Button) findViewById(R.id.button_back);
		button_send = (Button) findViewById(R.id.button_send);

		button_back.setOnClickListener(this);
		button_send.setOnClickListener(this);

		editTextChangePsw = (EditText) findViewById(R.id.editTextChangePsw);

	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		/*Toast.makeText(ForgottenPassword.this, taskId + "    " + result, 1)
				.show();
*/
		if (taskId == 1 && result.equalsIgnoreCase("Exception")==false) {

			// {"Status":"Error","Data":null,"Message":["The SMTP server requires a secure connection or the client was not authenticated. The server response was: 5.5.1 Authentication Required. Learn more at"]}

			try {
				JSONObject json = new JSONObject(result);
				String status = json.getString("Status");
				String Message = json.getJSONArray("Message").getString(0);

				if (status != null && Message != null) {
					alertWithtitleandMessage(status, Message);
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void alertWithtitleandMessage(String title, String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(
				ForgottenPassword.this);
		alert.setTitle(title);
		alert.setMessage(message);
		alert.setPositiveButton("OK", null);
		alert.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.button_back:
			this.finish();

			break;

		case R.id.button_send:

			if (LimoUtil.verifyEmail(editTextChangePsw.getText().toString()) == false) {
				editTextChangePsw.setError("Not Valid email address");
				editTextChangePsw.requestFocus();
			} else

			{

				JSONObject json = new JSONObject();
				try {
					json.put("Email", editTextChangePsw.getText().toString());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new CustomAsynTaskJSON_PUT(ForgottenPassword.this, 1,
						"Loading...", LimoUtil.URL_LOGIN, json).execute("");

			}
			break;

		default:
			break;
		}

	}

}
