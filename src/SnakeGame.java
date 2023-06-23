import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakeGame extends JPanel implements KeyListener, ActionListener{
	
	private int steps=0;
	
	private int nut = 0;  

	private boolean right = false, left = false, up = false, down = false;
	private boolean gameOver = false;

	private ImageIcon rightPart, upPart, downPart, leftPart, behindPart;
	private ImageIcon coinImage;  // image in the place of apple
	private ImageIcon header;


	private int snakeLength = 3; //total 3 parts are shown by default whenever game is started one is sheep and other two are rings will increase when length will increase of snake

	private Timer time;
	private int wait = 100;       //time to delay the movement
	
	private int[] xLength = new int[750];  // X axis for the game play area or game pad area 
	private int[] yLength = new int[750];  //Y axis  for the game play area or game pad area 
	
	private int coinXPosition[]= {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
			425, 450, 475, 500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800
			};  // positions of x axis where coin can exist
	
	private int coinYPosition[]= {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
			425, 450, 475, 500, 525, 550};   // positions of y axis where coin can exist
	
	
	private Random random = new Random();
	
	private int xRand= random.nextInt(32);   // using random to place coin on random places in the game area
	private int yRand= random.nextInt(20);
	
	
	
		

	public SnakeGame() {        //default constructor
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(wait, this);
		time.start();
	}

	public void paint(Graphics graphics) {

		if(steps == 0) {
			xLength[2]= 50;   //showing ring in this index
			xLength[1]= 75;   // showing ring
			xLength[0]= 100;  // showing sheep


			yLength[2]= 100;  //showing ring in this index
			yLength[1]= 100;   //showing ring
			yLength[0]= 100;   // showing sheep
			steps++;
		}

		graphics.setColor(Color.WHITE);       //Creating border of the header image
		graphics.drawRect(24, 10, 905, 55);

		
		header = new ImageIcon("./images/header.jpeg");
		header.paintIcon(this, graphics, 25, 11);

		
		graphics.setColor(Color.WHITE);             //Specifying game play area
		graphics.drawRect(24, 74, 905, 577);

		graphics.setColor(Color.BLACK);
		graphics.fillRect(25, 75, 904, 575);
		
		graphics.setColor(Color.BLACK);
		graphics.setFont(new Font("arial", Font.CENTER_BASELINE, 14));
		graphics.drawString("Nut Number: "+ nut, 100, 40);      // show nut num on the top
		graphics.drawString("Press Esc to Exit Game ", 550, 40); 
		
			
		
		rightPart = new ImageIcon("./images/right.png");     // default image of sheep in place of snake to start the game
		rightPart.paintIcon(this, graphics, xLength[0], yLength[0]);

		for(int i=0; i<snakeLength; i++) {

			if(i==0 && up) {                              // using image according to the conditions
				upPart = new ImageIcon("./images/right.png");
				upPart.paintIcon(this, graphics, xLength[i], yLength[i]);
			}

			if(i==0 && down) {
				downPart = new ImageIcon("./images/right.png");
				downPart.paintIcon(this, graphics, xLength[i], yLength[i]);
			}

			if(i==0 && left) {
				leftPart = new ImageIcon("./images/right.png");
				leftPart.paintIcon(this, graphics, xLength[i], yLength[i]);
			}

			if(i==0 && right) {
				rightPart = new ImageIcon("./images/right.png");
				rightPart.paintIcon(this, graphics, xLength[i], yLength[i]);
			}

			if(i!=0) {
				behindPart = new ImageIcon("./images/cir.png");
				behindPart.paintIcon(this, graphics, xLength[i], yLength[i]);
			}
		}
		
		coinImage = new ImageIcon("./images/coin.png");
		
		if(coinXPosition[xRand] == xLength[0] && coinYPosition[yRand] == yLength[0]) {    // Checking collision of head with coin
			snakeLength++;   // increment length of snake if snake eats the nut
			nut++;           // increment the nut num if nut is eaten.
			xRand = random.nextInt(32);
			yRand = random.nextInt(20);
		}
		
		coinImage.paintIcon(this, graphics, coinXPosition[xRand], coinYPosition[yRand]);
		
				
		for(int i=1; i<snakeLength; i++) {               // Game Over
			if(xLength[i] == xLength[0] && yLength[i] == yLength[0]) {
				time.stop();
				right= false;
				left = false;
				up = false;
				down = false;
				
				gameOver = true;
				
				graphics.setColor(Color.WHITE);
				graphics.setFont(new Font("arial", Font.BOLD, 50));
				graphics.drawString("Game Over", 300, 300);
				
				graphics.setFont(new Font("arial", Font.BOLD, 20));
				graphics.drawString("Enter to ReStart", 350, 340);
				
				graphics.setFont(new Font("arial", Font.BOLD, 20));
				graphics.drawString("Esc to Exit", 350, 380);
			}
		}
		
		
		graphics.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		
		if(right) {

			for(int right = snakeLength-1; right>= 0; right-- ) {
				yLength[right+1] = yLength[right];
			}

			for(int right = snakeLength; right >=0; right--) {

				if(right == 0) {
					xLength[right]= xLength[right]+ 25;
				}
				else {
					xLength[right]= xLength[right-1];
				}
				if(xLength[right]>800) {
					xLength[right]= 25;
				}
			}
			repaint();
		}
		
		if(left) {

			for(int right = snakeLength-1; right>= 0; right-- ) {
				yLength[right+1] = yLength[right];
			}

			for(int right = snakeLength; right >=0; right--) {

				if(right == 0) {
					xLength[right]-= 25;
				}
				else {
					xLength[right]= xLength[right-1];
				}
				if(xLength[right]<25) {
					xLength[right]= 850;
				}
			}
			repaint();
			
		}

		if(up) {
			for(int right = snakeLength-1; right>= 0; right-- ) {
				xLength[right+1] = xLength[right];
			}

			for(int right = snakeLength; right >=0; right--) {

				if(right == 0) {
					yLength[right]-= 25;
				}
				else {
					yLength[right]= yLength[right-1];
				}
				if(yLength[right]<75) {
					yLength[right]= 625;
				}
			}
			repaint();
		}

		if(down) {

			for(int right = snakeLength-1; right>= 0; right-- ) {
				xLength[right+1] = xLength[right];
			}

			for(int right = snakeLength; right >=0; right--) {

				if(right == 0) {
					yLength[right]+= 25;
				}
				else {
					yLength[right]= yLength[right-1];
				}
				if(yLength[right]>625) {
					yLength[right]= 75;
				}
			}
			repaint();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {           // Perform actions as per Keys are pressed
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			steps = 0;
			snakeLength=3;
			nut = 0;
			time.start();
			repaint();
		}

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {

			steps++;
			
			right = true;
			
			if(!left) {
			
				right = true;
			
			}
			else {
				
				right = false;
				left = true;

			}
			up = false;
			down = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT) {

			steps++;
			
			left = true;

			if(!right) {
				
				left = true;
			
			}
			
			else {
	
				left = false;

				right = true;
			
			}
			up = false;
			down = false;
		}

		if(e.getKeyCode() == KeyEvent.VK_UP) {

			steps++;
			
			up = true;

			if(!down) {
				up = true;
			}
			
			else {

				up = false;

				down = true;
			}
			left = false;
			right = false;
		}


		if(e.getKeyCode() == KeyEvent.VK_DOWN) {

			steps++;
			down = true;

			if(!up) {
				down = true;
			}
			else {
				up = true;

				down = false;
			}
			left = false;
			right = false;
		}
		
		

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
