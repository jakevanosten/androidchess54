package com.example.jake.gamePiece;

import com.example.jake.boardData.*;

public class Rook extends GamePiece{

	public Rook(int wob) {
		super(wob);
	}

	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {
		if(isValidLoc(curr,next,board) && isPathClear(curr,next,board) && (isUp(curr,next,board) || isDown(curr,next,board) || isHoriz(curr,next,board))) {return true;}
		
		return false;
	}
	/*make method for Try to Move to see if its a legal move*/
	/*This will be the method that error checks for rook specific moves*/
	/*Also will be able to indicate if a move will result in a simple change in location or a kill */
	
	
	/*make method for setting the actual movement if Try to Move is valid*/
	

	
	
	
	
	
	
	/* subclass of abstract class gamePiece */	
	
	/* Rook Piece: the "castle"
	 * 
	 * Rook Movement: rook can move any # of spaces horizontal or vertical
	 * 
	 * Note: if an opponent's piece blocks the path, that piece may be captured
	 * by moving the rook to (but not beyond) the occupied square and
	 * removing the opponent's piece
	 * 
	 * 
	 * Note: Rooks cannot jump over pieces of either color.
	 * If one of your other pieces blocks your rook's path,
	 * your rook must stop before reaching that square
	 * 
	 */
	
}
