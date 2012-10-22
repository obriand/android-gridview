package com.example.android_gridview;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.GridView;
import android.graphics.Bitmap;

public class DynCustoGridActivity extends Activity {
	
	private GridView mGridView;
	
	private ArrayList<String> mImageUrls = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_dyn_img);
        
        mImageUrls.add("http://static2.dmcdn.net/static/video/501/543/28345105:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/001/543/28345100:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/970/543/28345079:jpeg_preview_large.jpg?20120507124341");
        mImageUrls.add("http://static2.dmcdn.net/static/video/170/543/28345071:jpeg_preview_large.jpg?20120507124341");
		
        mGridView = (GridView) findViewById(R.id.dyn_custo_grid_view);
        mGridView.setAdapter(new DynCustoAdapter(DynCustoGridActivity.this, mImageUrls));
        
    }

}
