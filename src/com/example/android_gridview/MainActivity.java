package com.example.android_gridview;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.GridView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        
        // Instance of ImageAdapter Class
        ImageAdapter imageAdapter = new ImageAdapter(this.getApplicationContext());        
        gridView.setAdapter(imageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
