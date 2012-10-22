package com.example.android_gridview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DynCustoGridActivity extends Activity {
	
	private GridView mGridView;
	
	private ArrayList<String> mImageUrls = new ArrayList<String>();

	private DisplayImageOptions mOptions;
	
	protected ImageLoader mImageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_dyn_img);
        
        mImageUrls.add("http://static2.dmcdn.net/static/video/501/543/28345105:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/001/543/28345100:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/970/543/28345079:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/170/543/28345071:jpeg_preview_large.jpg?20120507124341");
        
        mImageLoader.init(ImageLoaderConfiguration.createDefault(this));
        
        mOptions = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher)
			.showImageForEmptyUri(R.drawable.ic_launcher)
			.cacheInMemory()
			.cacheOnDisc()
			.build();
		
        mGridView = (GridView) findViewById(R.id.dyn_custo_grid_view);
        mGridView.setAdapter(new DynUILAdapter(DynCustoGridActivity.this, mOptions, mImageUrls));
        
    }

}
