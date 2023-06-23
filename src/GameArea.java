import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameArea extends JFrame {

	
	public GameArea() {
		JFrame frame = new JFrame();
		SnakeGame snakegame = new SnakeGame();
		frame.setBounds(10, 10, 905, 700);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(snakegame);
		frame.setVisible(true);
	}

}
