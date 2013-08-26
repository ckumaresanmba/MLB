package com.mylimobook.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.mylimobook.LimoUtil;
import com.mylimobook.api.WebAPIRequest;
import com.mylimobook.db.DataBaseHelper;
import com.mylimobook.pojos.SuburbsResponcePojo;

public class SuburbsBacgroundTask extends AsyncTask<String, Void, String>{

	Context context;
	public SuburbsBacgroundTask(Context context)
	{
		this.context=context;
	}
	
	ProgressDialog pd;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd=ProgressDialog.show(context, "", "Hi");
	}
	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String result=new WebAPIRequest().performGet(LimoUtil.URL_SUBURBS_GET);
		if(result.equalsIgnoreCase("Exception")==false)
		{
			Gson gson = new Gson();
			SuburbsResponcePojo suburbsResponcePojo=	gson.fromJson(result, SuburbsResponcePojo.class);
			
			
			System.out.println("1 "+suburbsResponcePojo.getStatus());
			System.out.println("1 "+suburbsResponcePojo.getData().getSuburbsPojosList());
			System.out.println("1 "+suburbsResponcePojo.getData().getSuburbsPojosList().get(0).getCountryCode());
			System.out.println("1 "+suburbsResponcePojo.getData().getSuburbsPojosList().get(0).getLat());
			System.out.println("1 "+suburbsResponcePojo.getData().getSuburbsPojosList().get(0).getLong());
			System.out.println("1 "+suburbsResponcePojo.getData().getSuburbsPojosList().get(0).getSuburbDesc());
			
			DataBaseHelper dbHelper= new DataBaseHelper(context);
			
			dbHelper.openDataBase();
			
			if(suburbsResponcePojo.getData().getSuburbsPojosList()!=null)
			{
				dbHelper.deleteAllRows();
				for (int i = 0; i < suburbsResponcePojo.getData().getSuburbsPojosList().size(); i++) {
					dbHelper.inserRow(suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getSuburbId(), 
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getPostalcode(), 
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getSuburbDesc(), 
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getState(),
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getLat(),
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getLong(),
							suburbsResponcePojo.getData().getSuburbsPojosList().get(i).getCountryCode());
					
				}
				dbHelper.close();
			}
			dbHelper.openDataBase();
			//Cursor cursor=dbHelper.getAlldataFromDb();
			
			//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA   "+cursor.getCount());
			
		}
		return 	null;
		
	}

	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		pd.dismiss();
		
	}
	
}
