package com.mylimobook.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylimobook.R;
import com.mylimobook.contacts.ContactFragment;
import com.mylimobook.contacts.ContactFragmentActivity;
import com.mylimobook.pojos.ExistingContactsPojo;
import com.mylimobook.util.ImageCache.ImageCacheParams;
import com.mylimobook.util.ImageFetcher;

public class ExistingContactsAdapter extends BaseAdapter {

	private static final String IMAGE_CACHE_DIR_THUMB = null;
	private ArrayList<ExistingContactsPojo> list;
	Activity context;
	LayoutInflater mLayInflarer;
	private com.mylimobook.util.ImageFetcher mImageFetcher;
	ContactFragment fragment;
	ViewGroup viewGroup;

	public ExistingContactsAdapter(ContactFragment fragment,
			ArrayList<ExistingContactsPojo> list, ViewGroup viewGroup) {
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
		mImageFetcher = new ImageFetcher(fragment.getActivity(), 500);
		// mImageFetcher.setLoadingImage(R.drawable.loading_image);
		mImageFetcher.addImageCache(fragment.getActivity()
				.getSupportFragmentManager(), cacheParams);
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
			convertView = mLayInflarer.inflate(R.layout.contact_row, null);

			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_address = (TextView) convertView
					.findViewById(R.id.tv_address);
			holder.button_add = (ImageButton) convertView
					.findViewById(R.id.Imagebutton_add);
			holder.imageView1 = (ImageView) convertView
					.findViewById(R.id.imageView1);

			holder.lin_for_search_contact= (LinearLayout) convertView
					.findViewById(R.id.lin_for_search_contact);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();

		}

		holder.tv_name.setText(list.get(position).getFirstName() + " "
				+ list.get(position).getLastName());
		holder.tv_address.setText(list.get(position).getLocDescription()
				+ " - " + list.get(position).getRegoNo());
		holder.button_add.setVisibility(View.GONE);
		holder.button_add.setClickable(false);
		
		if (list.get(position).isIsPending()) {
			//holder.button_add.setText("");//Request Sent
			holder.button_add.setBackground(context.getResources().getDrawable(R.drawable.request_is_already_send));
			holder.button_add.setVisibility(View.VISIBLE);
			holder.button_add.setClickable(false);
		}
		else
		{
			if (list.get(position).isIsBlocked()) {
				//holder.button_add.setText("");//Blocked
				holder.button_add.setBackground(context.getResources().getDrawable(R.drawable.user_is_block));
				holder.button_add.setVisibility(View.VISIBLE);
				holder.button_add.setClickable(false);
			}
			else
			{
				//holder.button_add.setText("Blocked");
				//holder.button_add.setBackground(null);
				holder.button_add.setVisibility(View.GONE);
				holder.button_add.setClickable(false);
			}
		}


		
		mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
		
		mImageFetcher.loadImage(list.get(position).getPhotoUrl()
				, holder.imageView1);
		// holder.button_add
		holder.button_add.setClickable(false);
		holder.button_add.setFocusable(false);
		holder.button_add.setFocusableInTouchMode(false);
		holder.lin_for_search_contact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragment.listItemClick(position);
			}
		});
		return convertView;
	}

	public static class ViewHolder {
		TextView tv_name, tv_address;

		ImageView imageView1;
		ImageButton button_add;
		LinearLayout lin_for_search_contact;

	}

}
