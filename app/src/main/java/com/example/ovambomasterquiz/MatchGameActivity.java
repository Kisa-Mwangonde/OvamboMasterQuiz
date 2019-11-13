package com.example.ovambomasterquiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MatchGameActivity extends AppCompatActivity {

    ImageView currentView= null;
    private int countPair=0;
    final int[] drawabale= new int[] { R.drawable.bowl,R.drawable.hut, R.drawable.dress,
            R.drawable.meal, R.drawable.nsima, R.drawable.culturaldress, R.drawable.work, R.drawable.pot};

   int[] pos= {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
   int currentPos= -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matchgame);

        GridView gridView= (GridView) findViewById(R.id.gridView);
        ImageAdapter imageAdapter= new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentPos < 0){
                    currentPos= position;
                    currentView= (ImageView)view;
                    ((ImageView) view).setImageResource(drawabale[pos[position]]);
                }

                else{

                    if(currentPos == position){
                        ((ImageView) view).setImageResource(R.drawable.questionmark);

                    }
                    else if (pos[currentPos] != pos[position]){
                        currentView.setImageResource(R.drawable.questionmark);
                        Toast.makeText(getApplicationContext(), "Not a Match", Toast.LENGTH_SHORT).show();
                    }

                    else{

                        ((ImageView) view).setImageResource(drawabale[pos[position]]);
                        countPair++;

                        if (countPair==0){
                            Toast.makeText(getApplicationContext(), "You win", Toast.LENGTH_SHORT).show();

                        }
                    }

                    currentPos=-1;
                }



            }


        });


    }



}

