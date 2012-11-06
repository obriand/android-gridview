package com.obriand.android_gridview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class StaticImageAdapter extends BaseAdapter {
    private Context mContext;
 
    // Keep all Images in array
    public Integer[] mThumbIds = {
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery, android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_gallery
    };
 
    // Constructor
    public StaticImageAdapter(Context c){
        mContext = c;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
 
}