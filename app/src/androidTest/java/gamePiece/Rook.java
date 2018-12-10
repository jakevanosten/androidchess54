package gamePiece;

public class Rook extends GamePiece{

	public String rookMove;
	
	public Rook(String tag, int wob) {
		super(tag, wob);
	}
	
	public String getRookMove(){
		return rookMove;
	}
	
	public void setPawnMove(String tag){
		rookMove = tag;
	}
	
	public boolean tryMove(String curr, String next) {
		if(isValidLoc(curr,next) && isPathClear(curr,next) && (isUp(curr,next) || isDown(curr,next) || isHoriz(curr,next))) {return true;}
		
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
