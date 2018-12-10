package gameBoard;

import chessPieces.*;
import java.util.Scanner;



public class Board{
	int row, col = 0;
	public static CellType[][] cells;
	
	
	
	
	public Board(){
		this.row = 9;
		this.col = 9;
		cells = new CellType[this.row][this.col];
	}
	
	
	
	
	
	public static int transRow(char num) {
		if(num == '1') {
			return 7;
		}else if (num == '2') {
			return 6;
		}else if (num == '3') {
			return 5;
		}else if (num == '4') {
			return 4;
		}else if (num == '5') {
			return 3;
		}else if (num == '6') {
			return 2;
		}else if (num == '7') {
			return 1;
		}else if (num == '8') {
			return 0;
		}
		
		return 69;
	}
	
	public static int transCol(char letter) {
		if(letter == 'a') {
			return 0;
		}else if (letter == 'b') {
			return 1;
		}else if (letter == 'c') {
			return 2;
		}else if (letter == 'd') {
			return 3;
		}else if (letter == 'e') {
			return 4;
		}else if (letter == 'f') {
			return 5;
		}else if (letter == 'g') {
			return 6;
		}else if (letter == 'h') {
			return 7 ;
		}
		
		return 69;
	}
	
	public void setBoard(Board b) {
		
		b.cells[0][0] = new Rook("bR", 1);
		b.cells[0][1] = new Knight("bN",1);
		b.cells[0][2] = new Bishop("bB",1);
		b.cells[0][3] = new Queen("bQ",1);
		b.cells[0][4] = new King("bK",1);
		b.cells[0][5] = new Bishop("bB",1);
		b.cells[0][6] = new Knight("bN",1);
		b.cells[0][7] = new Rook("bR",1);
		b.cells[0][8] = new Legend("8"); 
		
		b.cells[1][0] =  new Pawn("bp",1);
		b.cells[1][1] =  new Pawn("bp",1);
		b.cells[1][2] =  new Pawn("bp",1);
		b.cells[1][3] =  new Pawn("bp",1);
		b.cells[1][4] =  new Pawn("bp",1);
		b.cells[1][5] =  new Pawn("bp",1);
		b.cells[1][6] =  new Pawn("bp",1);
		b.cells[1][7] =  new Pawn("bp",1);
		b.cells[1][8] =  new Legend("7"); 
		
		b.cells[2][0] =  new BlankSpace("  ");
		b.cells[2][1] =  new BlankSpace("##");
		b.cells[2][2] =  new BlankSpace("  ");
		b.cells[2][3] =  new BlankSpace("##");
		b.cells[2][4] =  new BlankSpace("  ");
		b.cells[2][5] =  new BlankSpace("##");
		b.cells[2][6] =  new BlankSpace("  ");
		b.cells[2][7] =  new BlankSpace("##");
		b.cells[2][8] =  new Legend("6");
		
		b.cells[3][0] =  new BlankSpace("##");
		b.cells[3][1] =  new BlankSpace("  ");
		b.cells[3][2] =  new BlankSpace("##");
		b.cells[3][3] =  new BlankSpace("  ");
		b.cells[3][4] =  new BlankSpace("##");
		b.cells[3][5] =  new BlankSpace("  ");
		b.cells[3][6] =  new BlankSpace("##");
		b.cells[3][7] =  new BlankSpace("  ");
		b.cells[3][8] =  new Legend("5");
		
		b.cells[4][0] =  new BlankSpace("  ");
		b.cells[4][1] =  new BlankSpace("##");
		b.cells[4][2] =  new BlankSpace("  ");
		b.cells[4][3] =  new BlankSpace("##");
		b.cells[4][4] =  new BlankSpace("  ");
		b.cells[4][5] =  new BlankSpace("##");
		b.cells[4][6] =  new BlankSpace("  ");
		b.cells[4][7] =  new BlankSpace("##");
		b.cells[4][8] =  new Legend("4");
		
		b.cells[5][0] =  new BlankSpace("##");
		b.cells[5][1] =  new BlankSpace("  ");
		b.cells[5][2] =  new BlankSpace("##");
		b.cells[5][3] =  new BlankSpace("  ");
		b.cells[5][4] =  new BlankSpace("##");
		b.cells[5][5] =  new BlankSpace("  ");
		b.cells[5][6] =  new BlankSpace("##");
		b.cells[5][7] =  new BlankSpace("  ");
		b.cells[5][8] =  new Legend("3");
		
		b.cells[6][0] =  new Pawn("wp",0);
		b.cells[6][1] =  new Pawn("wp",0);
		b.cells[6][2] =  new Pawn("wp",0);
		b.cells[6][3] =  new Pawn("wp",0);
		b.cells[6][4] =  new Pawn("wp",0);
		b.cells[6][5] =  new Pawn("wp",0);
		b.cells[6][6] =  new Pawn("wp",0);
		b.cells[6][7] =  new Pawn("wp",0);
		b.cells[6][8] =  new Legend("2"); 
		
		b.cells[7][0] =  new Rook("wR",0);
		b.cells[7][1] =  new Knight("wN",0);
		b.cells[7][2] =  new Bishop("wB",0);
		b.cells[7][3] =  new Queen("wQ",0);
		b.cells[7][4] =  new King("wK",0);
		b.cells[7][5] =  new Bishop("wB",0);
		b.cells[7][6] =  new Knight("wN",0);
		b.cells[7][7] =  new Rook("wR",0);
		b.cells[7][8] =  new Legend("1"); 
		
		b.cells[8][0] =  new Legend("a");
		b.cells[8][1] =  new Legend("b");
		b.cells[8][2] =  new Legend("c");
		b.cells[8][3] =  new Legend("d");
		b.cells[8][4] =  new Legend("e");
		b.cells[8][5] =  new Legend("f");
		b.cells[8][6] =  new Legend("g");
		b.cells[8][7] =  new Legend("h");
		b.cells[8][8] =  new Legend(""); 
	}
	
