package gamePiece;

import boardData.BoardIndex;
import boardData.Locations;

public class GamePiece{


	int whiteOrBlack;
	
	public GamePiece(int whiteOrBlack) {
		this.whiteOrBlack = whiteOrBlack;
	}
	
	public int getColor() {
		return whiteOrBlack;
	}
	
	public boolean tryMove(Locations curr, Locations next, BoardIndex[][] board) {return false;}; //going to be overridden by each piece since they have different standards for moving
	
	/*
	 * DIFFERENT RULES
	 * 	DONE - 1. IsValidLoc - If space that youre trying to move to is blocked by your team (if opponent is there you capture them)
	 *  DONE - 2. IsPathClear - checks if all spaces in path to destination are not blocked by other pieces (does not apply to knights)
	 *  
	 *  DONE 3. IsUp - checks if move is in a straight forward direction (for Pawns, Rooks, Queens, Kings)
	 *  DONE 4. IsDown - checks if move is in a backwards direction (for Rooks, Queens, Kings)
	 *  DONE 5. IsHoriz - checks if move is to the left or right of current position (for Rooks, Queens, Kings)
	 *  DONE 6. IsDiag - checks if move is diagonal from current position (for Bishops, Queens, Kings/pawns when capturing - needs to be opponent there)
	 *  DONE 7. IsL - checks if in L from current position (for Knights)
	 *  
	 *  DONE - in Pawn.java 8. IsFirstMove - checks if they are in the beginning row which would make the two space jump possible (for Pawns)
	 *  
	 *  9. IsOneSpace - move length restriction for pawns and kings
	 */
	
	public boolean isOneSpace(Locations curr, Locations next,BoardIndex[][] board) {
		return false;
	}
	
	public boolean isValidLoc(Locations curr, Locations next, BoardIndex[][] board) {
		if(board[curr.getX()][curr.getY()] == null){
			return false;
		}

		GamePiece curCell = board[curr.getX()][curr.getY()].getPiece();

		int currColor = curCell.whiteOrBlack;

		if(board[next.getX()][next.getY()] == null){
			return true;
		}
		GamePiece nxtCell = board[next.getX()][next.getY()].getPiece();

			if (nxtCell.whiteOrBlack == currColor) { //same color in next, can't move
				return false;
			}

		return true;
	}
	
	public boolean isPathClear(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = next.getX();
		int nextRow = curr.getY();
		int nextCol = next.getX();
		
		/*DONE -if only row changes, move along the columns. 
		 *DONE - if only col changes, move along the row.
		 * DONE - if row and col changed by same amount, move along diagonal.
		 * otherwise, must be a knight, return true since they can jump over pieces
		 */
		if(currCol == nextCol && currRow != nextRow && currRow < nextRow) { //check each row on path - moving down the board
			for(int i=currRow+1;i<=nextRow;i++) {
				if(board[i][currCol] != null) { //piece in the way
					return false;
				}
			}
			return true;
		}else if (currCol == nextCol && currRow != nextRow && currRow > nextRow) { //moving up
			for(int i=currRow-1;i>=nextRow;i--) {
				if(board[i][currCol] != null) { //piece in the way
					return false;
				}
			}
			return true;
		}else if (currCol != nextCol && currRow == nextRow && currCol < nextCol) { //moving right
			for(int i=currCol+1;i<=nextCol;i++) {
				if(board[currRow][i] != null) { //piece in the way
					return false;
				}
			}
			return true;
		}else if (currCol != nextCol && currRow == nextRow && currCol > nextCol) { //moving left
			for(int i=currCol-1;i>=nextCol;i--) {
				if(board[currRow][i] != null) { //piece in the way
					return false;
				}
			}
			return true;
		}else if (nextCol-currCol==nextRow-currRow) { //moving diagonally
			int k = currRow;
			if(nextCol>currCol && nextRow>currRow) {//down and right
				k = currRow+1;
				for(int i=currCol+1;i<=nextCol;i++) {
					if(board[k][i] != null) { //piece in the way
						return false;
					}
					k++;
				}
				return true;
			}else if(nextCol>currCol && nextRow<currRow) {//up and right
				k = currRow-1;
				for(int i=currCol+1;i<=nextCol;i++) {
					if(board[k][i] != null) { //piece in the way
						return false;
					}
					k--;
				}
				return true;
			}else if(nextCol<currCol && nextRow>currRow) {//down and left
				k = currRow+1;
				for(int i=currCol-1;i>=nextCol;i--) {
					if(board[k][i] != null) { //piece in the way
						return false;
					}
					k++;
				}
				return true;
			}else { //up and left
				k = currRow-1;
				for(int i=currCol-1;i>=nextCol;i--) {
					if(board[k][i] != null) { //piece in the way
						return false;
					}
					k--;
				}
				return true;
			}
		}
		return true;
	}
	
	public boolean isUp(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();
		int nextRow = next.getY();
		int nextCol = next.getX();
		
		if (currCol == nextCol && currRow != nextRow && currRow > nextRow) { //moving up
			return true;
		}
		return false;
	}
	
	public boolean isDown(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();
		int nextRow = next.getY();
		int nextCol = next.getX();
		
		if (currCol == nextCol && currRow != nextRow && currRow < nextRow){ //moving up
			return true;
		}
		return false;
	}
	
	public boolean isHoriz(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();
		int nextRow = next.getY();
		int nextCol = next.getX();
		
		if (currCol != nextCol && currRow == nextRow) {
			return true;
		}
		return false;
	}
	
	public boolean isDiag(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();
		int nextRow = next.getY();
		int nextCol = next.getX();
		
		if (nextCol-currCol==nextRow-currRow){
			return true;
		}
		return false;
	}
	
	public boolean isL(Locations curr, Locations next,BoardIndex[][] board) {
		int currRow = curr.getY();
		int currCol = curr.getX();
		int nextRow = next.getY();
		int nextCol = next.getX();
		
		if ((nextCol-currCol==2 || currCol-nextCol==2) && (nextRow-currRow==1 || currRow-nextRow==1)) { //left or right 2 and up or down 1
			return true;
		}else if ((nextRow-currRow==2 || currRow-nextRow==2) && (nextCol-currCol==1 || currCol-nextCol==1)) { //up or down 2 and left or right 1
			return true;
		}
		return false;
	} 
	
}
