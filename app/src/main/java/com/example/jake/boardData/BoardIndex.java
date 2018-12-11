package com.example.jake.boardData;

import com.example.jake.gamePiece.GamePiece;

public class BoardIndex {
    public GamePiece gp;

    public BoardIndex(GamePiece gp){
        this.gp = gp;
    }

    public void setPiece(GamePiece gp){
        this.gp = gp;
    }

    public GamePiece getPiece(){
        return this.gp;
    }
}
