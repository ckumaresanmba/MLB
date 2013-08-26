package com.mylimobook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.pojos.Locations;
import com.mylimobook.pojos.MakeTypes;
import com.mylimobook.pojos.ProfileResPOJO;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;
import com.mylimobook.task.CustomAsynTaskJSON_PUT;
import com.mylimobook.util.ImageCache.ImageCacheParams;
import com.mylimobook.util.ImageFetcher;

public class EditProfileActivity extends FragmentActivity implements OnClickListener,
		AsyncTaskCompleteListener {

	private static final String IMAGE_CACHE_DIR_THUMB = null;
	ImageView imageView_profile;
	LinearLayout linear_proPic;
	TextView textviewLocation, textview_vehicle_type;
	EditText editText_firstName, editText_lastname, edittext_psw,
			edittext_email, edittext_psw_rpt, edittext_mobileNo,
			edittext_regNo;

	Button button_back, button_save;

	File path;
	ArrayList<Locations> locationList;
	ArrayList<MakeTypes> makeTypeList;
	private Bitmap bitmap;

	private ProfileResPOJO obj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);


		if(LimoUtil.isNetworkAvailable(this)==false)
		{
			LimoUtil.toast(getString(R.string.CheckYourInternetConnection),this);
		}
		
		LimoUtil.scaleDpi = getResources().getDisplayMetrics().density;
		initView();
		locationList = new ArrayList<Locations>();
		makeTypeList = new ArrayList<MakeTypes>();
		
		
		ImageCacheParams cacheParams = new ImageCacheParams(
				EditProfileActivity.this, IMAGE_CACHE_DIR_THUMB);

		cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
		// 25% of
		// app memory

		// The ImageFetcher takes care of loading images into our ImageView
		// children asynchronously
		
		ImageFetcher mImageFetcher = new ImageFetcher(this, 150);
		mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
		mImageFetcher.addImageCache(this.getSupportFragmentManager(), cacheParams);
		Gson gson = new Gson();
		String json = new UserPefrencePOJO()
				.getProfileResPOJO(this);
		
		if (json != "null") {
			obj = gson.fromJson(json, ProfileResPOJO.class);
			String str =obj.getData().getProfile().getPhotoUrl();
			if (str != null && str.length()
			>1) {
				mImageFetcher.loadImage(str, imageView_profile);
				
			}

		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		imageView_profile = (ImageView) findViewById(R.id.imageView_profile);

		button_back = (Button) findViewById(R.id.button_back);
		button_back.setOnClickListener(this);
		button_save = (Button) findViewById(R.id.button_save);
		button_save.setOnClickListener(this);

		linear_proPic = (LinearLayout) findViewById(R.id.linear_proPic);
		linear_proPic.setOnClickListener(this);
		textviewLocation = (TextView) findViewById(R.id.textviewLocation);
		textviewLocation.setOnClickListener(this);
		textview_vehicle_type = (TextView) findViewById(R.id.textview_vehicle_type);
		textview_vehicle_type.setOnClickListener(this);
		editText_firstName = (EditText) findViewById(R.id.editText_firstName);
		editText_lastname = (EditText) findViewById(R.id.editText_lastname);

		edittext_psw = (EditText) findViewById(R.id.edittext_psw);

		edittext_email = (EditText) findViewById(R.id.edittext_email);

		edittext_psw_rpt = (EditText) findViewById(R.id.edittext_psw_rpt);

		edittext_mobileNo = (EditText) findViewById(R.id.edittext_mobileNo);
	/*	edittext_mobileNo.addTextChangedListener(new TextWatcher() {
             private boolean mFormatting; // this is a flag which prevents the  stack overflow.
             private int mAfter;

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 // nothing to do here.. 
             }

             //called before the text is changed...
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //nothing to do here...
                 mAfter  =   after; // flag to detect backspace..

             }

             @Override
             public void afterTextChanged(Editable s) {
                 // Make sure to ignore calls to afterTextChanged caused by the work done below
                 if (!mFormatting) {
                     mFormatting = true;
                    // using US formatting...
                     if(mAfter!=0) // in case back space ain't clicked...
                // Locale local_au = new Locale("en","AU");
             PhoneNumberUtils.formatNumber(s,PhoneNumberUtils.getFormatTypeForLocale( Locale.ENGLISH));                             
                      mFormatting = false;
                 }
             }
         });
		*/
		
		edittext_mobileNo.addTextChangedListener(new TextWatcher() {
			String str = "";
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

				if (count != 0) {
					if (start == 4) {
						str = s + "";
						edittext_mobileNo.setText(str.substring(0, 4) + " " + str.substring(4));
						edittext_mobileNo.setSelection(edittext_mobileNo.getText().length());

						System.out.println(edittext_mobileNo.getText());
					} else if (start == 8) {
						str = s + "";
						edittext_mobileNo.setText(str.substring(0, 8) + " " + str.substring(8));
						edittext_mobileNo.setSelection(edittext_mobileNo.getText().length());
						System.out.println(edittext_mobileNo.getText());

					}

					// PhoneNumberUtils.formatNumber(ed_mobile.getText(),
					// PhoneNumberUtils.FORMAT_NANP);

					if (s.length() ==4) {
						edittext_mobileNo.setText(s + " ");
						edittext_mobileNo.setSelection(edittext_mobileNo.getText().length());

						System.out.println(edittext_mobileNo.getText());

					} else if (s.length() == 8) {
						edittext_mobileNo.setText(s + " ");
						edittext_mobileNo.setSelection(edittext_mobileNo.getText().length());

					}

				}

			
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		edittext_regNo = (EditText) findViewById(R.id.edittext_regNo);

		new AsyncTask<String, Void, String>() {
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				String json = new UserPefrencePOJO()
						.getProfileResPOJO(EditProfileActivity.this);
				if (json != "null") {

					obj = gson.fromJson(json, ProfileResPOJO.class);
				/*	String str = obj.getData().getProfile().getPhoto();
					if (str != null) {
						byte[] bytearray = Base64.decode(str, 0);
						bitmap = BitmapFactory.decodeByteArray(bytearray, 0,
								bytearray.length);
					}*/

				}

				return null;
			}

			protected void onPostExecute(String result) {

				editText_firstName.setText(obj.getData().getProfile()
						.getFirstName());
				editText_lastname.setText(obj.getData().getProfile()
						.getLastName());
				
				
				edittext_mobileNo.setText(LimoUtil.convertMobileFormat(obj.getData().getProfile()
						.getMobileNo()));
				
				edittext_email.setText(obj.getData().getProfile().getEmail());
			

				String citycode = obj.getData().getProfile().getCityCode();

				locationList = obj.getData().getLocationsList();
				for (int i = 0; i < locationList.size(); i++) {
					if (locationList.get(i).getCityCode()
							.equalsIgnoreCase(citycode)) {
						textviewLocation.setTag(locationList.get(i));

						textviewLocation.setText(locationList.get(i)
								.getLocDescription());
					}
				}

				int makeId = obj.getData().getProfile().getMakeId();
				makeTypeList = obj.getData().getMakeTypesList();
				for (int i = 0; i < makeTypeList.size(); i++) {
					if (makeTypeList.get(i).getMakeId() == makeId) {
						textview_vehicle_type.setTag(makeTypeList.get(i));

						textview_vehicle_type.setText(makeTypeList.get(i)
								.getDescription());
					}
				}

				edittext_regNo.setText(obj.getData().getProfile().getRegoNo());
				if (bitmap != null) {
					imageView_profile.setImageBitmap(bitmap);

				}

			};
		}.execute("");
	}
	
	String msg ="";

	@Override
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub

		if (result.equalsIgnoreCase("Exception")) {
			LimoUtil.toast(getString(R.string.errorMessage),
					EditProfileActivity.this);
		} else {
			if (taskId == 1) {


				try {
					JSONObject json = new JSONObject(result);
					String status = json.getString("Status");
					if (status.equalsIgnoreCase("Success")) {
						 msg = json.getJSONArray("Message").optString(0);
					

						new CustomAsynTaskGET(EditProfileActivity.this, 2,
								"Loading...").execute(LimoUtil.URL_PROFILE
								+ "?UserKey="
								+ new UserPefrencePOJO()
										.getUserKey(EditProfileActivity.this));

					} else {
						 msg = json.getJSONArray("Message").optString(0)
								+ "";
						//alertDialog(false, msg);
						LimoUtil.toast(msg, EditProfileActivity.this);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} 
			else if (taskId == 2)// Loading data from server
			{

				
				LimoUtil.toast(msg, EditProfileActivity.this);
		
				new CustomAsynTaskGET(EditProfileActivity.this, 3, "Loading...")
				.execute(LimoUtil.URL_PROFILE
						+ "?UserKey="
						+ new UserPefrencePOJO()
								.getUserKey(EditProfileActivity.this));
				
				
			}
			else if(taskId==3)
			{
				if(result.equalsIgnoreCase("Exception")==false)
				{
					
					new UserPefrencePOJO().savePreference("ProfileResPOJO", result, EditProfileActivity.this);
					Gson gson= new Gson();
					
					ProfileResPOJO obj = gson.fromJson(result,ProfileResPOJO.class);
					//alertDialog(true, msg);
					
					setResult(Activity.RESULT_OK);
					EditProfileActivity.this.finish();
				
					
				
					Gson gson1= new Gson();
					//ProfileResPOJO profileResPOJO  = new ProfileResPOJO();
					////gson.toJson(profileResPOJO); // May not serialize foo.value correctly

					ProfileResPOJO obj1 = gson1.fromJson(result,ProfileResPOJO.class);
					if(obj1.getStatus().equalsIgnoreCase("Success"))
					{
						new UserPefrencePOJO().savePreference("ProfileResPOJO", result, EditProfileActivity.this);
					
						
					}
			}
		}
		}

	}

	private void alertDialog(boolean isSucess, String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(
				EditProfileActivity.this);
		alert.setMessage(string);
		if (isSucess) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							setResult(Activity.RESULT_OK);
							EditProfileActivity.this.finish();
							
						}
					});
		} else {
			alert.setPositiveButton("OK", null);
		}
		alert.show();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		super.onActivityResult(requestCode, resultCode, data);
		new ActivtyResultTask(requestCode, resultCode, data).execute("");

	}

	private class ActivtyResultTask extends AsyncTask<String, Void, String> {
		Bitmap imgBitmap = null;
		int reqCode, resCode;
		Intent data;

		ActivtyResultTask(int requestCode, int resultCode, Intent data) {
			this.reqCode = requestCode;
			this.resCode = resultCode;
			this.data = data;
		}

		ProgressDialog pd;

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(EditProfileActivity.this, "",
					"Processing...");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
		/*	Toast.makeText(EditProfileActivity.this, "Hi  " + path,
					Toast.LENGTH_LONG).show();*/

			if (imgBitmap != null)
				imageView_profile.setImageBitmap(imgBitmap);
			/*
			 * else Toast.makeText(Registration.this, "Error ",
			 * Toast.LENGTH_LONG) .show();
			 */
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			if (resCode == Activity.RESULT_OK) {
				switch (reqCode) {
				case 0:
					if (resCode == Activity.RESULT_OK) {

						if (data.getData() != null) {
							Bitmap temp = (Bitmap) data.getExtras().get("data");
							path = LimoUtil.saveBitmap(temp);
							imgBitmap = Bitmap.createScaledBitmap(temp,
									(int) (90 * LimoUtil.scaleDpi),
									(int) (90 * LimoUtil.scaleDpi), true);
							// imageView_profilePic.setImageBitmap(scaledphoto);

						} else {

							Bitmap temp = (Bitmap) data.getExtras().get("data");
							path = LimoUtil.saveBitmap(temp);

							imgBitmap = Bitmap.createScaledBitmap(temp,
									(int) (90 * LimoUtil.scaleDpi),
									(int) (90 * LimoUtil.scaleDpi), true);
							// imageView_profilePic.setImageBitmap(scaledphoto);

						}

					}

					break;
				case 1:
					System.out.println("rescode "
							+ (resCode == Activity.RESULT_OK));

					if (resCode == Activity.RESULT_OK) {

						try {
							Uri selectedImage = data.getData();
							String[] filePathColumn = { MediaStore.Images.Media.DATA };

							Cursor cursor = getContentResolver().query(
									selectedImage, filePathColumn, null, null,
									null);
							cursor.moveToFirst();

							int columnIndex = cursor
									.getColumnIndex(filePathColumn[0]);
							String filePath = cursor.getString(columnIndex);
							cursor.close();

							Bitmap yourSelectedImage = BitmapFactory
									.decodeFile(filePath);
							path = LimoUtil.saveBitmap(yourSelectedImage);
							imgBitmap = Bitmap.createScaledBitmap(
									yourSelectedImage,
									(int) (90 * LimoUtil.scaleDpi),
									(int) (90 * LimoUtil.scaleDpi), true);

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					break;

				}

			}

			return null;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.button_back:
			this.finish();

			break;
		case R.id.button_save:

			if (editText_firstName.getText().toString().trim().length() == 0) {
				editText_firstName.setError("Required");
				editText_firstName.requestFocus();
			} else if (editText_lastname.getText().toString().trim().length() == 0) {
				editText_lastname.setError("Required");
				editText_lastname.requestFocus();
			} else if (edittext_email.getText().toString().trim().length() == 0) {
				edittext_email.setError("Required");
				edittext_email.requestFocus();

			}
			 else if (edittext_mobileNo.getText().toString().trim().length() == 0) {
				 edittext_mobileNo.setError("Required");
				 edittext_mobileNo.requestFocus();

				}
			else if (edittext_mobileNo.getText().toString().trim().length() != 0 && edittext_mobileNo.getText().toString().trim().replaceAll(" ", "").length()<10 ) {
				String text=  edittext_mobileNo.getText().toString().trim().replaceAll(" ", "");
				if(text .length()<10)
					edittext_mobileNo.setError("Minimum 10 digit");
				edittext_mobileNo.requestFocus();

			}
			

			else if (textviewLocation.getText().toString().length() == 0) {

				System.out.println("1");
				alertDialog(false, "Location Required.");
			} else if (edittext_regNo.getText().toString().trim().length() == 0) {
			
				edittext_regNo.setError("Required");
				edittext_regNo.requestFocus();
			} else if (textview_vehicle_type.getText().toString().length() == 0) {

				alertDialog(false, "Vehicle Type Required.");
			} else {
				System.out.println("EditProfileActivity.onClick()");
				System.out.println("EditProfileActivity.onClick()");
				System.out.println("EditProfileActivity.onClick()");

				JSONObject json = new JSONObject();
				try {

					if (path == null) {

					} else {

						byte[] data = new byte[(int) path.length()];
						try {
							new FileInputStream(path).read(data);
						} catch (Exception e) {
							e.printStackTrace();
						}
						String encodedImage = Base64.encodeToString(data,
								Base64.DEFAULT);

						json.put("FileNameWithExtension", "profilepic.jpg");
						json.put("ProfilePhoto", encodedImage);

					}

					json.put("FirstName", ""
							+ editText_firstName.getText().toString());
					json.put("LastName", ""
							+ editText_lastname.getText().toString());

					json.put("MobileNo", ""
							+ edittext_mobileNo.getText().toString().replace(" ", ""));
					

					json.put("RegoNo", "" + edittext_regNo.getText().toString());

					json.put(
							"MakeId",
							((MakeTypes) textview_vehicle_type.getTag())
									.getMakeId() + "");

					json.put(
							"CityCode",
							((Locations) textviewLocation.getTag())
									.getCityCode() + "");

					json.put(
							"UserKey",
							new UserPefrencePOJO()
									.getUserKey(EditProfileActivity.this) + "");

					new CustomAsynTaskJSON_PUT(EditProfileActivity.this, 1,
							"Updating...", LimoUtil.URL_PROFILE, json)
							.execute("");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("" + e.getMessage());
				}
			}

			break;

		case R.id.linear_proPic:
			pickTheprofilePic();

			break;
		case R.id.textviewLocation:

			final CharSequence[] locationItems = new CharSequence[locationList
					.size()];
			for (int i = 0; i < locationList.size(); i++) {
				locationItems[i] = locationList.get(i).getLocDescription();
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Select Location");
			builder.setItems(locationItems,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							// Do something with the selection
							textviewLocation.setText(locationList.get(item)
									.getLocDescription());
							textviewLocation.setTag(locationList.get(item));

						}
					});
			AlertDialog alert = builder.create();
			alert.show();

			break;

		case R.id.textview_vehicle_type:

			final CharSequence[] makeTypeItems = new CharSequence[makeTypeList
					.size()];
			for (int i = 0; i < makeTypeList.size(); i++) {
				makeTypeItems[i] = makeTypeList.get(i).getDescription();
			}

			AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
			builder1.setTitle("Select Vehicle Type");
			builder1.setItems(makeTypeItems,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							// Do something with the selection
							textview_vehicle_type.setText(makeTypeList
									.get(item).getDescription());
							textview_vehicle_type.setTag(makeTypeList.get(item));

						}
					});
			AlertDialog alert1 = builder1.create();
			alert1.show();

			break;
		
		default:
			break;
		}
	}

	private void pickTheprofilePic() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		alertDialogBuilder.setMessage("Pick Picture from !!");

		alertDialogBuilder

				.setCancelable(true)
				.setPositiveButton("Camera",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								Intent intentPicture = new Intent(
										MediaStore.ACTION_IMAGE_CAPTURE);
								startActivityForResult(intentPicture, 0);
								dialog.cancel();
							}
						})
				.setNegativeButton("Gallery",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {

								Intent pickPhoto = new Intent(
										Intent.ACTION_PICK,
										android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								startActivityForResult(pickPhoto, 1);
								dialog.cancel();
							}
						});

		AlertDialog alertDialog = alertDialogBuilder.create();

		alertDialog.show();
	}

}
