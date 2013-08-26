package com.mylimobook.pojos;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPefrencePOJO {

//	private String UserKey = "";
	
	private final String USERKEY="UserKey";

	

	public String getProfileResPOJO(Context context) {
		return getPreference("ProfileResPOJO", context);
	}

	public void setProfileResPOJO(String profileResPOJO,Context context) {
		
		savePreference("ProfileResPOJO", profileResPOJO, context);
		
	}

	public String getUserKey(Context context) {
		return getPreference(USERKEY, context);
	}

	public void setUserKey(String userKeyvalue, Context context) {
		
		savePreference(USERKEY, userKeyvalue, context);
		
	}
	
	public void setKeepMeLogin(boolean value,Context context)
	{
		savePreferenceBoolean("KEEPMELOGIN", value, context);
	}
	
	public boolean isKeepMeloginTrue(Context context)
	{
		return getPreferenceBoolean("KEEPMELOGIN", context);
	}

	public void savePreference(String name, String value,Context context) {
		SharedPreferences app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putString(name, value);
		editor.commit();
		System.out.println("savePreference() name "+name +"& value :"+value);

		
		
	}
	
	public String getPreference(String name, Context context) {

		SharedPreferences app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		String value = "";
		value = app_preferences.getString(name, "null");

		 System.out.println("get Pref name :"+name +"Value is "+value);
		return value;
		
		
	}
	private void savePreferenceBoolean(String name, boolean value,Context context) {
		SharedPreferences app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = app_preferences.edit();
		editor.putBoolean(name, value);
		editor.commit();
		System.out.println("savePreference() name "+name +"& value :"+value);

		
		
	}

	public boolean getPreferenceBoolean(String name, Context context) {

		SharedPreferences app_preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean value = false;
		value = app_preferences.getBoolean(name, false);

		 System.out.println("get Pref name :"+name +"Value is "+value);
		return value;
		
		
	}
}
