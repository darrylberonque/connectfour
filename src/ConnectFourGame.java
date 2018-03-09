import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



/**
 * @param ConnectFourGame
 * Sets up the visual for the interactive Connect 4 main menu.
 *
 */


public class ConnectFourGame extends JFrame{
	
	private static final long serialVersionUID = -2768103730921002005L;
	private static Integer level = 1;
	
	public ConnectFourGame(String title) {
		super(title);
		setSize(722, 800);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static void updateLevel(Integer lvl) {
		level = lvl;
	}
	
	public static void main(String[] args) {
		// Window JFrame for all the components
		ConnectFourGame frame  = new ConnectFourGame("Connect Four");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		
		// Other Window for starting the game (New Game) & (Game Level)
		ConnectFourGame window = new ConnectFourGame("Connect Four");
		window.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		

		// Setup level selection
		Integer[] levels = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		JComboBox<Integer> chooseLevel = new JComboBox<Integer>(levels);
		chooseLevel.setSelectedIndex(0);
		chooseLevel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<Integer> lvl = (JComboBox<Integer>) e.getSource();
				Integer chosen = (Integer) lvl.getSelectedItem();
				updateLevel(chosen);
			}
			
		});
		chooseLevel.setBounds(321,550,80,25);

		// Setup new game 
		JButton btn = new JButton("New Game");
		btn.addMouseListener(new MouseAdapter() {
			
			// Open the board game with selected level
			public void mouseClicked(MouseEvent me) {
				System.out.println(level);
				Board gameBoard = new Board(frame, level);
				frame.add(gameBoard.getGameBoard());
				window.setVisible(false);
				frame.setVisible(true);
			}
		});
		btn.setBounds(300,500,125,35);
		
		
		MenuDisplay display = new MenuDisplay(btn, chooseLevel);
        window.add(display);
        window.setVisible(true);        
		frame.setVisible(false);
	}

}
