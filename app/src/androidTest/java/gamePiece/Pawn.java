package gamePiece;

import boardData.BoardIndex;
import boardData.Locations;

public class Pawn extends GamePiece{


	public Pawn(int wob) {
		super(wob);
	}
	
	public boolean isFirstMove(Locations curr, BoardIndex[][] board) {
		
		if((this.whiteOrBlack == 0 && curr.getY() == 1) || (this.whiteOrBlack == 1 && curr.getY() == 6)){
			return true;
		}
		return false;
	}
	
	public boolean tryMove(Locations curr, Locations next,BoardIndex[][] board) { //This is complicated, need to do diagonal if trying to capture, otherwise get length to be 1 or 2
		if(isValidLoc(curr,next,board)) {return true;}
		
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for pawn specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
}
