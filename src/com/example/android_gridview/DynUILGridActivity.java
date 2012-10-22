package com.example.android_gridview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DynUILGridActivity extends Activity {
	
	private GridView mGridView;
	
	private ArrayList<String> mImageUrls = new ArrayList<String>();

	private DisplayImageOptions mOptions;
	
	protected ImageLoader mImageLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_img);
        
        mImageUrls.add("http://static2.dmcdn.net/static/video/501/543/28345105:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/001/543/28345100:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/970/543/28345079:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://sstatic2.dmcdn.net/static/video/170/543/28345071:jpeg_preview_large.jpg?20120507124341");
        
        mImageLoader.init(ImageLoaderConfiguration.createDefault(this));
        
        mOptions = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher)
			.showImageForEmptyUri(android.R.drawable.ic_menu_gallery)
			.cacheInMemory()
			.cacheOnDisc()
			.build();
		
        mGridView = (GridView) findViewById(R.id.dyn_grid_view);
        mGridView.setAdapter(new DynUILAdapter(DynUILGridActivity.this, mOptions, mImageUrls));

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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }
}
