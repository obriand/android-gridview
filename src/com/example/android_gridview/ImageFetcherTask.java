package com.example.android_gridview;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class ImageFetcherTask extends AsyncTask<String, Void, HashMap<Integer, Object>> {

    private final WeakReference<ImageView> mImageViewReference;
    
    private int mImageSampleSize;
    
    private ImageMemoryCache mImageMemoryCache;
    
    private ImageDiskCache mImageDiskCache;

    public ImageFetcherTask(ImageView imageView, int is) {
    	mImageViewReference = new WeakReference<ImageView>(imageView);
    	mImageSampleSize = is;
    	mImageMemoryCache = ImageMemoryCache.getInstance();
    	mImageDiskCache = ImageDiskCache.getInstance();
    	
        Log.d("MainActivity:onCreate", "ImageMemoryCache:getInstance");
        Log.d("MainActivity:onCreate", "ImageDiskCache:getInstance");
    }

    @Override
    protected HashMap<Integer, Object> doInBackground(String... url) {
		// Pass the url to download the image
    	HashMap<Integer, Object> hm = fetchImage(url[0]);
		return hm;
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(HashMap<Integer, Object> hm) {
    	if (isCancelled()) {
            hm = null;
        }
        if (mImageViewReference != null) {
            ImageView imageView = mImageViewReference.get();
            if (imageView != null) {
            	Bitmap bitmap = (Bitmap) hm.get(1);
                imageView.setImageBitmap(bitmap);
                String url = (String) hm.get(0);
                // Store the image to the cache
				if (bitmap != null) {
					//mImageMemoryCache.addImageToCache(url, bitmap);
					mImageDiskCache.addImageToCache(url, bitmap);
				}
            }
        }
    }
    
	private HashMap<Integer, Object> fetchImage(String url) {
		Bitmap bitmap = null; 
		try {
			// Try from the cache
			//if (mImageMemoryCache.containsImage(url)) {
			if (mImageDiskCache.containsImage(url)) {
				//bitmap = mImageMemoryCache.getImageFromDiskCache(url);
				bitmap = mImageDiskCache.getImageFromDiskCache(url);
				Log.d("fetchImage", "from cache");
			} else {
			// From the network
				final BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = mImageSampleSize; // image size divided by 2
				options.inJustDecodeBounds = false;
				URLConnection connection = (new URL(url)).openConnection();
				connection.setUseCaches(true); 
				connection.connect();
				InputStream input = connection.getInputStream(); 
				bitmap = BitmapFactory.decodeStream(input, null, options);	
				Log.d("fetchImage", "from web");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HashMap<Integer, Object> hm = new HashMap<Integer, Object>();
		hm.put(0, url);
		hm.put(1, bitmap);
		return hm;
   }

}