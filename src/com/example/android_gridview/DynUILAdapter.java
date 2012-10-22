package com.example.android_gridview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class DynUILAdapter extends BaseAdapter {
	
	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<String> mImages = new ArrayList<String>();
	private DisplayImageOptions mOptions;
	protected ImageLoader mImageLoader = ImageLoader.getInstance();
	
	public DynUILAdapter(Context c, DisplayImageOptions o, ArrayList<String> l) {
		mContext = c;
		mOptions = o;
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
		final ImageView imageView;
		if (convertView == null) {
			imageView = (ImageView) mInflater.inflate(R.layout.item_grid_image, parent, false);
		} else {
			imageView = (ImageView) convertView;
		}

		mImageLoader.displayImage(mImages.get(position), imageView, mOptions, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingComplete(Bitmap loadedImage) {
				Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.fade_in);
				imageView.setAnimation(anim);
				anim.start();
			}
		});

		return imageView;
	}
}
