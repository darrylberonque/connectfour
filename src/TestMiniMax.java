import java.util.ArrayList;

/**
 * @param TestMiniMax
 * Provides the best move for AI, given the board state.
 */

public class TestMiniMax {
	int boardState[][] = new int[6][7];
	BoardChecker boardChecker = new BoardChecker(boardState);
	ArrayList<Integer> depthOne = new ArrayList<Integer>();
	ArrayList<Integer> depthTwo = new ArrayList<Integer>();
	int maxDepth;
	int order[] = new int[7];
	int stateNum = 1;

	// Invert the boardState to see the board from our (ai's) perspective
	public TestMiniMax(int boardState[][], int maxDepth) {
		this.boardState = boardState;
		this.maxDepth = maxDepth;
		this.order[0] = 3;
		this.order[1] = 2;
		this.order[2] = 4;
		this.order[3] = 1;
		this.order[4] = 5;
		this.order[5] = 0;
		this.order[6] = 6;
	}
	
	// Handle finding open row 
	public ArrayList<Integer> getRows(int boardState[][]) {
		ArrayList<Integer> rows = new ArrayList<Integer>();
		// Iterate thorugh each column to find the "free" row. Invalid columns will return a -1. 
		for (int i = 0; i < 7; i++) {
			int r = 5;
			while(boardState[r][i] == 1 || boardState[r][i] == 2) {
				r--;
				
				if (r < 0) {
					break;
				}
			}
			rows.add(r);
		}
		
		return rows;
	}
	
	// Scoring method for given state and depth of current search
	public int boardScore(int boardState[][], int color, int depth) {
		int score = 0;
		if (color == 0) {
			score = 22 - depth;
		} else {
			score = -22 + depth;
		}
		
		return score;
	}
	
	public void printState(int[][] boardArray) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(boardArray[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	// Implementationn of min-max with negamax
	public int negaMaxScore(int boardState[][], int depth, int col, int color, int alpha, int beta) {
		int maxScore = - Integer.MAX_VALUE;	
		int maxScoreCol = -1;
		int[] colorSign = new int[2];
		colorSign[0] = 1;
		colorSign[1] = -1;
		ArrayList<Integer> rows = getRows(boardState);

		// Assign maximum score to a game state at current depth of search
		if (boardChecker.checkWinner(rows.get(col) + 1, col, boardState)) {
			maxScore = colorSign[1-color] * boardScore(boardState, color, depth);
		} else if(depth > maxDepth) {
			maxScore = 0;
		} else {
			for (Integer i: order) {
				if (rows.get(i) < 0) {
					continue;
				}
				boardState[rows.get(i)][i] = 1 + color;
				stateNum++;
				int score = -negaMaxScore(boardState, depth + 1, i, 1 - color, -beta, -alpha);
				if (score > maxScore) {
					maxScore = score;
					maxScoreCol = i;
				}
				if (score > alpha) {
					alpha = score;
				}
				if (alpha >= beta) {
					maxScore = alpha;
					boardState[rows.get(i)][i] = 0;
					break;
				}
				boardState[rows.get(i)][i] = 0;
			}
		}			

		if (depth == 0) {
			maxScore = maxScoreCol;
		}
		return maxScore;
	}
	    
}

