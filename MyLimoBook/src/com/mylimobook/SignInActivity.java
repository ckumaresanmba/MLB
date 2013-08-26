package com.mylimobook;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mylimobook.db.TestAdapter;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskJSONPOST;

public class SignInActivity extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	Button button_signIn, button2;

	TextView textView_forgotPsw;

	EditText editText_email, editText_psw;
	CheckBox checkBox_keep_melogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(new UserPefrencePOJO().getUserKey(SignInActivity.this).equalsIgnoreCase("null")==false && new UserPefrencePOJO().isKeepMeloginTrue(SignInActivity.this))
		{
			this.finish();
			startActivity(new Intent(SignInActivity.this, DashBoardActivity.class));
		}
		else
		{
			setContentView(R.layout.activity_sign_in);
		TestAdapter mDbHelper = new TestAdapter(this);
		mDbHelper.createDatabase();
		mDbHelper.open();
		mDbHelper.close();
		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_psw = (EditText) findViewById(R.id.editText_psw);

		button_signIn = (Button) findViewById(R.id.button_signIn);
		button_signIn.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		textView_forgotPsw = (TextView) findViewById(R.id.textView_forgotPsw);
		textView_forgotPsw.setOnClickListener(this);
		checkBox_keep_melogin=(CheckBox) findViewById(R.id.checkBox_keep_melogin);
			
		}
		
		
		//new SuburbsBacgroundTask(SignInActivity.this).execute("");
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.button_signIn:
			signIn();
			// startActivity(new Intent(SignInActivity.this,
			// DashBoardActivity.class));
			break;
		case R.id.button2:
			startActivity(new Intent(SignInActivity.this, Registration.class));
			break;
		case R.id.textView_forgotPsw:
			startActivity(new Intent(SignInActivity.this,
					ForgottenPassword.class));
			break;

		default:
			break;
		}
	}

	private void signIn() {
		// TODO Auto-generated method stub

		if (editText_email.getText().toString().trim().length() == 0) {
			editText_email.setError("Required");
			editText_email.requestFocus();
		} else if (LimoUtil.verifyEmail(editText_email.getText().toString()) == false) {
			editText_email.setError("Not Valid email address");
			editText_email.requestFocus();
		}

		else if (editText_psw.getText().length() < 8) {
			editText_psw.setError("Password is too short");
			editText_psw.requestFocus();
		}

		else {

			JSONObject json = new JSONObject();
			try {
				json.put("Email", editText_email.getText().toString());

				json.put("Password", editText_psw.getText().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("json  " + json);
			System.out.println("imoUtil.URL_LOGIN  " + LimoUtil.URL_LOGIN);

			new CustomAsynTaskJSONPOST(SignInActivity.this, 1, "Sign In...",
					LimoUtil.URL_LOGIN, json).execute("");
		}

	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		if(result.equalsIgnoreCase("Exception")==false)
		{
			System.out.println(result);
			//Toast.makeText(SignInActivity.this, taskId + "    " + result, 1).show();

			// {"Status":"Error","Data":null,"Message":["User doesn't exist"]}

			try {
				JSONObject json = new JSONObject(result);

				String status = json.getString("Status");
			

				// {"Status":"Success","Data":{"UserKey":"8489396f-4842-4c15-a6b2-3ee31e4e6ff5"},"Message":null}

				if (status.equalsIgnoreCase("Success")) {

					String userkey = json.optJSONObject("Data").getString("UserKey")
							+ "";

					System.out.println("userkey  "+userkey);
					if (userkey != null && userkey.equalsIgnoreCase("null")==false) {
						
						
						
						new UserPefrencePOJO().setKeepMeLogin(checkBox_keep_melogin.isChecked(),SignInActivity.this);
						
						new UserPefrencePOJO().setUserKey(userkey, SignInActivity.this);
						startActivity(new Intent(SignInActivity.this,
								DashBoardActivity.class));
						this.finish();

					}

				} else {
					String Message = json.optJSONArray("Message").optString(0) + "";

					AlertDialog.Builder alert = new AlertDialog.Builder(SignInActivity.this);
					
					alert.setTitle("ERROR ");
					alert.setMessage(Message);
					alert.setPositiveButton("OK", null);
					alert.show();

					
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
