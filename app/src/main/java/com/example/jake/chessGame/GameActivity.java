package com.example.jake.chessGame;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Environment;


import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import com.example.jake.boardData.BoardIndex;
import com.example.jake.boardData.Locations;
import com.example.jake.gamePiece.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.io.FileWriter;

import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //global counter to help name CSV files
    public static int recordNum = 1;


    //Declare and initialize knight pieces; going to disregard the string parameter
    Knight bN1 = new Knight(1);
    Knight bN2 = new Knight(1);
    Knight wN1 = new Knight(0);
    Knight wN2 = new Knight(0);

    //Declare and initialize rook pieces; going to disregard the string parameter
    Rook bR1 = new Rook(1);
    Rook bR2 = new Rook(1);
    Rook wR1 = new Rook(0);
    Rook wR2 = new Rook(0);

    //Declare and initialize bishop pieces; going to disregard the string parameter
    Bishop bB1 = new Bishop(1);
    Bishop bB2 = new Bishop(1);
    Bishop wB1 = new Bishop(0);
    Bishop wB2 = new Bishop(0);


    //Declare and initialize Queen Pieces
    Queen bQ = new Queen(1);
    Queen wQ = new Queen(0);

    //Declare and initialize King Pieces
    King bK = new King(1);
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
    public TextView gameLog;
    public TextView checkLog;
    public TextView pawnUp;
    public LinearLayout pawn_choices;
    public Locations src;
    public Locations dest;
    boolean undoFlag = false;
    boolean firstClickFlag = true;
    int playerInCheck = -1;
    Locations lastClick = new Locations(0,0);
    Locations firstClick = new Locations(0,0);
    boolean whiteTurn = true;
    GridLayout grid;
    ArrayList<Locations> listOfMoves = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initInternalBoard();
        initDisplay();
        pawnUp = findViewById(R.id.pawnUp);
        pawn_choices = findViewById(R.id.pawn_choices);
        gameLog = findViewById(R.id.gameLog);
        checkLog = findViewById(R.id.checkLog);

    }

    public void undo(View v){
        if(!undoFlag){
            return;
        }
        if(!whiteTurn){
            gameLog.setText("White: Choose Piece");
        }else{
            gameLog.setText("Black: Choose Piece");
        }
        internalBoard[firstClick.getX()][firstClick.getY()].setPiece(internalBoard[lastClick.getX()][lastClick.getY()].getPiece());
        internalBoard[lastClick.getX()][lastClick.getY()].setPiece(null);
        loadPieces();
        chessBoard[lastClick.getX()][lastClick.getY()].setBackgroundResource(0);
        listOfMoves.remove(firstClick); //clear last src
        listOfMoves.remove(lastClick); //clear last dest
        whiteTurn = !(whiteTurn);
        undoFlag = false;
    }

    public void move(View v) {

        if(Pawn.promotionFlag == 1) {//still need to upgrade before move
            if(whiteTurn){
                gameLog.setText("Black: Upgrade Pawn First");
            }else{
                gameLog.setText("White: Upgrade Pawn First");
            }
            return;
        }
        String t = v.getTag().toString();
        char c = t.charAt(0);
        char r = t.charAt(1);
        int row = r - '0';
        int col = c - '0';

        if(firstClickFlag){ //first click
            if(internalBoard[col][row].getPiece() == null){ //clicked on empty space, do nothing
                return;
            }else if((internalBoard[col][row].getPiece().getColor() == 0) != (whiteTurn)){//clicked on wrong team, do nothing
                return;
            }else{ //clicked on correct team
                firstClickFlag = false;
                firstClick.setX(col);
                firstClick.setY(row);
                if(whiteTurn){
                    gameLog.setText("White: Choose Destination");
                }else{
                    gameLog.setText("Black: Choose Destination");
                }

                //Store first click move in listOfMoves Array list
                src = new Locations(firstClick.getX(), firstClick.getY());
                listOfMoves.add(src);




                return;
            }

        }else{ //second click
            if(internalBoard[col][row].getPiece() == null){ //empty destination, check if valid move

                GamePiece curr = internalBoard[firstClick.getX()][firstClick.getY()].getPiece();

                Rook rk = null;
                Knight kn = null;
                Bishop bi = null;
                Queen qu = null;
                King ki = null;
                Pawn pa = null;

                if (curr instanceof Rook){ rk = (Rook) curr;}
                else if (curr instanceof Knight){ kn = (Knight) curr;}
                else if (curr instanceof Bishop){ bi = (Bishop) curr;}
                else if (curr instanceof Queen){ qu = (Queen) curr;}
                else if (curr instanceof King){ ki = (King) curr;}
                else{ pa = (Pawn) curr;}

                if(((rk!=null) ?
                        rk : ((kn!=null) ?
                        kn : ((bi!=null) ?
                        bi : ((qu!=null) ?
                        qu : ((ki!=null) ?
                        ki : pa))))).tryMove(firstClick,new Locations(col,row),internalBoard)) { //is a valid move

                    if (Pawn.promotionFlag ==1){
                        pawnUp.setVisibility(View.VISIBLE);
                        pawn_choices.setVisibility(View.VISIBLE);
                        lastClick.setX(col);
                        lastClick.setY(row);
                        checkForCheck();
                        kingIsCaptured();
                        return;
                    }else if(King.castlingFlag == 1){ //do the castle
                        undoFlag = true;
                        if(whiteTurn && firstClick.getX()<col) {//rook at 77 going to 57

                            internalBoard[6][7].setPiece(curr); //king move
                            internalBoard[5][7].setPiece(internalBoard[7][7].getPiece()); //rook move
                            internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null); //old king delete
                            internalBoard[7][7].setPiece(null);
                            chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                            chessBoard[7][7].setBackgroundResource(0);
                        }else if(whiteTurn && firstClick.getX() > col) { //rook at 07 going to 37

                            internalBoard[2][7].setPiece(curr); //king move
                            internalBoard[3][7].setPiece(internalBoard[0][7].getPiece()); //rook move
                            internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null); //old king delete
                            internalBoard[0][7].setPiece(null);
                            chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                            chessBoard[0][7].setBackgroundResource(0);
                        }else if(!(whiteTurn) && firstClick.getX()<col) {//rook at 70 going to 50

                            internalBoard[6][0].setPiece(curr); //king move
                            internalBoard[5][0].setPiece(internalBoard[7][0].getPiece()); //rook move
                            internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null); //old king delete
                            internalBoard[7][0].setPiece(null);
                            chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                            chessBoard[7][0].setBackgroundResource(0);
                        }else if(!(whiteTurn) && firstClick.getX() > col) { //rook at 00 going to d8

                            internalBoard[2][0].setPiece(curr); //king move
                            internalBoard[3][0].setPiece(internalBoard[0][0].getPiece()); //rook move
                            internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null); //old king delete
                            internalBoard[0][0].setPiece(null);
                            chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                            chessBoard[0][0].setBackgroundResource(0);
                        }
                        King.castlingFlag = 0;
                    }else {
                        undoFlag = true;
                        internalBoard[col][row].setPiece(curr);
                        internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                        chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                    }

                    firstClickFlag = true;
                    whiteTurn = !(whiteTurn);
                    if(whiteTurn){
                        gameLog.setText("White: Choose Piece");
                    }else{
                        gameLog.setText("Black: Choose Piece");
                    }
                    checkForCheck();
                    kingIsCaptured();
                }else{ //move not valid, do nothing
                    firstClickFlag = true;
                    if(whiteTurn){
                        gameLog.setText("White: Choose Piece");
                    }else{
                        gameLog.setText("Black: Choose Piece");
                    }
                    return;
                }
            }else{//piece in space
                if((internalBoard[col][row].getPiece().getColor() != internalBoard[firstClick.getX()][firstClick.getY()].getPiece().getColor())){ //diff team in space

                    GamePiece curr = internalBoard[firstClick.getX()][firstClick.getY()].getPiece();

                    Rook rk = null;
                    Knight kn = null;
                    Bishop bi = null;
                    Queen qu = null;
                    King ki = null;
                    Pawn pa = null;

                    if (curr instanceof Rook){ rk = (Rook) curr;}
                    else if (curr instanceof Knight){ kn = (Knight) curr;}
                    else if (curr instanceof Bishop){ bi = (Bishop) curr;}
                    else if (curr instanceof Queen){ qu = (Queen) curr;}
                    else if (curr instanceof King){ ki = (King) curr;}
                    else{ pa = (Pawn) curr;}

                    if(((rk!=null) ?
                            rk : ((kn!=null) ?
                            kn : ((bi!=null) ?
                            bi : ((qu!=null) ?
                            qu : ((ki!=null) ?
                            ki : pa))))).tryMove(firstClick,new Locations(col,row),internalBoard)) { //valid move

                        undoFlag = true;
                        internalBoard[col][row].setPiece(curr);
                        internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                        chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);

                        if (internalBoard[col][row].getPiece() instanceof King) {
                            gameLog.setText("Recording Saved.");
                            checkLog.setVisibility(View.VISIBLE);
                            //checkLog.setText("Hey bitch");
                            return;
                            //save recording
                        }

                        firstClickFlag = true;
                        whiteTurn = !(whiteTurn);
                        if(whiteTurn){
                            gameLog.setText("White: Choose Piece");
                        }else{
                            gameLog.setText("Black: Choose Piece");
                        }
                        checkForCheck();
                        kingIsCaptured();

                    }else{ //move not valid
                        firstClickFlag = true;
                        if(whiteTurn){
                            gameLog.setText("White: Choose Piece");
                        }else{
                            gameLog.setText("Black: Choose Piece");
                        }
                        return;
                    }

                }else{//same team
                    return;
                }
            }
        }
        checkForCheck();
        kingIsCaptured();
        lastClick.setX(col);
        lastClick.setY(row);

        //Store second click into listOfMoves arraylist
        dest = new Locations(lastClick.getX(), lastClick.getY());
        listOfMoves.add(dest);


        loadPieces();
    }

    public void initDisplay() {

        grid = findViewById(R.id.pieceSpots);
        int i, j;
        int k = 0;
        for (j = 0; j < 8; j++) {
            for (i = 0; i < 8; i++) {
                chessBoard[i][j] = (TextView) findViewById(grid.getChildAt(k).getId());
                if (internalBoard[i][j].getPiece() == null) {
                    undoBoard[i][j] = new BoardIndex(null);
                } else {
                    undoBoard[i][j].setPiece(internalBoard[i][j].getPiece());
                }
                k++;
            }
        }

        loadPieces();
    }

    public void loadPieces() {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                int x;
                int color;

                if (internalBoard[i][j].getPiece() instanceof Pawn) {
                    color = internalBoard[i][j].getPiece().getColor();
                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wpawn);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.bpawn);
                    }
                } else if (internalBoard[i][j].getPiece() instanceof Rook) {
                    color = internalBoard[i][j].getPiece().getColor();

                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wrook);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.brook);
                    }
                } else if (internalBoard[i][j].getPiece() instanceof Knight) {
                    color = internalBoard[i][j].getPiece().getColor();

                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wknight);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.bknight);
                    }
                } else if (internalBoard[i][j].getPiece() instanceof Bishop) {
                    color = internalBoard[i][j].getPiece().getColor();

                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wbishop);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.bbishop);
                    }
                } else if (internalBoard[i][j].getPiece() instanceof Queen) {
                    color = internalBoard[i][j].getPiece().getColor();

                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wqueen);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.bqueen);
                    }
                } else if (internalBoard[i][j].getPiece() instanceof King) {
                    color = internalBoard[i][j].getPiece().getColor();

                    if (color == 0) {
                        chessBoard[i][j].setBackgroundResource(R.drawable.wking);
                    } else {
                        chessBoard[i][j].setBackgroundResource(R.drawable.bking);
                    }
                }
            }
        }
    }



    public void initInternalBoard(){

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
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
        internalBoard[7][6].setPiece(wp8);


        //set white pieces
        internalBoard[0][7].setPiece(wR1);
        internalBoard[1][7].setPiece(wN1);
        internalBoard[2][7].setPiece(wB1);
        internalBoard[3][7].setPiece(wQ);
        internalBoard[4][7].setPiece(wK);
        internalBoard[5][7].setPiece(wB2);
        internalBoard[6][7].setPiece(wN2);
        internalBoard[7][7].setPiece(wR2);

       // printInternalBoard(internalBoard);

    }

