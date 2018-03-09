import javax.swing.*;
import java.awt.*;

/**
 * @param Slot
 * Manages the piece image shown for buttons on the board.
 */

public class Slot extends JButton{
	private static final long serialVersionUID = 2544420096835091395L; // CHECK
	private boolean turn = false;
	
	public Slot () {
		super();
		setMargin(new Insets(12, 9, 0, 0));

	}
	
	public void setPlayer(boolean playerTurn) {
		turn = playerTurn;
		ImageIcon piece1 = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/YellowPiece.png");
		ImageIcon piece2 = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/RedPiece.png");
		if(!turn) {
			setIcon(piece1);
		} else {
			setIcon(piece2);
		}			
	}
	
}
