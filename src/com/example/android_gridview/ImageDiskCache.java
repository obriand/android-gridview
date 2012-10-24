package com.example.android_gridview;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
		// Verify if image is in disk cache using url key
		boolean contain = false;
		String fileFromUrl = String.valueOf(url.hashCode());
		File searchFile = new File(mCacheDir.getAbsolutePath(), fileFromUrl);
        if (searchFile.exists()) contain = true;
		//Log.d("ImageDiskCache:containsImage-", String.valueOf(contain) + ":" + fileFromUrl);
		return contain;
	}
	
	public void addImageToCache(String url, Bitmap bitmap) {
		// store image bitmap file on the disk using the url as a key
		String fileFromUrl = String.valueOf(url.hashCode());
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(mCacheDir.getAbsolutePath(), fileFromUrl));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.d("ImageDiskCache:addImageToCache", url);
	}

	public Bitmap getImageFromCache(String url) {
		// get the image bitmap file from the disk using the url as a key
		String fileFromUrl = String.valueOf(url.hashCode());
		Bitmap bm = null;
		String resp = "";
		File getFile = new File(mCacheDir.getAbsolutePath(), fileFromUrl);
        bm = BitmapFactory.decodeFile(getFile.getAbsolutePath());
        if (bm==null) resp = "no file";
        //Log.d("ImageDiskCache:getImageFromCache", resp + "[" + getFile.getAbsolutePath() + "]" + " " + url);
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
