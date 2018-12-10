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
    android.widget.GridLayout grid;
    int firstLoc;
    int space1Color,space2Color;
    View[] pieceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }

    public void move(View v){


        if(firstClickFlag){ //first time clicking a space, store the imageview info for next click
            space1 = v;
            space1Color = ((ColorDrawable) v.getBackground()).getColor();
            firstLoc = getPieceIndex(v);
            v.setBackgroundColor(Color.CYAN);
            firstClickFlag = false;

        }else{ //second space clicked, time to do the move

            grid = findViewById(R.id.board);
            for(int i=0;i<64;i++){
                pieceOrder[i] = grid.getChildAt(i);
            }


            space2 = v;
            space2Color = ((ColorDrawable) v.getBackground()).getColor();
            int newLoc = getPieceIndex(v);

            grid = findViewById(R.id.board);
            space1.setBackgroundColor(space2Color);
            space2.setBackgroundColor(space1Color);

            View temp = space1;
            pieceOrder[firstLoc] = space2;
            pieceOrder[newLoc] = temp;

            grid = new GridLayout(this);
            for(int i=0;i<63;i++){
                grid.addView(pieceOrder[i],i);
            }

            firstClickFlag = true;
        }
    }

    public int getPieceIndex(View v){
        grid = findViewById(R.id.board);
        int childCount = grid.getChildCount();

        for(int i=0;i<childCount;i++) {
            if (v.equals((View) grid.getChildAt(i))) {
                return i;
            }
        }
        return -1;
    }


}
