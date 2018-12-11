package com.example.jake.chessGame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.GridLayout;
import android.widget.TextView;

import com.example.jake.boardData.BoardIndex;
import com.example.jake.gamePiece.*;


public class GameActivity extends AppCompatActivity {

    //Declare and initialize knight pieces; going to disregard the string parameter
    Knight bN1 = new Knight(1);
    Knight bN2 = new Knight(1);
    Knight wN1 = new Knight( 0);
    Knight wN2 = new Knight( 0);

    //Declare and initialize rook pieces; going to disregard the string parameter
    Rook bR1 = new Rook( 1);
    Rook bR2 = new Rook(1);
    Rook wR1 = new Rook(0);
    Rook wR2 = new Rook(0);

    //Declare and initialize bishop pieces; going to disregard the string parameter
    Bishop bB1 = new Bishop(1);
    Bishop bB2 = new Bishop(1);
    Bishop wB1 = new Bishop( 0);
    Bishop wB2 = new Bishop( 0);


    //Declare and initialize Queen Pieces
    Queen bQ = new Queen(1);
    Queen wQ = new Queen(0);

    //Declare and initialize King Pieces
    King bK = new King( 1);
    King wK = new King(0);

    //Declare and initialize pawn Pieces
    Pawn bp1 = new Pawn(1);
    Pawn bp2 = new Pawn(1);
    Pawn bp3 = new Pawn(1);
    Pawn bp4 = new Pawn(1);
    Pawn bp5 = new Pawn(1);
    Pawn bp6 = new Pawn(1);
    Pawn bp7 = new Pawn(1);
    Pawn bp8 = new Pawn(1);

    Pawn wp1 = new Pawn(0);
    Pawn wp2 = new Pawn(0);
    Pawn wp3 = new Pawn(0);
    Pawn wp4 = new Pawn(0);
    Pawn wp5 = new Pawn(0);
    Pawn wp6 = new Pawn(0);
    Pawn wp7 = new Pawn(0);
    Pawn wp8 = new Pawn(0);

    //declare and initialize textview object for display board and internal representation for board
    public TextView[][] chessBoard = new TextView[8][8];
    public BoardIndex[][] internalBoard = new BoardIndex[8][8];
    boolean firstClickFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        GridLayout gridLayout = findViewById(R.id.pieceSpots);

        initInternalBoard();
        initDisplay();
    }

    public void move(View v){


    }

    public void initDisplay(){

        int i,j;

        for(i = 0; i < 8; i++){
            for(j = 0; j < 8; j++){
                //chessBoard[i][j] = (TextView)
            }
        }

    }

    private void initInternalBoard(){
        int i,j;



    }




    public void undo(View v){

    }

    public void upgradePiece(View v){

    }
}