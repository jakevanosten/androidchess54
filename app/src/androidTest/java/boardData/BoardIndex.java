package boardData;

import gamePiece.GamePiece;

public class BoardIndex {
    private GamePiece gp;

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