//for debugging&testing purposes
   public void printInternalBoard(BoardIndex[][] internalBoard){

        int i, j;

        for(j = 0; j < 8; j++){
            for(i = 0; i < 8; i++){

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
            System.out.println();
        }


   }

    public void upgradePiece(View v){
        String t = v.getTag().toString();
        Pawn upper = (Pawn) internalBoard[firstClick.getX()][firstClick.getY()].getPiece();
        int wob = upper.getColor();
        switch (t) {
            case "pq":
                internalBoard[lastClick.getX()][lastClick.getY()].setPiece(new Queen(wob));
                internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                break;
            case "pb":
                internalBoard[lastClick.getX()][lastClick.getY()].setPiece(new Bishop(wob));
                internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                break;
            case "pr":
                internalBoard[lastClick.getX()][lastClick.getY()].setPiece(new Rook(wob));
                internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                break;
            case "pk":
                internalBoard[lastClick.getX()][lastClick.getY()].setPiece(new Knight(wob));
                internalBoard[firstClick.getX()][firstClick.getY()].setPiece(null);
                chessBoard[firstClick.getX()][firstClick.getY()].setBackgroundResource(0);
                break;
        }

        loadPieces();
        pawnUp.setVisibility(View.INVISIBLE);
        pawn_choices.setVisibility(View.INVISIBLE);
        if(!whiteTurn){
            gameLog.setText("White: Choose Piece");
        }else{
            gameLog.setText("Black: Choose Piece");
        }
        Pawn.promotionFlag = 0;
        firstClickFlag = true;
        whiteTurn = !(whiteTurn);
        checkForCheck();
        kingIsCaptured();
    }
    public ArrayList<Locations> getPossibleMoves(Locations from){
        int k =0;
        int m =0;
        Locations to;
        ArrayList<Locations> possMoves = new ArrayList<>();

        for (k = 0; k < 8; k++) {
            for (m = 0; m < 8; m++) {
                to = new Locations(m,k);
                if(internalBoard[m][k].getPiece() != null) {
                    if (internalBoard[from.getX()][from.getY()].getPiece().tryMove(from, to, internalBoard)) {
                        possMoves.add(to);
                    }
                }
            }
        }
        return possMoves;
    }

    /*
    public void aiMove(View v){

        Button aiButton = (Button) findViewById(R.id.ai_butt);

        getPossibleMoves()

    }
    */

    public boolean checkForCheck() {
        ArrayList<Locations> pieceMoves = new ArrayList<>();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                pieceMoves.clear();

                if (internalBoard[i][j].getPiece() != null) {
                    pieceMoves = getPossibleMoves(new Locations(i, j));
                    for (int n = 0; n <= pieceMoves.size() - 1; n++) {
                        System.out.println(pieceMoves.get(n).getX() + "," + pieceMoves.get(n).getY());
                        if (internalBoard[pieceMoves.get(n).getX()][pieceMoves.get(n).getY()].getPiece() instanceof King) {
                            checkLog.setVisibility(View.VISIBLE);
                            if(playerInCheck == 0){
                                checkLog.setText("White King in Check!");
                            }
                            else if(playerInCheck == 1){
                                checkLog.setText("Black King in Check!");
                            }else {
                                if (!whiteTurn) {
                                    checkLog.setText("Black King in Check!");
                                    playerInCheck = 1;
                                } else {
                                    checkLog.setText("White King in Check!");
                                    playerInCheck = 0;
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        playerInCheck = -1;
        checkLog.setVisibility(View.INVISIBLE);
        return false;
    }


    public boolean kingIsCaptured(){
        int kingCount = 0;
        boolean isKingBlack = false;
        boolean isKingWhite = false;

        for (int j = 0; j < 8; j++){
            for (int i = 0; i < 8; i++){

                if(internalBoard[i][j].getPiece() instanceof King && internalBoard[i][j].getPiece().getColor() == 1) {
                    kingCount++;
                    isKingBlack = true;
                }

                if(internalBoard[i][j].getPiece() instanceof King && internalBoard[i][j].getPiece().getColor() == 0) {
                    kingCount++;
                    isKingWhite = true;
                }

                else{
                    continue;
                }
            }
        }

        if(kingCount == 2 && isKingBlack == true && isKingWhite == true){
            return false;
        }

        if(kingCount == 1 && isKingBlack == true){
            gameOver(1);    //TRUE! king IS captured; black wins!
        }

        if(kingCount == 1 && isKingWhite == true){
            gameOver(0);    //TRUE! king IS captured; white wins!
        }

     return false;
    }


    public void drawGame(View v){


        Button drawButton = (Button) findViewById(R.id.draw);

        AlertDialog.Builder drawDialog = new AlertDialog.Builder(this);
        drawDialog.setTitle("End Game in Draw?");

        drawDialog.setIcon(R.drawable.finishflag);


        drawDialog.setPositiveButton("Yes, Draw Game!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                gameOver(-1);

            }
        });

        drawDialog.setNeutralButton("No!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog alert = drawDialog.create();
        alert.show();




    }

    public void resignGame(View v){


        Button resignButton = (Button) findViewById(R.id.resign);


        if(whiteTurn){
            gameLog.setText("Resign: Black wins!");
            gameOver(1);
        }else{
            gameLog.setText("Resign: White wins!");
            gameOver(0);
        }





    }






    public void printListOfMoves(ArrayList<Locations> listOfMoves){

        int listSize = listOfMoves.size();
        for(int i = 0; i < listSize; i++){
            int x = listOfMoves.get(i).getX();
            int y = listOfMoves.get(i).getY();

            System.out.println(x + "," + y);
            System.out.println();

        }

    }


    public boolean isExternalStorageWritable(){

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            return true;
        }
        return false;
    }

    public boolean isExternalStorageAvailable(){
        String extStorageState = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(extStorageState)){
            return true;
        }else{
            return false;
        }
    }

    public void writeRecordFileInExternalStorage(Context context, ArrayList<Locations> listOfMoves){

        boolean isWritable = isExternalStorageWritable();
        boolean isAvailable = isExternalStorageAvailable();
        String filename = "record" + recordNum + ".txt";
        System.out.println("filename is: " + filename);

        if(isWritable == true && isAvailable == true) {

            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            System.out.println("Base Directory Path: " + baseDir);

            File file = new File(baseDir, filename);

            FileWriter writer = null;
            try{

                writer = new FileWriter(file);

                for(Locations location: listOfMoves){
                    writer.write(location.getX());
                    writer.write(location.getY());
                    writer.write(NEW_LINE_SEPARATOR);
                }

                writer.close();
                System.out.println("Successful! Created file: " + filename);
                recordNum++;

            }catch(Exception e){
                e.printStackTrace();
            }


        }else{
            System.out.println("Uh-oh. ExternalStorage cannot be written to!");
            return;
        }
    }


    /*
    public void writeToFile(ArrayList<Locations> listOfMoves, Context context){
        try{

            String filename = "record" + recordNum + ".txt";
            System.out.println("filename is: " + filename);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));


            for(Locations location : listOfMoves){
                outputStreamWriter.write(location.getX());
                outputStreamWriter.write(location.getY());
                outputStreamWriter.write(NEW_LINE_SEPARATOR);
            }


            outputStreamWriter.close();
            System.out.println("Record File has successfully been created!");

        }catch(IOException e){
            Log.e("Exception", "file write failed!" + e.toString());
        }
    }




    public void test(TextView tv){

        String innermessage = "this is a message from records activity";

        tv.setText(innermessage);

    }

    */
    public void gameOver(int winner){

        AlertDialog.Builder gameOverDialog = new AlertDialog.Builder(this);
        gameOverDialog.setTitle("Game Over!");

        gameOverDialog.setIcon(R.drawable.finishflag);

        if(winner == 0){
            gameOverDialog.setMessage("White Wins!");
        }

        if(winner == 1){
            gameOverDialog.setMessage("Black Wins!");
        }

        if(winner == -1){       //represents draw
            gameOverDialog.setMessage("Draw!");
        }

        gameOverDialog.setPositiveButton("Save Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    nameRecord();
                    writeRecordFileInExternalStorage(GameActivity.this, listOfMoves);
                    //writeToFile(listOfMoves, GameActivity.this);
                    System.out.println("File successfull created!");


                    Intent backToMain = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(backToMain);



                }catch(Exception e){
                    System.out.println("ERR. Couldn't create file");
                    e.printStackTrace();
                }
            }
        });

        gameOverDialog.setNegativeButton("Don't Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               System.out.println("Game not Recorded/Saved!");

                //return to main menu
                Intent backToMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(backToMain);

            }
        });

        AlertDialog alert = gameOverDialog.create();
        alert.show();



    }

    public void nameRecord(){
        AlertDialog.Builder namingRecordDialog = new AlertDialog.Builder(GameActivity.this);

        namingRecordDialog.setTitle("Game Title");
        namingRecordDialog.setMessage("Enter Name for Game");

        final EditText input = new EditText(GameActivity.this);
        LinearLayout.LayoutParams lpd = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lpd);

        namingRecordDialog.setView(input);

        namingRecordDialog.setPositiveButton("Save Game Title", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gameTitle = input.getText().toString();
            }
        });

        namingRecordDialog.setNeutralButton("Set to Default Title", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gameTitle = "record" + recordNum;

                Toast.makeText(getApplicationContext(), "default title: record" +recordNum, Toast.LENGTH_SHORT).show();
                }
        });

    }

}