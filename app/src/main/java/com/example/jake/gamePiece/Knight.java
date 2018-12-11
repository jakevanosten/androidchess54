package com.example.jake.gamePiece;

import com.example.jake.boardData.*;

public class Knight extends GamePiece{

	public Knight(int wob) {
		super(wob);
	}
	
	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {
		if(isValidLoc(curr,next,board) && isPathClear(curr,next,board) && isL(curr,next,board)) {return true;}
		
		return false;
	}
/* subclass of abstract class gamePiece */	
	
	/* Knight Piece: the "horse"
	 * 
	 * Knight Movement: the only pieces that can jump over other pieces
	 * (these are the only pieces that cannot be blocked)
	 * 
	 * Movement Specified: THE KNIGHT MOVES IN AN L SHAPED PATTERN,
	 * two spaces horizontally and one space vertically or...
	 * one space horizontally and two spaces vertically
	 * 
	 * 
	 * Note: a knight captures a piece only when it lands on that piece's square
	 * (the knight can "jump" over other pieces (of either color) and capture
	 * an opponent's piece if it finishes its move on that piece's square.
	 * 
	 * Note: a knight may not move to a square already occupied by a piece of the 
	 * knight's color
	 * 
	 */
	
}
