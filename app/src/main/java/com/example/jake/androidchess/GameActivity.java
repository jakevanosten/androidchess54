package com.example.jake.androidchess;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;


public class GameActivity extends AppCompatActivity {

    boolean firstClickFlag = true;
    View space1,space2;
    android.support.v7.widget.GridLayout grid;
    int row,col = 0;
    int space1Color,space2Color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void move(View v){

        if(firstClickFlag){ //first time clicking a space, store the imageview info for next click
            space1 = v;
            space1Color = ((ColorDrawable) v.getBackground()).getColor();
            int[] locations = getPieceIndex(v);
            row = locations[0];
            col = locations[1];
            v.setBackgroundColor(Color.CYAN);
            System.out.println("Hi");
            firstClickFlag = false;
        }else{ //second space clicked, time to do the move
            space2 = v;
            int[] newLoc = getPieceIndex(v);

            firstClickFlag = true;
        }
    }

    public int[] getPieceIndex(View v){
        grid = findViewById(R.id.board);
        int[] location = new int[2];
        int childCount = grid.getChildCount();
        for(int i=0;i<childCount;i++){
            if(v.equals((View) grid.getChildAt(i))){
                location[0] = i/8;
                location[1] = i%8;
                break;
            }else{
                location[0] = -1;
                location[1] = -1;
            }
        }
        return location;
    }


}
