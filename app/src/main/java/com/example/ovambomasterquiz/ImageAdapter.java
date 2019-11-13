package com.example.ovambomasterquiz;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
  private Context context;


    public ImageAdapter(Context context){
      this.context= context;


  }


    @Override
    public int getCount() {
        return 16;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView;
      if(convertView == null){

          Resources r = Resources.getSystem();
          float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, r.getDisplayMetrics());

          imageView= new ImageView(this.context);
          imageView.setLayoutParams(new GridView.LayoutParams((int)px, (int)px));
          imageView.setScaleType(ImageView.ScaleType.FIT_XY);
          imageView.setBackgroundColor(Color.YELLOW);

      } else imageView= (ImageView) convertView;


        imageView.setImageResource(R.drawable.questionmark);
        return imageView;
    }
}
