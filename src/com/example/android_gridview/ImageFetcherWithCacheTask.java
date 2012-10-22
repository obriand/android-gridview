package com.example.android_gridview;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class ImageFetcherWithCacheTask extends AsyncTask<String, Void, Bitmap> {

    private final WeakReference<ImageView> imageViewReference;

    public ImageFetcherWithCacheTask(ImageView imageView) {
        imageViewReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... url) {
         // Pass the url to download the image
         return fetchImage(url[0]);
    }

    @Override
    // Once the image is downloaded, associates it to the imageView
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null) {
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
    
	private Bitmap fetchImage(String url) {
		Bitmap bitmap = null;
		// Get image from the network		
		try {
			bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
			return bitmap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
   }

}