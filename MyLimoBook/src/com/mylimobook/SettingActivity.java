package com.mylimobook;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.pojos.ProfileResPOJO;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.util.ImageCache.ImageCacheParams;
import com.mylimobook.util.ImageFetcher;

public class SettingActivity extends FragmentActivity implements
		OnClickListener {

	private static final String IMAGE_CACHE_DIR_THUMB = null;

	TableRow tableRow_editprofile, tableRow_changePsw1,
			tableRow_paymentDetails, tableRow_privacy, tablerow_terms,
			tableview_about, tableview_feedback;

	TextView textView_name, textView_email;
	ImageView imageView_profile;
	ProgressBar progressBar1;
	Button button_changepsw;
	ImageFetcher mImageFetcher;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);

		if (LimoUtil.isNetworkAvailable(this) == false) {
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),
					this);
		}

		progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
		progressBar1.setVisibility(View.GONE);
		initView();

		button_changepsw = (Button) findViewById(R.id.button_changepsw);
		button_changepsw.setOnClickListener(this);
		PackageInfo pInfo;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			(((TextView)findViewById(R.id.tvVersion))).setText("Version : "+pInfo.versionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	private Bitmap bitmap;
	private ProfileResPOJO obj = new ProfileResPOJO();

	private void initView() {
		// TODO Auto-generated method stub
		tableRow_editprofile = (TableRow) findViewById(R.id.tableRow_editprofile);
		// tableRow_changePsw = (TableRow)
		// findViewById(R.id.tableRow_changePsw);
		tableRow_paymentDetails = (TableRow) findViewById(R.id.tableRow_paymentDetails);

		tableRow_privacy = (TableRow) findViewById(R.id.tableRow_privacy);
		tablerow_terms = (TableRow) findViewById(R.id.tablerow_terms);

		tableRow_privacy.setOnClickListener(this);
		tablerow_terms.setOnClickListener(this);
		// tableRow_changePsw.setOnClickListener(this);
		tableRow_paymentDetails.setOnClickListener(this);
		tableRow_editprofile.setOnClickListener(this);
		// tableRow_changePsw.setOnClickListener(this);
		tableRow_paymentDetails.setOnClickListener(this);

		textView_name = (TextView) findViewById(R.id.textView_name);
		textView_email = (TextView) findViewById(R.id.textView_email);
		imageView_profile = (ImageView) findViewById(R.id.imageView_profile);

		tableview_about = (TableRow) findViewById(R.id.tableview_about);

		tableview_about.setOnClickListener(this);

		tableview_feedback = (TableRow) findViewById(R.id.tableview_feedback);

		tableview_feedback.setOnClickListener(this);

		ImageCacheParams cacheParams = new ImageCacheParams(
				SettingActivity.this, IMAGE_CACHE_DIR_THUMB);

		cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
		// 25% of
		// app memory

		// The ImageFetcher takes care of loading images into our ImageView
		// children asynchronously
		
		mImageFetcher = new ImageFetcher(this, 150);
		mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
		mImageFetcher.addImageCache(this.getSupportFragmentManager(), cacheParams);

		Gson gson = new Gson();
		String json = new UserPefrencePOJO()
				.getProfileResPOJO(SettingActivity.this);
		
		if (json != "null") {
			obj = gson.fromJson(json, ProfileResPOJO.class);
			String str =obj.getData().getProfile().getPhotoUrl();
			if (str != null && str.length()
			>1) {
				mImageFetcher.loadImage(str, imageView_profile);
				
			}

		}
		new AsyncTask<String, Void, String>() {
			protected void onPreExecute() {

				progressBar1.setVisibility(View.VISIBLE);
			}

			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
		
				

				return null;
			}

			protected void onPostExecute(String result) {
				progressBar1.setVisibility(View.GONE);

				textView_name.setText(obj.getData().getProfile().getFirstName()
						+ " " + obj.getData().getProfile().getLastName());
				textView_email.setText(obj.getData().getProfile().getEmail()
						+ " ");
				if (bitmap != null) {
					imageView_profile.setImageBitmap(bitmap);
				}

			};
		}.execute("");

		ImageButton imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
		imageButton_back.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("requestCode"+requestCode);
		System.out.println("resultCode"+resultCode);
		System.out.println("data"+data);
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				
				new AsyncTask<String, Void, String>() {

					protected void onPreExecute() {
						progressBar1.setVisibility(View.VISIBLE);
					}

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						Gson gson = new Gson();
						String json = new UserPefrencePOJO()
								.getProfileResPOJO(SettingActivity.this);
						if (json != "null") {
							obj = gson.fromJson(json, ProfileResPOJO.class);
						/*	String str = obj.getData().getProfile().getPhoto();
							if (str != null) {
								byte[] bytearray = Base64.decode(str, 0);
								bitmap = BitmapFactory.decodeByteArray(
										bytearray, 0, bytearray.length);
							}*/

						}

						return null;
					}

					protected void onPostExecute(String result) {
						progressBar1.setVisibility(View.GONE);

						textView_name.setText(obj.getData().getProfile()
								.getFirstName()
								+ " "
								+ obj.getData().getProfile().getLastName());
						textView_email.setText(obj.getData().getProfile()
								.getEmail()
								+ " ");
						ImageCacheParams cacheParams = new ImageCacheParams(
								SettingActivity.this, IMAGE_CACHE_DIR_THUMB);

						cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
						// 25% of
						// app memory

						// The ImageFetcher takes care of loading images into our ImageView
						// children asynchronously
						mImageFetcher = new ImageFetcher(SettingActivity.this, 150);
						mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
						mImageFetcher.addImageCache(SettingActivity.this.getSupportFragmentManager(), cacheParams);

						
						
							String str =obj.getData().getProfile().getPhotoUrl();
							if (str != null && str.length()
							>1) {
								mImageFetcher.loadImage(str, imageView_profile);
								
							}


					};
				}.execute("");
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tableRow_editprofile:

			startActivityForResult(new Intent(this, EditProfileActivity.class),
					1);
			break;

		case R.id.tableRow_privacy:

			startActivity(new Intent(this, PrivacyPolicy.class));
			break;

		case R.id.tablerow_terms:

			startActivity(new Intent(this, TermsAndService.class));
			break;
		/*
		 * case R.id.tableRow_changePsw:
		 * 
		 * startActivity(new Intent(this, ChangePassword.class)); break;
		 */
		case R.id.tableRow_paymentDetails:

			startActivity(new Intent(this, PaymentDetails.class));
			break;
		case R.id.tableview_feedback:

			startActivity(new Intent(this, FeedbackActivity.class));

			break;

		case R.id.tableview_about:

			startActivity(new Intent(this, AboutActivity.class));

			break;
		case R.id.imageButton_back:

			onBackPressed();
			break;
		case R.id.button_changepsw:
			startActivity(new Intent(SettingActivity.this, ChangePassword.class));

			break;

		default:
			break;
		}
	}

}
