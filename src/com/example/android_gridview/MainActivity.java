package com.example.android_gridview;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class MainActivity extends Activity {
	
	private String[] imageUrls = {
			"http://static2.dmcdn.net/static/video/501/543/28345105:jpeg_preview_large.jpg?20120507124341",
            "http://static2.dmcdn.net/static/video/001/543/28345100:jpeg_preview_large.jpg?20120507124341",
            "http://static2.dmcdn.net/static/video/970/543/28345079:jpeg_preview_large.jpg?20120507124341",
            "http://static2.dmcdn.net/static/video/170/543/28345071:jpeg_preview_large.jpg?20120507124341",
    };

	private DisplayImageOptions options;
	
	protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ** simple code with static images
//        GridView gridView = (GridView) findViewById(R.id.grid_view);
//        ImageAdapter imageAdapter = new ImageAdapter(this.getApplicationContext());        
//        gridView.setAdapter(imageAdapter);
        
        // ** code with lazy loading using Universal Image Loader (from nostra13)
        // http://static2.dmcdn.net/static/video/501/543/28345105:jpeg_preview_large.jpg?20120507124341
        // http://static2.dmcdn.net/static/video/001/543/28345100:jpeg_preview_large.jpg?20120507124341
        // http://static2.dmcdn.net/static/video/970/543/28345079:jpeg_preview_large.jpg?20120507124341
        // http://static2.dmcdn.net/static/video/170/543/28345071:jpeg_preview_large.jpg?20120507124341
        // http://2.bp.blogspot.com/_mhyzW5rK904/TMRFf8CuMcI/AAAAAAAABdU/8e2fy-ZTr2I/s1600/9.+Pajaroto+Blog.jpg
        
        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        
        options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.cacheInMemory()
			.cacheOnDisc()
			.build();
		GridView gridView = (GridView) findViewById(R.id.grid_view);
		gridView.setAdapter(new ImageAdapter());

        /**
         * On Click event for Single Gridview Item
         * */
//        gridView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
// 
//                // Sending image id to FullScreenActivity
//                Intent i = new Intent(getApplicationContext(), FullImageActivity.class);
//                // passing array index
//                i.putExtra("id", position);
//                startActivity(i);
//            }
//        });
        
    }
    
	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return imageUrls.length;
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
				imageView = (ImageView) getLayoutInflater().inflate(R.layout.item_grid_image, parent, false);
			} else {
				imageView = (ImageView) convertView;
			}

			imageLoader.displayImage(imageUrls[position], imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
					imageView.setAnimation(anim);
					anim.start();
				}
			});

			return imageView;
		}
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
