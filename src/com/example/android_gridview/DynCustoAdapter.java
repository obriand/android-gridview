package com.example.android_gridview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class DynCustoAdapter extends BaseAdapter {
	
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<String> mImages = new ArrayList<String>();
	
	public DynCustoAdapter(Context c, ArrayList<String> l) {
		mContext = c;
		mInflater = LayoutInflater.from(mContext);
		mImages = l;
	}
	
	@Override
	public int getCount() {
		return mImages.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(mContext);
		
		if (convertView == null) {
			imageView = (ImageView) mInflater.inflate(R.layout.item_grid_image, parent, false);
		} else {
			imageView = (ImageView) convertView;
		}
		
		// temporary image
		imageView.setImageResource(android.R.drawable.ic_menu_gallery);

		new ImageFetcherWithoutCacheTask(imageView).execute(mImages.get(position));

		return imageView;
	}
}
