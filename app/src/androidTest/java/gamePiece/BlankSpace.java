package gamePiece;

public class BlankSpace extends CellType{
	int isBlack = 0;
	
	public BlankSpace(String tag) {
		super(tag);
		if(tag == "  ") {
			this.isBlack = 0;
		}else if (tag == "##"){
			this.isBlack = 1;
		}
	}
		
}
