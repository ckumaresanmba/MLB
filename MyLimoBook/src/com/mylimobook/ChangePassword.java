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

import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskJSONPOST;

public class ChangePassword extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	Button button_back, button_changpsw, button_cancel;
	EditText editTextChangePsw, editText_newpsw, editText_retype;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub

		button_back = (Button) findViewById(R.id.button_back);

		button_changpsw = (Button) findViewById(R.id.button_changpsw);

		button_cancel = (Button) findViewById(R.id.button_cancel);

		button_cancel.setOnClickListener(this);
		button_back.setOnClickListener(this);
		button_changpsw.setOnClickListener(this);

		editTextChangePsw = (EditText) findViewById(R.id.editTextChangePsw);
		editText_newpsw = (EditText) findViewById(R.id.editText_newpsw);
		editText_retype = (EditText) findViewById(R.id.editText_retype);

	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub

		
		if(result!="Exception")
		{
			System.out.println(result);
		//	LimoUtil.toast(result, ChangePassword.this);
			//{"Status":"Error","Data":null,"Message":["Your current password is wrong"]}

			
			try {
				JSONObject json= new JSONObject(result);
				String status=json.optString("Status");
				String msg="";
				msg=json.getJSONArray("Message").optString(0);
				if(status.equalsIgnoreCase("Success"))
				{
					this.finish();
					LimoUtil.toast(msg, ChangePassword.this);
				}
				else
				{
					LimoUtil.toast(msg, ChangePassword.this);
				}
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			alertWithMessage("Internet connection problem");
		}
	}

	private void alertWithMessage( String message) {
		AlertDialog.Builder alert = new AlertDialog.Builder(
				this);
		
		alert.setMessage(message);
		alert.setPositiveButton("Ok", null);
		alert.show();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_cancel:

			onBackPressed();
			break;
		case R.id.button_back:
			onBackPressed();
			break;
		case R.id.button_changpsw:

			if(editTextChangePsw.getText().toString().trim().length()==0)
			{
				editTextChangePsw.setError("Required");
				editTextChangePsw.requestFocus();
			}
			else 	if(editText_newpsw.getText().toString().length()<8)
			{
				editText_newpsw.setError("Minimum 8 Characters");
				editText_newpsw.requestFocus();
			}
			else 	if(editText_retype.getText().toString().length()==0)
			{
				editText_retype.setError("Required");
				editText_retype.requestFocus();
			}
			
			
			else if(!editText_newpsw.getText().toString().equals(editText_retype.getText().toString()))
			{
				editText_retype.setError("Password does not match.");
				editText_retype.requestFocus();
			}
			else
			{
				JSONObject json = new JSONObject();
				try {
					json.put("UserKey", new UserPefrencePOJO().getUserKey(ChangePassword.this));
					json.put("CurrentPassword", editTextChangePsw.getText().toString());
					json.put("NewPassword", editText_newpsw.getText().toString());
					json.put("ConfirmPassword", editText_retype.getText().toString());
					
					new CustomAsynTaskJSONPOST(ChangePassword.this, 1, "Loading...", LimoUtil.URL_CHANGEPSW, json).execute("");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			break;

		default:
			break;
		}
	}

}
