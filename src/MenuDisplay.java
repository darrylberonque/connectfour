import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * @param MenuDisplay
 * Extends JPanel to manage components to be shown in menu GUI
 */

public class MenuDisplay extends JPanel{

	
	public MenuDisplay(JButton jb, JComboBox cb) {
		setLayout(null);
		add(jb);
		add(cb);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon("/Users/Darryl/Documents/workspace/ConnectFour/src/connect-4.jpg").getImage();
        g.drawImage(image, 0, 0, 722, 800, this);
	}
}
