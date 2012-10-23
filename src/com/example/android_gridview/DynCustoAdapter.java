package com.example.android_gridview;

import java.io.File;
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
	private DiskLruCache mDiskCache;
	private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
	private static final String DISK_CACHE_SUBDIR = "thumbnails";

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
	    File cacheDir = mContext.getCacheDir();
	    mDiskCache = DiskLruCache.openCache(this, cacheDir, DISK_CACHE_SIZE);
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
		 new ImageFetcherTask(imageView).execute(mImages.get(position));

		new ImageFetcherTask(imageView).execute(mImages
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
	
	public void addBitmapToCache(String key, Bitmap bitmap) {
	    // Add to memory cache as before
//	    if (getBitmapFromMemCache(key) == null) {
//	        mMemoryCache.put(key, bitmap);
//	    }

	    // Also add to disk cache
	    if (!mDiskCache.containsKey(key)) {
	        mDiskCache.put(key, bitmap);
	    }
	}

	public Bitmap getBitmapFromDiskCache(String key) {
	    return mDiskCache.get(key);
	}

	// Creates a unique subdirectory of the designated app cache directory. Tries to use external
	// but if not mounted, falls back on internal storage.
	public static File getCacheDir(Context context, String uniqueName) {
	    // Check if media is mounted or storage is built-in, if so, try and use external cache dir
	    // otherwise use internal cache dir
	    final String cachePath = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
	            || !Environment.isExternalStorageRemovable() ?
	                    context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();

	    return new File(cachePath + File.separator + uniqueName);
	}
}
