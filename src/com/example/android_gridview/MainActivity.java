package com.example.android_gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageMemoryCache.initialize(getApplicationContext());
        ImageDiskCache.initialize(getApplicationContext());
        Log.d("MainActivity:onCreate", "ImageMemoryCache:initialize");
        Log.d("MainActivity:onCreate", "ImagediskCache:initialize"); 
    }
    
    // Button static image activity
    public void displayStaticImgActivity(View view) { 
    	Intent intent = new Intent(MainActivity.this, StaticImgGridActivity.class);
    	startActivity(intent); 
    }
    
    // Button dyn image activity
    public void displayDynImgActivity(View view) {  
    	Intent intent = new Intent(MainActivity.this, DynUILGridActivity.class);
    	startActivity(intent);
    }
    
    // Button dyn custo image activity
    public void displayDynCustoImgActivity(View view) {  
    	Intent intent = new Intent(MainActivity.this, DynCustoGridActivity.class);
    	startActivity(intent);
    }


}