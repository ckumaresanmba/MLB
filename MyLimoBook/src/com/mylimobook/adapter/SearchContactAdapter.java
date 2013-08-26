package com.mylimobook.adapter;

import java.util.ArrayList;

import org.w3c.dom.Notation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mylimobook.R;
import com.mylimobook.SearchContact;
import com.mylimobook.contacts.ContactDetails;
import com.mylimobook.pojos.SearchResultPojo;
import com.mylimobook.util.ImageCache.ImageCacheParams;
import com.mylimobook.util.ImageFetcher;

public class SearchContactAdapter extends BaseAdapter {

	private static final String IMAGE_CACHE_DIR_THUMB = null;
	private ArrayList<SearchResultPojo> list;
	SearchContact context;
	LayoutInflater mLayInflarer;
	ViewGroup viewGroup;
	
	
	
	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
		System.out.println("SearchContactAdapter.notifyDataSetChanged()");
	}

	public static ImageFetcher mImageFetcher ;
	  ImageCacheParams cacheParams;
	public SearchContactAdapter(SearchContact context, ArrayList<SearchResultPojo> list) {
		this.list = list;
		this.context = context;
		mLayInflarer = LayoutInflater.from(context);
		   mImageFetcher = new ImageFetcher(context, 120);
		   cacheParams  = new ImageCacheParams(
				   context, IMAGE_CACHE_DIR_THUMB);
			 mImageFetcher.addImageCache(context.getSupportFragmentManager(),
					    cacheParams);
				  cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
				             // 25% of
				             // app memory

				  // The ImageFetcher takes care of loading images into our ImageView
				  // children asynchronously
			
		  
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public SearchResultPojo getItem(int arg0) {
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

			
			holder.lin_for_search_contact= (LinearLayout) convertView.findViewById(R.id.lin_for_search_contact);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_address = (TextView) convertView
					.findViewById(R.id.tv_address);
			holder.button_add = (ImageButton) convertView.findViewById(R.id.Imagebutton_add);
			holder.imageView1 = (ImageView) convertView.findViewById(R.id.imageView1);

			convertView.setTag(holder);
			
			
			
			
		} else {
			holder = (ViewHolder) convertView.getTag();

		}
	
		holder.tv_name.setText(list.get(position).getFirstName()+" "+list.get(position).getLastName());
		holder.tv_address.setText(list.get(position).getLocDescription()+" - "+list.get(position).getRegoNo());

//IsPending is true: Don't show any buttons(Show label - "Request Sent")
//IsPending is false. Show Add Button
		
		if(list.get(position).isIsPending())
		{
			holder.button_add.setBackground(context.getResources().getDrawable(R.drawable.request_is_already_send));
		}
		else
		{
			holder.button_add.setBackground(context.getResources().getDrawable(R.drawable.add_for_search_contact));
			holder.button_add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					context.addButtonclick(holder,position);
					
				}
			});
			
		}
		
		
		
	
			   mImageFetcher.setLoadingImage(R.drawable.com_facebook_profile_picture_blank_square);
			
			   mImageFetcher
				  .loadImage(list.get(position).getPhotoUrl(), holder.imageView1);
			 
		holder.lin_for_search_contact.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SearchResultPojo ds = (SearchResultPojo) list.get(position);
				Gson gson= new  Gson();
				String str = gson.toJson(ds);
				Intent intent=new Intent(context, ContactDetails.class);
				intent.putExtra("OBJECT", str);
				intent.putExtra("ISFROM", 3);
				intent.putExtra("POS", position);
				
				context.startActivity(intent);
			}
		});
		
		
		return convertView;
	}

	public static class ViewHolder {
		TextView tv_name,tv_address;
	
		LinearLayout lin_for_search_contact;
		ImageView imageView1;
		ImageButton button_add;
		
	}

}
