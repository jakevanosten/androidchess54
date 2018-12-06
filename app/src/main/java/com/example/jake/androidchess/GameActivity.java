package com.example.jake.androidchess;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;


public class GameActivity extends AppCompatActivity {

    boolean firstClickFlag = false;
    ImageView space1,space2;
    int firstX,firstY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void move(View v){

        if(!firstClickFlag){ //first time clicking a space, store the imageview info for next click
            space1 = (ImageView) v;
            int[] locations = new int[2];
            v.getLocationOnScreen(locations);
            firstX = locations[0];
            firstY = locations[1];
            System.out.println("(" + firstX + "," + firstY + ")");
        }
    }


}
