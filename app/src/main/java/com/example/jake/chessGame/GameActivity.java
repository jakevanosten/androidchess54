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
    public BoardIndex[][] undoBoard = new BoardIndex[8][8];

    boolean firstClickFlag = true;
    GridLayout grid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initInternalBoard();
        initDisplay();
    }

    public void move(View v){


    }


    public void initDisplay(){

        grid = findViewById(R.id.pieceSpots);
        int i,j;

        for(i = 0; i < 8; i++){
            for(j = 0; j < 8; j++){
                chessBoard[i][j] = (TextView) findViewById(grid.getChildAt(i).getId());
                if(internalBoard[i][j].getPiece() == null){
                    undoBoard[i][j] = null;
                }else{
                    undoBoard[i][j].setPiece(internalBoard[i][j].getPiece());
                }
            }
        }

        loadPieces();
    }

    public void loadPieces(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){

                if(internalBoard[i][j].getPiece() == null){
                    chessBoard[i][j].setBackgroundResource(0);
                }else {
                    GamePiece gp = internalBoard[i][j].getPiece();

                    if (gp instanceof Pawn) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wpawn);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.bpawn);
                        }
                    } else if (gp instanceof Rook) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wrook);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.brook);
                        }
                    } else if (gp instanceof Knight) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wknight);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.bknight);
                        }
                    } else if (gp instanceof Bishop) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wbishop);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.bbishop);
                        }
                    } else if (gp instanceof Queen) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wqueen);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.bqueen);
                        }
                    } else if (gp instanceof King) {
                        if (gp.getColor() == 0) {
                            chessBoard[i][j].setBackgroundResource(R.drawable.wking);
                        } else {
                            chessBoard[i][j].setBackgroundResource(R.drawable.bking);
                        }
                    }
                }
            }
        }
        checkForCheck();
    }



    private void initInternalBoard(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                internalBoard[i][j] = new BoardIndex(null);
                undoBoard[i][j] = new BoardIndex(null);
            }
        }
        //set black special pieces
        internalBoard[0][0].setPiece(bR1);
        internalBoard[1][0].setPiece(bN1);
        internalBoard[2][0].setPiece(bB1);
        internalBoard[3][0].setPiece(bQ);
        internalBoard[4][0].setPiece(bK);
        internalBoard[5][0].setPiece(bB2);
        internalBoard[6][0].setPiece(bN2);
        internalBoard[7][0].setPiece(bR2);

        //set black pawns
        internalBoard[0][1].setPiece(bp1);
        internalBoard[1][1].setPiece(bp2);
        internalBoard[2][1].setPiece(bp3);
        internalBoard[3][1].setPiece(bp4);
        internalBoard[4][1].setPiece(bp5);
        internalBoard[5][1].setPiece(bp6);
        internalBoard[6][1].setPiece(bp7);
        internalBoard[7][1].setPiece(bp8);

        //set white pawns
        internalBoard[0][6].setPiece(wp1);
        internalBoard[1][6].setPiece(wp2);
        internalBoard[2][6].setPiece(wp3);
        internalBoard[3][6].setPiece(wp4);
        internalBoard[4][6].setPiece(wp5);
        internalBoard[5][6].setPiece(wp6);
        internalBoard[6][6].setPiece(wp7);

        //set white pieces
        internalBoard[0][7].setPiece(wR1);
        internalBoard[1][7].setPiece(wN1);
        internalBoard[2][7].setPiece(wR1);
        internalBoard[3][7].setPiece(wQ);
        internalBoard[4][7].setPiece(wK);
        internalBoard[5][7].setPiece(wR2);
        internalBoard[6][7].setPiece(wN2);
        internalBoard[7][7].setPiece(wR2);



        printInternalBoard(internalBoard);

    }

//for debugging&testing purposes
   public void printInternalBoard(BoardIndex[][] internalBoard){

        int i, j;

        for(i = 0; i < 8; i++){
            for(j = 0; j < 8; j++){

                if (internalBoard[i][j].getPiece() instanceof Rook) {
                    System.out.print("R ");
                }

                else if(internalBoard[i][j].getPiece() instanceof Knight){
                    System.out.print("N ");
                }

                else if(internalBoard[i][j].getPiece() instanceof  Bishop){
                    System.out.print("B ");
                }

                else if(internalBoard[i][j].getPiece() instanceof Queen){
                    System.out.print("Q ");

                }

                else if(internalBoard[i][j].getPiece() instanceof King){
                    System.out.print("K ");

                }

                else if(internalBoard[i][j].getPiece() instanceof Pawn){
                    System.out.print("P ");

                }

            }
        }


   }

    public void undo(View v){

    }

    public void upgradePiece(View v){

    }

    public void checkForCheck(){

    }
}