package com.mylimobook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.json.JSONArray;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.pojos.Locations;
import com.mylimobook.pojos.MakeTypes;
import com.mylimobook.task.AsyncTaskCompleteListener;
import com.mylimobook.task.CustomAsynTaskGET;

public class Registration extends Activity implements OnClickListener,
		AsyncTaskCompleteListener {

	Button button_back, button_join;
	ImageView imageView_profile;
	LinearLayout linear_proPic;
	TextView textviewLocation, textview_vehicle_type;
	EditText editText_firstName, editText_lastname, edittext_psw,
			edittext_email, edittext_psw_rpt, edittext_mobileNo,
			edittext_regNo;

	CheckBox checkBox_accept;

	File path;
	ArrayList<Locations> locationList;
	ArrayList<MakeTypes> makeTypeList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		LimoUtil.scaleDpi = getResources().getDisplayMetrics().density;
		initView();

		if (LimoUtil.isNetworkAvailable(this) == false) {
			LimoUtil.toast(
					getString(R.string.CheckYourInternetConnection), this);
		}

		locationList = new ArrayList<Locations>();
		makeTypeList = new ArrayList<MakeTypes>();

		new CustomAsynTaskGET(Registration.this, 1, "Loading...")
				.execute(LimoUtil.URL_REGISTER);
	}

	private void initView() {
		// TODO Auto-generated method stub
		imageView_profile = (ImageView) findViewById(R.id.imageView_profile);
		button_join = (Button) findViewById(R.id.button_join);
		button_back = (Button) findViewById(R.id.button_back);
		button_back.setOnClickListener(this);
		button_join.setOnClickListener(this);

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

		checkBox_accept = (CheckBox) findViewById(R.id.checkBox_accept);

		TextView textView_terms = (TextView) findViewById(R.id.textView_terms);
		textView_terms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				startActivity(new Intent(Registration.this,
						TermsAndService.class));
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_back:
			this.finish();

			break;
		case R.id.button_join:
			join();

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

	private void join() {
		// TODO Auto-generated method stub

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

		else if (LimoUtil.verifyEmail(edittext_email.getText().toString()) == false) {
			edittext_email.setError("Not Valid email address");
			edittext_email.requestFocus();

		} else if (edittext_psw.getText().toString().trim().length() == 0) {
			edittext_psw.setError("Required");
			edittext_psw.requestFocus();
		} else if (edittext_psw.getText().toString().trim().length() < 8) {
			edittext_psw.setError("Minimum 8 Characters");
			edittext_psw.requestFocus();
		} else if (edittext_psw_rpt.getText().toString().trim().length() == 0) {
			edittext_psw_rpt.setError("Required");
			edittext_psw_rpt.requestFocus();
		}

		else if (edittext_psw.getText().toString()
				.equals(edittext_psw_rpt.getText().toString()) == false) {
			edittext_psw_rpt.setError("Password does not match.");
			edittext_psw_rpt.requestFocus();
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

			alertDialog(false, "Location Required.");
		} else if (edittext_regNo.getText().toString().trim().length() == 0) {
			edittext_regNo.setError("Required");
			edittext_regNo.requestFocus();
		} /*else if (textview_vehicle_type.getText().toString().length() == 0) {

			alertDialog(false, "Vehicle Type Required.");
		} */else if (checkBox_accept.isChecked() == false) {

			alertDialog(false,
					"Please Accept Terms & Conditions to join myLimoBook");
		} else {
			new AsyncTask<String, Void, String>() {

				ProgressDialog pd;

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					pd = ProgressDialog.show(Registration.this, "",
							"Joining...");

				}

				@Override
				protected String doInBackground(String... params) {
					// TODO Auto-generated method stub

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
						json.put("Email", ""
								+ edittext_email.getText().toString());
						json.put("MobileNo", ""
								+ edittext_mobileNo.getText().toString().replaceAll(" ", ""));
						json.put("Password", ""
								+ edittext_psw.getText().toString());
						json.put("ConfirmPassword", ""
								+ edittext_psw_rpt.getText().toString());
						json.put("RegoNo", ""
								+ edittext_regNo.getText().toString());
						
						if( textview_vehicle_type.getText().toString()!=null &&  textview_vehicle_type.getText().toString().length()>0)
						{
							json.put(
									"MakeId",
									((MakeTypes) textview_vehicle_type.getTag())
											.getMakeId() + "");
						}
					

						json.put(
								"CityCode",
								((Locations) textviewLocation.getTag())
										.getCityCode() + "");

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					return new WebAPIRequest().performPostwithjson(
							LimoUtil.URL_REGISTER, json);
				}

				protected void onPostExecute(String result) {
					try {
						// :{"Status":"Success","Data":null,"Message":["You have been successfully registered"]}
						JSONObject jsonobject = new JSONObject(result);
						pd.dismiss();
						String status = jsonobject.getString("Status");
						if (status.equalsIgnoreCase("Success")) {
							String message = jsonobject.getJSONArray("Message")
									.getString(0);
							alertDialog(true, message);
						} else {
							String message;
							message = jsonobject.getJSONArray("Message")
									.getString(0);

							alertDialog(false, message);
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				};
			}.execute("");

		}

	}

	private void alertDialog(boolean isSucess, String string) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(Registration.this);
		alert.setMessage(string);
		if (isSucess) {
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Registration.this.finish();
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
			pd = ProgressDialog.show(Registration.this, "", "Processing...");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
		/*	Toast.makeText(Registration.this, "Hi  " + path, Toast.LENGTH_LONG)
					.show();*/

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
	public void onTaskComplete(String result, int taskId) {
		// TODO Auto-generated method stub
		if (result.equalsIgnoreCase("Exception") == false) {
			switch (taskId) {
			case 1:

				jsonParse(result);

				break;

			default:
				break;
			}

		}
	}

	private void jsonParse(String result) {
		// TODO Auto-generated method stub

		try {
			JSONObject json = new JSONObject(result);

			String status = json.getString("Status");
			if (status.equalsIgnoreCase("Success")) {
				JSONObject data = json.getJSONObject("Data");

				JSONArray location = data.getJSONArray("Locations");

				for (int i = 0; i < location.length(); i++) {
					locationList.add(new Locations(location.getJSONObject(i)
							.optString("CityCode"), location.getJSONObject(i)
							.optString("LocDescription"), location
							.getJSONObject(i).optString("LocationCode")));

				}

				JSONArray makeType = data.getJSONArray("MakeTypes");

				for (int i = 0; i < makeType.length(); i++) {
					makeTypeList.add(new MakeTypes(makeType.getJSONObject(i)
							.optInt("MakeId"), makeType.getJSONObject(i)
							.optString("Description"), makeType
							.getJSONObject(i).optInt("NoOfSeats")));

				}

			} else {
				alertDialog(false, "Try again later.");
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	String res = "";

}
