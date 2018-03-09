
/**
* @param Board
* Sets up the board game GUI, allowing the user to drop the piece in the correct
* row and column for the turn. Also holds the board state which is a 2D array that 
* keeps track of currently occupied spots on the board. 
*/


public class BoardChecker {	
	
	int boardArray[][];

	public BoardChecker(int boardState[][]) {
		boardArray = boardState;
	}
	
	public boolean hChecker(int row, int column) {
		boolean check = false;
		int hcheck = 3;
		int hcounter = 0;
		while(column > 0 && hcheck > 0) {
			column--;
			hcheck--;
		}
		
		for(int i = column; i < 6; i++) {
			if(boardArray[row][i] == boardArray[row][i+1] && boardArray[row][i] != 0 && boardArray[row][i+1] != 0) {
				hcounter++;
			} else {
				hcounter = 0;
			}
				
			if (hcounter == 3) {
				//System.out.println("Winner!! - Horizontal");
				check = true;
				break;
			}
			
		}
		return check;
	}
	
	
	public boolean vChecker(int row, int column) {
		boolean check = false;
		int vcheck = 3;
		int vcounter = 0;
		while(row < 5 && vcheck > 0) {
			row++;
			vcheck--;
		}

		for(int i = row; i > 0; i--) {
			if(boardArray[i][column] == boardArray[i-1][column] && boardArray[i][column] != 0 && boardArray[i-1][column] != 0) {
				vcounter++;
			} else {
				vcounter = 0;
			}
			
			if (vcounter == 3) {
				//System.out.println("Winner!! - Vertical");
				check = true;
				break;
			}
			
		}
		return check;
	}

	public boolean lrdChecker(int row, int column) {
		boolean check = false;
		int hcheck = 3;
		int vcheck = 3;
		int lrdcounter = 0;

		
		while((column > 0 && hcheck > 0) && (row < 5 && vcheck > 0)) {
			column--;
			row++;
			vcheck--;
			hcheck--;
		}

		int j = row;
		for(int i = column; i + 1 < 6; i++) {			
			if(j > 0) {
				if(boardArray[j][i] == boardArray[j-1][i+1] && boardArray[j][i] != 0 && boardArray[j-1][i+1] != 0) {
					lrdcounter++;
				} else {
					lrdcounter = 0;
				}
				
				if (lrdcounter == 3) {
					// System.out.println("Winner!! - Left-Right Diagonal");
					check = true;
					break;
				}
			}
		
			j--;
		}
		return check;
	}
	
	public boolean rldChecker(int row, int column) {
		boolean check = false;
		int hcheck = 3;
		int vcheck = 3;
		int rldcounter = 0;

		
		while((column < 6 && hcheck > 0) && (row < 5 && vcheck > 0)) {
			column++;
			row++;
			vcheck--;
			hcheck--;
		}

		int j = row;
		for(int i = column; i - 1 >= 0; i--) {			
			if(j > 0) {
				
				if(boardArray[j][i] == boardArray[j-1][i-1] && boardArray[j][i] != 0 && boardArray[j-1][i-1] != 0) {
					rldcounter++;
				} else {
					rldcounter = 0;
				}

				if (rldcounter == 3) {
					//System.out.println("Winner!! - Right-Left Diagonal");
					check = true;
					break;
				}
			}
		
			j--;
		}
		return check;
	}
	
	public boolean checkWinner(int row, int column, int boardState[][]) {
		this.boardArray = boardState;
		boolean winner = hChecker(row, column) || vChecker(row, column) || 
						 lrdChecker(row, column) || rldChecker(row, column);
		
		return winner;
	}
}
