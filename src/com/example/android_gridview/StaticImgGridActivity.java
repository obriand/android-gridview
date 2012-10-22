package com.example.android_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

public class StaticImgGridActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_img);
        
        // ** simple code with static images
        GridView gridView = (GridView) findViewById(R.id.static_grid_view);
        StaticImageAdapter imageAdapter = new StaticImageAdapter(this.getApplicationContext());        
        gridView.setAdapter(imageAdapter);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}