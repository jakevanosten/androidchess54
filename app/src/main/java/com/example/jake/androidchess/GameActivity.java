package com.example.jake.androidchess;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.gameBoard.Board;
import com.example.gamePiece.Bishop;
import com.example.gamePiece.King;
import com.example.gamePiece.Queen;
import com.example.gamePiece.Pawn;
import com.example.gamePiece.Rook;
import com.example.gamePiece.Knight;
import com.example.gamePiece.BlankSpace;
import com.example.gamePiece.CellType;
import com.example.gamePiece.Legend;


public class GameActivity extends AppCompatActivity {

    //declare bishop pieces
    Bishop bB1;
    Bishop bB2;
    Bishop wB1;
    Bishop wB2;

    //declare knight pieces
    Knight bK1;
    Knight bK2;
    Knight wK1;
    Knight wK2;

    //declare king pieces


    public void setDisplayBoard(TextView[][] displayBoard) {
        DisplayBoard = displayBoard;
    }

    boolean firstClickFlag = true;

    public TextView[][] DisplayBoard = new TextView[8][8];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

    }

    public void move(View v){

    }




}
