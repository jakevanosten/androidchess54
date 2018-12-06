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
    android.support.v7.widget.GridLayout grid;
    int row,col = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void move(View v){

        if(!firstClickFlag){ //first time clicking a space, store the imageview info for next click
            space1 = (ImageView) v;
            String pieceID = getResources().getResourceName(v.getId());
            int[] locations = getPieceIndex(v, pieceID);
            row = locations[0];
            col = locations[1];
            System.out.println("(" + row + "," + col + ")");
        }
    }

    public int[] getPieceIndex(View v, String id){
        grid = findViewById(R.id.board);
        int[] location = new int[2];
        int childCount = grid.getChildCount();
        for(int i=0;i<childCount;i++){
            if(id == getResources().getResourceName((grid.getChildAt(i)).getId())){ //matches ID
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
