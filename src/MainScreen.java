import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 905, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		setResizable(false);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO SNAKE GAME");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(238, 11, 374, 54);
		contentPane.add(lblNewLabel);
		
		JLabel imgLbl = new JLabel("");
		imgLbl.setForeground(Color.GREEN);
		
		
		ImageIcon img = new ImageIcon("./images/main.jpg");
		imgLbl.setIcon(img);
		imgLbl.setBounds(10, 76, 871, 587);
		contentPane.add(imgLbl);
		
		JButton btn = new JButton("Start Game");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				GameArea run = new GameArea();
				run.setBackground(Color.DARK_GRAY);
			}
		});
		btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn.setBounds(676, 11, 180, 47);
		btn.setBackground(Color.GREEN);
		contentPane.add(btn);
	}
}
