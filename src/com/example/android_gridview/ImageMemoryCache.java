package com.example.android_gridview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageMemoryCache {
	
	private static ImageMemoryCache sInstance;
	
	private Context mContext;
	
	private HashMap<String, Bitmap> mCacheData;
	
	private ImageMemoryCache(Context c) {
		mContext = c;
        mCacheData = new HashMap<String, Bitmap>();
	}
	
	public static void initialize(Context c) {
		if (sInstance == null) {
			sInstance = new ImageMemoryCache(c);
		}
	}

	public static ImageMemoryCache getInstance() {
		if (sInstance == null) {
			throw new IllegalStateException(
					"ImageMemoryCache should be initialized before being accessed");
		}
		return sInstance;
	}
	
	public boolean containsImage(String url) {
		boolean contain = mCacheData.containsKey(url);
		//Log.d("ImageMemoryCache:containsImage", String.valueOf(contain));
		return contain;		
	}
	
	public void addImageToCache(String url, Bitmap bitmap) {
		mCacheData.put(url, bitmap);
		//Log.d("ImageMemoryCache:addImageToCache", url);
	}

	public Bitmap getImageFromCache(String url) {
		Bitmap bm = null;
		bm = (Bitmap) mCacheData.get(url);
		//Log.d("ImageMemoryCache:getImageFromCache", url);
        return bm;
	}
	
	public void clear() {
		mCacheData.clear();
	}
}
