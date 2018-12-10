package gamePiece;

public class Pawn extends GamePiece{

	public String pawnMove;
	
	public Pawn(String tag, int wob) {
		super(tag, wob);
	}	
	
	/*
	public String getPawnMove(){
		return pawnMove;
	}
	
	public void setPawnMove(String tag) {
		pawnMove = tag;
	}
	*/
	
	public boolean isFirstMove(String curr) {
		
		if((this.whiteOrBlack == 0 && curr.charAt(1) == '2') || (this.whiteOrBlack == 1 && curr.charAt(1) == '7')){
			return true;
		}
		return false;
	}
	
	public boolean tryMove(String curr, String next) { //This is complicated, need to do diagonal if trying to capture, otherwise get length to be 1 or 2
		if(isValidLoc(curr,next)) {return true;}
		
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for pawn specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
}
