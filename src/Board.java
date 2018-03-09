import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.HashMap;

/**
 * @param Board
 * Sets up the board game GUI, allowing the user to drop the piece in the correct
 * row and column for the turn. Also holds the board state which is a 2D array that 
 * keeps track of currently occupied spots on the board. 
 */


public class Board {	

	private JLayeredPane boardLayers = new JLayeredPane();
	private boolean turn = false; // Which player's turn (false = p1, true = p2)
	private HashMap<JButton, Integer> columns = new HashMap<JButton, Integer>();

	private int boardArray[][] = new int[6][7];
	private ConnectFourGame frame;
	private BoardChecker boardChecker = new BoardChecker(boardArray);
	
	private TestMiniMax max;
	
	public Board(ConnectFourGame frame, int lvl) {
		this.max = new TestMiniMax(boardArray, lvl);
		
		this.frame = frame;
		boardLayers.setPreferredSize(new Dimension(722, 800));

		// Populate the board array
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				boardArray[i][j] = 0;
			}
		}
		
		/* Setting up the board GUI */
		// The first component for the layered pane1: board outline
		JLabel boardImage = new JLabel();
		boardImage.setBounds(0, 100, 750, 650);
		ImageIcon b = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/Connect4Board.png");
		boardImage.setIcon(b);
		
		// The second component for the layered pane1: board buttons
		JPanel board = new JPanel(new GridLayout(6, 7, 0, 0));
		for(int i = 0; i < 42; i++) {
			Slot btn = new Slot();
			btn.setOpaque(false); 
			btn.setBorderPainted(false);
			board.add(btn);
		}
		board.setBounds(0, 112, 702, 604);
		
		// The rest of the components for the layered panel: makes up the top panel
		int offset = 8;
		for(int i = 0; i < 7; i++) {
			// Initialize the current button and save it's index
			JButton drop = new JButton();
			columns.put(drop, i);
			drop.setOpaque(false);
			drop.setBorderPainted(false);
			
			// Setting up the piece icon for the buttons on the board 
			ImageIcon pieceImage1 = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/YellowPiece.png");
			ImageIcon pieceImage2 = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/RedPiece.png");
			JLabel dropImage = new JLabel(); 
			dropImage.setBorder(new EmptyBorder(10, 0, 0, 0));
			
			
			drop.add(dropImage);
			drop.setBounds(offset, 0, 100, 114);
			drop.addMouseListener(new MouseAdapter() {
				
				// Shows the piece color to drop for the current player
				public void mouseEntered(MouseEvent me) {
					if(!turn) {
						dropImage.setIcon(pieceImage1);
					} else {
						dropImage.setIcon(pieceImage2);
					}
				}
				
				// Piece dissapears as mouse exits component
				public void mouseExited(MouseEvent me) {
					dropImage.setIcon(null);
				}
				
				// Handles the piece updating on gameBoard gui and updating boardArray
				public void mousePressed(MouseEvent me) {
					updateGameState(columns.get(drop), board);
				}
				
				// Handling for ai
				public void mouseReleased(MouseEvent me) {
						if(!turn) {
							dropImage.setIcon(pieceImage1); 
						} else {
							updateGameState(max.negaMaxScore(boardArray, 0, columns.get(drop), 1, -Integer.MAX_VALUE, Integer.MAX_VALUE), board); // If ai is second player
							dropImage.setIcon(pieceImage1); 
						}					
				}
			}); 
			
			boardLayers.add(drop, new Integer(1));
			offset += 100;
		}
		
		
		
		// Adding the layers to jlayered pane
		boardLayers.add(boardImage, new Integer(1));	
		boardLayers.add(board, new Integer(0));
		
		System.out.println("Initial State: ");
		System.out.println();
		printState();
	}
	
	public JLayeredPane getGameBoard() {
		return boardLayers;
	} 
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean getTurn(boolean turn) {
		return turn;
	}
	
	public void updateGameState(int col, JPanel board) {
		Slot curr = new Slot();
	
		int row = 5;
		while(boardArray[row][col] == 1 || boardArray[row][col] == 2) {
			row--;
			
			if(row < 0) {
				break;
			}
		}

		if(row >= 0) {			
			curr = (Slot) board.getComponent(row * 7 + col);
			curr.setPlayer(turn);
			if(!turn) {
				boardArray[row][col] = 1;
			} else {
				boardArray[row][col] = 2;
			}
			turn = !turn;
			if (boardChecker.checkWinner(row, col, boardArray)) {
				JOptionPane.showMessageDialog(frame, "Winner!!!");
			}
		} else {
			JOptionPane.showMessageDialog(frame, "Invalid Move: Please make a new move");
		}
		
		printState();

	}
	
	public int[][] getBoardState() {
		return boardArray;
	}
	
	public void printState() {
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
}
