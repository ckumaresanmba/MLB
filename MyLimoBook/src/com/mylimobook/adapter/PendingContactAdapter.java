package com.mylimobook.adapter;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.LimoUtil;
import com.mylimobook.R;
import com.mylimobook.contacts.ContactDetails;
import com.mylimobook.contacts.ContactFragmentActivity;
import com.mylimobook.contacts.PendingContactFragment;
import com.mylimobook.pojos.PendingContacts;
import com.mylimobook.pojos.UserPefrencePOJO;
import com.mylimobook.task.CustomAsynTaskJSON_PUT_forFragment;
import com.mylimobook.util.ImageFetcher;
import com.mylimobook.util.ImageCache.ImageCacheParams;

public class PendingContactAdapter extends BaseAdapter {
	private static final String IMAGE_CACHE_DIR_THUMB = null;
	private ArrayList<PendingContacts> list;
	
	Activity context;
	LayoutInflater mLayInflarer;
	PendingContactFragment fragment;
	ViewGroup viewGroup;
	private com.mylimobook.util.ImageFetcher mImageFetcher;
	public PendingContactAdapter(PendingContactFragment fragment,
			ArrayList<PendingContacts> list, ViewGroup viewGroup) {
		this.list = list;
		this.context = (ContactFragmentActivity) fragment.getActivity();
		mLayInflarer = LayoutInflater.from(context);
		this.viewGroup = viewGroup;
		this.fragment = fragment;
		

		ImageCacheParams cacheParams = new ImageCacheParams(
				fragment.getActivity(), IMAGE_CACHE_DIR_THUMB);

		cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
													// 25% of
													// app memory

		// The ImageFetcher takes care of loading images into our ImageView
		// children asynchronously
		mImageFetcher = new ImageFetcher(fragment.getActivity()
				, 500);
		// mImageFetcher.setLoadingImage(R.drawable.loading_image);
		mImageFetcher.addImageCache(
				fragment.getActivity()
				.getSupportFragmentManager(),
				cacheParams);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public PendingContacts getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mLayInflarer.inflate(R.layout.contact_pendingreq_row,
					null);
			holder.lin_reqFordetail = (LinearLayout) convertView
					.findViewById(R.id.lin_reqFordetail);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_address = (TextView) convertView
					.findViewById(R.id.tv_address);
			holder.button_add = (Button) convertView
					.findViewById(R.id.button_add);
			holder.button_reject = (Button) convertView
					.findViewById(R.id.button_reject);
			
			holder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);
			

			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}
		holder.lin_reqFordetail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				PendingContacts ds = (PendingContacts) list.get(position);
				Intent intent = new Intent(context, ContactDetails.class);
				/*Gson gson = new Gson();
				String str = gson.toJson(ds);
				Intent intent = new Intent(context, ContactDetails.class);
				intent.putExtra("OBJECT", str);*/
				intent.putExtra("ISFROM", 2);
				intent.putExtra("POS", position);
				context.startActivityForResult(intent, 1);
			}
		});
		holder.tv_name.setText(list.get(position).getFirstName()+" "+list.get(position).getLastName());

		holder.tv_address.setText(list.get(position).getLocDescription() + " - "
				+ list.get(position).getRegoNo());
		



		holder.button_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Accept
				JSONObject json = new JSONObject();
				try {

					//[8:01:00 AM] Ramasamy NP: RequestedToUserKey is currently login user 22 Aug
					json.put("RequestByUserKey",list.get(position)
							.getUserKey());
					json.put("RequestedToUserKey",
							new UserPefrencePOJO().getUserKey(context));
					json.put("StatusId", LimoUtil.STATUSID_ACCEPT_CONTACT);
				
				
				
					new CustomAsynTaskJSON_PUT_forFragment(context, fragment,
							2, "Accepting...", LimoUtil.URL_Contacts, json,
							position).execute("");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		holder.button_reject.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Reject
				
				
				JSONObject json = new JSONObject();
				try {
					//[8:01:00 AM] Ramasamy NP: RequestedToUserKey is currently login user 22 Aug
					
					json.put("RequestByUserKey",list.get(position)
							.getUserKey());
					
					json.put("RequestedToUserKey",
							new UserPefrencePOJO().getUserKey(context));
					json.put("StatusId", LimoUtil.STATUSID_REJECT_CONTACT);
				
				

					new CustomAsynTaskJSON_PUT_forFragment(context, fragment,
							3, "Rejecting...", LimoUtil.URL_Contacts, json,
							position).execute("");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
		
		mImageFetcher.loadImage(list.get(position).getPhotoUrl()
				, holder.imageView1);
		

		return convertView;
	}

	public static class ViewHolder {
		TextView tv_name, tv_address;
		LinearLayout lin_reqFordetail;
		ImageView imageView1;
		Button button_add, button_reject;

	}

}
