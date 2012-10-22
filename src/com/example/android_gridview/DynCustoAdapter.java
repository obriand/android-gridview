package com.example.android_gridview;

import java.util.ArrayList;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class DynCustoAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<String> mImages = new ArrayList<String>();
//	private LruCache mMemoryCache;

	public DynCustoAdapter(Context c, ArrayList<String> l) {
		mContext = c;
		mInflater = LayoutInflater.from(mContext);
		mImages = l;
//        final int memClass = ((ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
//        final int cacheSize = 1024 * 1024 * memClass / 8;
//        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//            @Override
//            protected int sizeOf(String key, Bitmap bitmap) {
//                // The cache size will be measured in bytes rather than number of items.
//                return bitmap.getByteCount();
//            }
//        };
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
			imageView = (ImageView) mInflater.inflate(R.layout.item_grid_image,
					parent, false);
		} else {
			imageView = (ImageView) convertView;
		}

//		final String imageKey = String.valueOf(mImages.get(position));
//		final Bitmap bitmap = getBitmapFromMemCache(imageKey);
//		if (bitmap != null) {
//			imageView.setImageBitmap(bitmap);
//		} else {
//			// temporary image
//			imageView.setImageResource(android.R.drawable.ic_menu_gallery);
//			new ImageFetcherWithoutCacheTask(imageView).execute(mImages.get(position));
//		}

		// temporary image
		 imageView.setImageResource(android.R.drawable.ic_menu_gallery);
		 new ImageFetcherWithoutCacheTask(imageView).execute(mImages.get(position));

		new ImageFetcherWithoutCacheTask(imageView).execute(mImages
				.get(position));

		return imageView;
	}
	
//	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//	    if (getBitmapFromMemCache(key) == null) {
//	        mMemoryCache.put(key, bitmap);
//	    }
//	}
//
//	public Bitmap getBitmapFromMemCache(String key) {
//	    return mMemoryCache.get(key);
//	}
}
