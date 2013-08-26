package com.mylimobook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.mylimobook.jobs.JobFragmentActivity;
import com.mylimobook.pojos.ContactResponce;

public class LimoUtil {

	public static final String tempState = "NSW";
	public static final double tempAmount = 155.50d;

	//public static final String URLBASE = "http://mylimobookcloudservice.cloudapp.net";
	//public static final String URLBASE_PHOTO = "https://mylimobookimagestorage.blob.core.windows.net/profilephotos/";
	 
		//public static final String URLBASE ="http://1ff2eacbfedb4f09b30a088431650ada.cloudapp.net";//"http://mylimobookapiservice.cloudapp.net";// "http://1ff2eacbfedb4f09b30a088431650ada.cloudapp.net/";
	
		public static final String URLBASE ="http://mylimobookapiservice.cloudapp.net";
		
		// public static final String URLBASE =
	// "http://76a677433a9448dc8270aff22c6566de.cloudapp.net";
	// public static final String URLBASE =
	// "http://76a677433a9448dc8270aff22c6566de.cloudapp.net/";
	public static ContactResponce contactResponce = new ContactResponce();
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static final String URL_REGISTER = URLBASE + "/api/Register";
	public static final String URL_LOGIN = URLBASE + "/api/User";
	public static final String URL_CHANGEPSW = URLBASE + "/api/Settings";

	public static final String URL_SETTINGS = URLBASE + "/api/Settings";
	public static final String URL_Contacts = URLBASE + "/api/Contacts";

	public static final String About_DocumentTypeId = "5";
	public static final String Terms_Con__DocumentTypeId = "10";
	public static final String Policy_DocumentTypeId = "15";
	// public static final String URL_SETTINGS = URLBASE+"/api/Profile";
	public static final String URL_PROFILE = URLBASE + "/api/Profile";
	public static final String URL_FEEDBACK = URLBASE + "/api/Feedback";

	public static final String URL_SUBURBS_GET = URLBASE + "/api/suburb";
	public static final String URL_JOB_GET_EVENT = URLBASE + "/api/job";
	public static final String URL_JOB_GET_INFo = URLBASE + "/api/JobInfo";

	public static final String URL_JOB_GET = URLBASE + "/api/job";

	public static final String mYJOB_TYPE_ID = "10";
	public static JobFragmentActivity JOB_FRAGMENT_ACTIVITY;
	public static JobDetails JOB_DETAILS;

	public static final String mYPAGE_SIZE = "10";
	public static final String mYPAGE_INDEX = "0";

	public static final String Network_JOB_TYPE_ID = "20";
	public static final String Network_PAGE_SIZE = "10";
	public static final String Network_PAGE_INDEX = "0";

	public static final int STATUSID_ACCEPT_CONTACT = 30;
	public static final int STATUSID_REJECT_CONTACT = 40;
	public static final int STATUSID_REMOVE_CONTACT = 60;
	public static final int STATUSID_BLOCK_CONTACT = 50;
	public static final int STATUSID_UNBLOCK_CONTACT = 70;
	
	public static final int JOB_STATUS_ID_FOR_OPEN = 100;

	public static final int JOB_STATUS_ID_FOR_OFFLOAD = 110;
	public static final int JOB_STATUS_ID_FOR_ACCEPTED = 120;
	public static final int JOB_STATUS_ID_FOR_CONFIRMED = 130;
	public static final int JOB_STATUS_ID_FOR_SWAPPED = 150;
	public static final int JOB_STATUS_ID_FOR_CANCELLED = 200;

	// http://mylimobookcloudservice.cloudapp.net/api/Profile

	public static void toast(String msg, Context context) {
	
		Toast toast= Toast.makeText(context, msg, Toast.LENGTH_LONG);
		
		toast.setGravity(Gravity.CENTER|Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.show();
	}

	public static boolean isNetworkAvailable(Context con) {
		ConnectivityManager connectivityManager = (ConnectivityManager) con
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null;
	}

	// public static final String URL_LOGIN = URLBASE+"/api/User";

	public static void hideKeyboard(EditText edittext, Context context) {
		// hide virtual keyboard
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
	}

	public static void showKeyboard(EditText edittext, Context context) {
		// hide virtual keyboard
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
	//	imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
	//	imm.show(edittext.getWindowToken(), 0);
		
		
		
		imm.toggleSoftInputFromWindow(edittext.getApplicationWindowToken(),     InputMethodManager.SHOW_FORCED, 0);
        edittext.requestFocus();
	}

	public static void alertDialog(String string, Context context) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(context);
		alert.setMessage(string);
		alert.setPositiveButton("OK", null);
		alert.show();

	}

	public static boolean verifyEmail(String email) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static String setNumberFormat(int number) {
		if (number == 0)
			return "0";
		// test for number between 3 and 21 as they follow a
		// slightly different rule and all end with th
		if (number > 3 && number < 21) {
			return number + "th";
		}
		// return the last digit of the number e.g. 102 is 2
		int lastdigit = number % 10;
		// append the correct ordinal val
		switch (lastdigit) {
		case 1:
			return number + "st";
		case 2:
			return number + "nd";
		case 3:
			return number + "rd";
		default:
			return number + "th";
		}
	}
	
	
	public static String commisionAmount(String amount)
	{
		double  totalAMount =Double.parseDouble(amount);
		
		double rate=.2d;
		 rate=(totalAMount*5)/100;
		 return "$ "+String.format("%.2f", rate);	
	}

	public static float scaleDpi = 1.0f;

	public static File saveBitmap(Bitmap bm) {
		File file = null;
		System.out.println("bm is " + bm);
		try {
			// Create Folder
			File folder = new File(Environment.getExternalStorageDirectory()
					.toString() + "/.ATEMP/");
			folder.mkdirs();

			String extStorageDirectory = folder.toString();

			long lo = System.currentTimeMillis();
			String mFileName = lo + ".png";

			file = new File(extStorageDirectory, mFileName);

			FileOutputStream stream = new FileOutputStream(
					file.getAbsolutePath());
			bm.compress(CompressFormat.PNG, 100, stream);

			stream.flush();
			stream.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("error on saving image");

		}

		// return Uri.parse("file://" + file.getAbsolutePath());

		return file;
	}

	public static String convertMobileFormat(String value) {

		if(value!=null)
		{
			System.out.println("LimoUtil.convertMobileFormat()");
			System.out.println("A "+value);
			if (value.length() >= 9) {
				String tmp = value.substring(0, 4) + " " + value.substring(4, 7)
						+ " " + value.substring(7);
			
				System.out.println("B "+tmp );
				return tmp;
			}
		}
		

		return value;
	}
}
