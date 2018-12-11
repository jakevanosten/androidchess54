package gamePiece;

import boardData.BoardIndex;
import boardData.Locations;

public class Queen extends GamePiece{

	public Queen(int wob) {
		super(wob);
	}

	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {
		if(isValidLoc(curr,next,board) && isPathClear(curr,next,board) && (isUp(curr,next,board) || isDown(curr,next,board) || isHoriz(curr,next,board) || isDiag(curr,next,board))) {return true;}
		
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for queen specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
	
	//Queen Movement: can move any # spaces horizontally, vertically, diagonally
	//essentially, shes a bad bihhh
}
