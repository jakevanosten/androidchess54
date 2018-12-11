package gamePiece;

import boardData.BoardIndex;
import boardData.Locations;

public class King extends GamePiece{

	public King(int wob) {
		super(wob);
	}
	
	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {
		if(isValidLoc(curr,next,board) && isPathClear(curr,next,board) && isOneSpace(curr,next,board) && (isUp(curr,next,board) || isDown(curr,next,board) || isHoriz(curr,next,board) || isDiag(curr,next,board))) {
			return true;
		}
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for king specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	/*Valid move check should be thorough incase movement will result in putting king in check*/
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
}
