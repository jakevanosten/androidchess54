package com.example.jake.gamePiece;

import com.example.jake.boardData.*;

public class Pawn extends GamePiece{

	public static int promotionFlag = 0;

	public Pawn(int wob) {
		super(wob);
	}
	
	public boolean isFirstMove(Locations curr) {
		
		if((this.getColor() == 1 && curr.getY() == 1) || (this.getColor() == 0 && curr.getY() == 6)){
			return true;
		}
		return false;
	}

	public boolean isPromoting(Locations curr, Locations next, BoardIndex[][] board) {
		GamePiece currPiece = board[curr.getX()][curr.getY()].getPiece();
		if(currPiece == null){
			return false;
		}

		int currColor = currPiece.getColor();

		if(currColor == 0 && next.getY() == 0) { //white pawn reaching top row of board
			return true;
		}else if(currColor == 1 && next.getY() == 7) { //black pawn reaching bottom row of board
			return true;
		}
		return false;
	}

	public boolean oppInSpace(Locations curr, Locations next, BoardIndex[][] board) {
		GamePiece currPiece = board[curr.getX()][curr.getY()].getPiece();
		GamePiece nxtPiece = board[next.getX()][next.getY()].getPiece();

		int currColor = currPiece.getColor();

		if(nxtPiece != null) {
			if (nxtPiece.getColor() != currColor) {
				return true;
			}
			return false;
		}
		return false;
	}


	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) { //This is complicated, need to do diagonal if trying to capture, otherwise get length to be 1 or 2
		boolean val = isValidLoc(curr,next,board);
		boolean path = isPathClear(curr,next,board);
		promotionFlag = 0;


		if(isFirstMove(curr) && val && path && ((isUp(curr,next,board) && this.getColor() == 0) || (isDown(curr,next,board) && this.getColor() == 1))) {//can move two spaces
			int currRow = curr.getY();
			int nextRow = next.getY();

			if(isOneSpace(curr,next,board) && isDiag(curr,next,board) && oppInSpace(curr,next,board)) {
				if(isPromoting(curr,next,board)) {
					promotionFlag = 1;
				}
				return true; } //first move is a capture

			if(((nextRow==currRow-2 || nextRow==currRow-1) && this.getColor() == 0) || ((nextRow==currRow+2 || nextRow==currRow+1) && this.getColor() == 1)) {
				return true;
			}

			return false;
		}

		if(val && path && isOneSpace(curr,next,board)) { //now check if going up, or going diagonally to capture

			if(isDiag(curr,next,board) && oppInSpace(curr,next,board)) { //valid capture
				return true;
			}else if((isUp(curr,next,board) && this.getColor() == 0) || (isDown(curr,next,board) && this.getColor() == 1)) {return true;} //moving white up or black down
		}
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for pawn specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
}
