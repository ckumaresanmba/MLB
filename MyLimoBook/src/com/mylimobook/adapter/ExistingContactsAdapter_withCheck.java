package com.mylimobook.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mylimobook.R;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.util.ImageFetcher;
import com.mylimobook.util.ImageCache.ImageCacheParams;

public class ExistingContactsAdapter_withCheck extends BaseAdapter {

	private static final String IMAGE_CACHE_DIR_THUMB = null;
	private ArrayList<ExistingContactsPojo> list;
	Activity context;
	LayoutInflater mLayInflarer;
	Fragment fragment;
	ViewGroup viewGroup;
	ImageFetcher mImageFetcher;
	public ExistingContactsAdapter_withCheck(Fragment fragment,
			ArrayList<ExistingContactsPojo> list, ViewGroup viewGroup) {
		this.list = list;
		this.context =  fragment.getActivity();
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
		mImageFetcher = new ImageFetcher(fragment.getActivity(), 500);
		// mImageFetcher.setLoadingImage(R.drawable.loading_image);
		mImageFetcher.addImageCache(fragment.getActivity()
				.getSupportFragmentManager(), cacheParams);

	}
	public ExistingContactsAdapter_withCheck(Activity activity,
			ArrayList<ExistingContactsPojo> list, ViewGroup viewGroup) {
		this.list = list;
		this.context =  activity;
		mLayInflarer = LayoutInflater.from(context);
		this.viewGroup = viewGroup;
		this.fragment = fragment;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public ExistingContactsPojo getItem(int arg0) {
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
			convertView = mLayInflarer.inflate(R.layout.contact_row_with_checkbox, null);

			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_address = (TextView) convertView
					.findViewById(R.id.tv_address);
			holder.CheckBox_add = (CheckBox) convertView
					.findViewById(R.id.CheckBox_add);

			convertView.setTag(holder);

		} else {
			
			holder = (ViewHolder) convertView.getTag();

		}

		
		mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
		
		mImageFetcher.loadImage(list.get(position).getPhotoUrl()
				, holder.imageView1);
		
		holder.tv_name.setText(list.get(position).getFirstName()+" "+list.get(position).getLastName());
		holder.tv_address.setText(list.get(position).getLocDescription() + " - "
				+ list.get(position).getRegoNo());
	
		holder.CheckBox_add.setChecked(list.get(position).isChecked());
		holder.CheckBox_add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				list.get(position).setChecked(holder.CheckBox_add.isChecked());
			}
		});
		
		
		return convertView;
	}

	public static class ViewHolder {
		TextView tv_name, tv_address;

		ImageView imageView1;
		
		CheckBox CheckBox_add;

	}

}
