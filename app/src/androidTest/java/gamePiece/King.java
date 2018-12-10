package gamePiece;

public class King extends GamePiece{

	public String kingMove;
	
	public King(String tag, int wob) {
		super(tag, wob);
	}
	
	public String getKingMove(){
		return kingMove;
	}
	
	public void setKingMove(String tag){
		kingMove = tag;
	}
	
	public boolean tryMove(String curr, String next) {
		if(isValidLoc(curr,next) && isPathClear(curr,next) && isOneSpace(curr,next) && (isUp(curr,next) || isDown(curr,next) || isHoriz(curr,next) || isDiag(curr,next))) {
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
