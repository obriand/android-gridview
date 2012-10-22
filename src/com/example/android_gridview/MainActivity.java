package com.example.android_gridview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    // Button static image activity
    public void displayStaticImgActivity(View view) { 
    	Intent intent = new Intent(MainActivity.this, StaticImgGridActivity.class);
    	startActivity(intent);
    }
    
    // Button static image activity
    public void displayDynImgActivity(View view) {  
    	Intent intent = new Intent(MainActivity.this, DynUILGridActivity.class);
    	startActivity(intent);
    }

}