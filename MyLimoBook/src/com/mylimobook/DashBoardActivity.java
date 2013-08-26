package com.mylimobook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.contacts.ContactFragmentActivity;
import com.mylimobook.jobs.JobFragmentActivity;
import com.mylimobook.pojos.ProfileResPOJO;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;

public class DashBoardActivity extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	ImageButton IB_settinngs;
	Button button_Job,button_signout;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash_board);

		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		initView();

		
		new CustomAsynTaskGET(DashBoardActivity.this, 1, "Loading...")
				.execute(LimoUtil.URL_PROFILE
						+ "?UserKey="
						+ new UserPefrencePOJO()
								.getUserKey(DashBoardActivity.this));
		
		
		
		
}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		JobFragmentActivity.myjobList=null;
		JobFragmentActivity.networkJobList=null;
		
	}

	private void initView() {
		// TODO Auto-generated method stub

		IB_settinngs = (ImageButton) findViewById(R.id.IB_settinngs);
		IB_settinngs.setOnClickListener(this);
		Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);
		button_Job = (Button) findViewById(R.id.button_Job);
		button_Job.setOnClickListener(this);

		button_signout = (Button) findViewById(R.id.button_signout);
		button_signout.setOnClickListener(this);

		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.IB_settinngs:

			startActivity(new Intent(this, SettingActivity.class));

			break;
		case R.id.button2:

			startActivity(new Intent(this, ContactFragmentActivity.class));

			break;
		case R.id.button_Job:

			startActivity(new Intent(this, JobFragmentActivity.class));

			break;
		case R.id.button_signout:

			AlertDialog.Builder alert = new AlertDialog.Builder(DashBoardActivity.this);
			alert.setTitle("Log Out");
			
			alert.setMessage("Click Confirm to log out.");
			alert.setPositiveButton("Cancel", null);
			alert.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					new UserPefrencePOJO().setUserKey("null", DashBoardActivity.this);
					DashBoardActivity.this.finish();
					startActivity(new Intent(DashBoardActivity.this, SignInActivity.class));
				}
			});
			alert.show();

			break;
			
		default:
			break;
		}
	}

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub

		switch (taskId) {
		case 1:
			
			if(result.equalsIgnoreCase("Exception")==false)
			{
				
				
			
				
			
				Gson gson= new Gson();
				//ProfileResPOJO profileResPOJO  = new ProfileResPOJO();
				////gson.toJson(profileResPOJO); // May not serialize foo.value correctly

				ProfileResPOJO obj = gson.fromJson(result,ProfileResPOJO.class);
				if(obj.getStatus().equalsIgnoreCase("Success"))
				{
					new UserPefrencePOJO().savePreference("ProfileResPOJO", result, DashBoardActivity.this);
					setValue();
					TextView TextView01_username=(TextView) findViewById(R.id.TextView01_username);
					String str=obj.getData().getProfile().getFirstName()+" "+obj.getData().getProfile().getLastName();
					TextView01_username.setText(str.toUpperCase());

					
					System.out.println("obj "+obj.toString());
					
					System.out.println("obj "+obj.getMessage());
					
					System.out.println("getData "+obj.getData());
					//System.out.println("getProfile "+obj.getData().getProfile());
					System.out.println("obj "+obj.getStatus());
					
					
					
				}
				else
				{
					// reconnect  from login reqire.
				}
			
				//System.out.println("obj "+obj.getData().getProfile().getLastName());
				
			}
			break;

		default:
			break;
		}
	}

	private void setValue() {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		ProfileResPOJO obj ;
		String json = new UserPefrencePOJO()
				.getProfileResPOJO(DashBoardActivity.this);
		obj = gson.fromJson(json, ProfileResPOJO.class);
		
		TextView TextView01_username=(TextView) findViewById(R.id.TextView01_username);
		TextView01_username.setText(obj.getData().getProfile().getFirstName()+" "+obj.getData().getProfile().getLastName());
	}

}
