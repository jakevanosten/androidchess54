package com.example.jake.gamePiece;

import com.example.jake.boardData.*;

public class King extends GamePiece{

	public static int castlingFlag = 0;

	public King(int wob) {
		super(wob);
	}


	public boolean isCastling(Locations curr, Locations next, BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();

		int nextCol = next.getX();

		if(currCol==4 && (currRow==0 || currRow==7) && (currCol-nextCol == 2 || nextCol-currCol == 2)) { //trying to move king horiz 2 spaces, now look for rook in place
			if(currCol-nextCol==2) { //looking toward left side for rook
				int i = currCol-1;
				while(!(board[i][currRow].getPiece() instanceof Rook)) {
					if(board[i][currRow].getPiece() != null) {
						return false;
					}
					i--;
				}
				return true;
			}else { //looking to right rook
				int i = currCol+1;
				while(!(board[i][currRow].getPiece() instanceof Rook)) {
					if(board[i][currRow].getPiece() != null) {
						return false;
					}
					i++;
				}
				return true;
			}
		}
		return false;
	}



	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {
		if(isHoriz(curr,next,board)) {
			if(isCastling(curr,next,board)) {
				castlingFlag = 1;
				return true;
			}
		}
		castlingFlag = 0;
		
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