	public void printBoard(Board b) {
		
		String cellTag = "";
		for (int i=0; i<b.row;i++) {
			for(int k=0;k<b.col;k++) {
				CellType cell = b.cells[i][k];
				
				if(cell instanceof Bishop || cell instanceof King || cell instanceof Knight || cell instanceof Pawn || cell instanceof Queen || cell instanceof Rook) {
					cellTag = ((GamePiece) b.cells[i][k]).getTag();
				}else if(cell instanceof BlankSpace) {
					cellTag = ((BlankSpace) b.cells[i][k]).getTag();
				}else if(cell instanceof Legend) {
					cellTag = ((Legend) b.cells[i][k]).getTag() + " ";
				}
				
				System.out.print(cellTag + " ");
			}
			System.out.println();
		}

	}

	
	public void getMove(Board b){
		
		
		Scanner reader = new Scanner(System.in);
		System.out.print("White Move: ");	
		String n = reader.nextLine();
		
		while(n.length() != 5 || (n.charAt(0) == n.charAt(3) && n.charAt(1) == n.charAt(4)) || (n.charAt(0) < 'a') || (n.charAt(0) > 'h') || (n.charAt(3) < 'a') || (n.charAt(3) > 'h') || (n.charAt(1) < '1') || (n.charAt(1) > '8') || (n.charAt(4) < '1') || (n.charAt(4) > '8')) {
			System.out.println("Illegal Move, try again.");
			System.out.print("White Move: ");
			n = reader.nextLine();
		}
		
		char originLetter = n.charAt(0);
		char originNumber = n.charAt(1);
		
		char destLetter = n.charAt(3);
		char destNumber = n.charAt(4);
		
		/* IF WE WANT TO PARSE BY origin and destination via token array we can 
		 * (just gonna run into issue separating letter from number so i thought charAt() was easier at the moment)
		 
		String delim = "[ ]+";
		String[] tokens = n.split(delim);
		
		for (int i = 0; i < tokens.length; i++)
		    System.out.println(tokens[i]);
		*/
		
		System.out.println("White Move: " + n);
		System.out.println("original location letter: " + originLetter + " original location number: " + originNumber);
		System.out.println("destination: " + destLetter + destNumber);
		
		
		
		//FIND CORRESPONDING ROW&COL for origin and destination
		//ISSUE NEEDING TO BE RESOLVED: doesn't equate to the correct result
		//char numerical value to integer might be the issue in conversion
		int originRow = transRow(originNumber);
		int originCol = transCol(originLetter);
		int destRow = transRow(destNumber);
		int destCol = transCol(destLetter);
		
		System.out.println("Origin Location: " + originLetter + originNumber + " Actual RxC coordinates: " + originRow + originCol);
		System.out.println("Destination: " + destLetter + destNumber + " Actual RxC coordinates: " + destRow + destCol);
		
		
		
		
		
		//This checks if this movement results in a CheckMate to end the game
		//If isCheckmate() is true, then we close the input stream and end the game
		boolean check = isCheckmate(b);
		if(check == true) reader.close();
		
	}
	
	public static boolean isCheckmate(Board b){
		
		//Scan board to see if in CheckMate position
		
		//if not in CheckMate
		return false;
		//else, return true!
	}
	
	
	public static void main(String[] args) {
		Board bo = new Board();
		bo.setBoard(bo); 
		bo.printBoard(bo);
		
	
		//NOTE: maybe a game loop until ischeckMate is true so that the game keeps getting moves and updating the board
			bo.getMove(bo);
		  //bo.updateBoard(bo);
		
		
		

	}

}