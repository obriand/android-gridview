package com.example.android_gridview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class ImageDiskCache {
	
	private static ImageDiskCache sInstance;
	
	private Context mContext;
	
	private File mCacheDir;
	
	private ImageDiskCache(Context c) {
		mContext = c;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
        	mCacheDir=new File(android.os.Environment.getExternalStorageDirectory(),mContext.getPackageName());
        else
        	mCacheDir=mContext.getCacheDir(); 
        if(!mCacheDir.exists())
        	mCacheDir.mkdirs();
	}
	
	public static void initialize(Context c) {
		if (sInstance == null) {
			sInstance = new ImageDiskCache(c);
		}
	}

	public static ImageDiskCache getInstance() {
		if (sInstance == null) {
			throw new IllegalStateException(
					"ImageCache should be initialized before being accessed");
		}
		return sInstance;
	}
	
	public boolean containsImage(String url) {
		// TODO Verify if image is in disk cache using url key
		boolean contain = false;
		Log.d("containsImage", String.valueOf(contain));
		return contain;
	}
	
	public void addImageToCache(String url, Bitmap bitmap) {
		// TODO store image bitmap file on the disk using the url as a key
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(mCacheDir.getAbsolutePath(), String.valueOf(url.hashCode())));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("addImageToCache", url);
	}

	public Bitmap getImageFromDiskCache(String url) {
		// TODO get the image bitmap file from the disk using the url as a key
		Bitmap bm = null;
        String filename=String.valueOf(url.hashCode());
        File f = new File(mCacheDir, filename);
        bm = BitmapFactory.decodeFile(f.getAbsolutePath());
		Log.d("getImageFromDiskCache", url);
        return bm;
	}
	
	public void clear() {
        File[] files=mCacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
	}
}
