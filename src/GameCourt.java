/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeSet;

import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Square square; // the Black Square, keyboard control
	private Circle ball; // the Golden ball, bounces
	private int lives = 2;
	private brickArr allBricks; 

	public static boolean playing = false; // whether the game is running 
	private JLabel status; // Current status text, i.e. "Running..."

	private LinkedList<line> lines = new LinkedList<line>();
	private boolean mouseDown;
	private boolean spacebarPressed = false;
	int mouseX;
	int mouseY;
	int difficulty =2;
	boolean isGamePaused = false;

	// Game constants
	public static final int COURT_WIDTH = 640;
	public static final int COURT_HEIGHT = 500;
	public static final int SQUARE_VELOCITY = 8;

	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	public GameCourt(JLabel status) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		// The timer is an object which triggers an action periodically with the given INTERVAL. We
		// register an ActionListener with this timer, whose actionPerformed() method is called each
		// time the timer triggers. We define a helper method called tick() that actually does
		// everything that should be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key events are handled by its key listener.
		setFocusable(true);

		// This key listener allows the square to move as long as an arrow key is pressed, by
		// changing the square's velocity accordingly. (The tick method below actually moves the
		// square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					square.setVx(-SQUARE_VELOCITY);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					square.setVx(SQUARE_VELOCITY);
				} 
			}

			public void keyReleased(KeyEvent e) {
				square.setVx(0);
				square.setVy(0);
			}
		});
		
		//Checking is spacebar is pressed to launch
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					spacebarPressed = true;
				}
			}

		});
		
		//Checking if the mouse is pressed
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mouseDown = true;
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseDown = true;
				mouseX = e.getX();
				mouseY = e.getY();

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}

			@Override
			public void mouseEntered(MouseEvent e) {	
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}			
		});



		this.status = status;
	}

	/**
	 * (Re-)set the game to its initial state.
	 * @throws IOException 
	 */
	public void reset() throws IOException {
		lives = 2;
		//Creating ball and square
		square = new Square(COURT_WIDTH, COURT_HEIGHT, Color.BLACK);
		ball = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.YELLOW, square.getPx() + 7, 
				square.getPy() - 45, 0, 0);
		
		//If the game was paused, load the saved game
		if (isGamePaused) {
			allBricks = new brickArr("files/savedgame.txt", COURT_WIDTH, COURT_HEIGHT); 
		} else {

			String level = "";

			//Choosing level
			if (difficulty == 2) {
				level = "files/easy.txt";
			} else if (difficulty == 3) {
				level = "files/medium.txt";
			} else  {
				level = "files/hard.txt";
			} 

			allBricks = new brickArr(level, COURT_WIDTH, COURT_HEIGHT); 
		}

		//reset launch
		ball.resetLaunch();
		spacebarPressed = false;
		mouseDown = false;

		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor triggers.
	 */
	void tick() {
		if (playing) {
			
			//If the ball is not launched, creating lines until the spacebar launched the ball
			if (!ball.hasLaunched()) {
				if (mouseDown) {
					int x = mouseX;
					int y = mouseY;
					line temp = new line(square.getPx() + 32, square.getPy() - 19, x, y);
					lines.push(temp);
				}
				if (spacebarPressed) {
					ball.launch(mouseX, mouseY);
					lines.clear();
				}
				status.setText("Moust to aim and spacebar to launch!");
			} else {
				
				//Advances the ball and square
				square.move();
				ball.move();

				// make the ball bounce off walls and square
				ball.bounce(ball.hitWall());
				ball.bounce(ball.hitObj(square));

			

				// check for the game end conditions
				if (allBricks.isEmpty()) {
					playing = false;
					status.setText("You Win!");
				}
				
				//If the ball hits the ground, you lose a life/lose the game
				if (ball.hitGround()) {
					lives--;
					if (lives == 0) {
						status.setText("You Lose :(");
						playing = false;
					} else {
						ball.resetLaunch();
						ball = new Circle(COURT_WIDTH, COURT_HEIGHT, Color.YELLOW, square.getPx() + 7, 
								square.getPy() - 45, 0, 0);						
						spacebarPressed = false;
						mouseDown = false;
					}
				}
				
				//Checking if a brick intersects the ball
				for (int i = 0; i < allBricks.getLengthX(); i++) {
					for (int j = 0; j < allBricks.getLengthY(); j++) {
						if (allBricks.getBrick(i, j) != null) {
							brick temp = allBricks.getBrick(i, j);
							if (ball.intersects(temp)) {
								
								//Ball bounces off brick
								ball.bounce(ball.hitObj(temp));
								
								//Decreasing the hit and checking to remove the brick
								temp.decreaseHit();
								if (temp.isHitZero()) {
									allBricks.removeBrick(temp);
								}
							}
						}
					}
				}
				if (lives != 0) {
					status.setText("Lives remaining is: " + lives);
				}
			}
			// update the display
			repaint();
		}
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		square.draw(g);
		ball.draw(g);
		
		//Repaints the brickArr
		for (int i = 0; i < allBricks.getLengthX(); i++) {
			for (int j = 0; j < allBricks.getLengthY(); j++) {
				if (allBricks.getBrick(i, j) != null) {
					allBricks.getBrick(i, j).draw(g);
				}
			}
		}
		
		//Peek to draw the line at the front of  the linked list
		if (!lines.isEmpty()) {
			line temp = lines.peek();
			temp.draw(g);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
	
	//Updates mouse location
	public void updateMouse(MouseEvent e) {
		if (mouseDown) {
			mouseX 
			= e.getX();
			mouseY = e.getY();
		}
	}
	
	//Sets difficulty of the game
	public void setDifficulty(int d) {
		difficulty = d;
		try {
			reset();

			//Choosing difficulty
			String diff = "";

			if (difficulty == 2) {
				diff = "Easy";
			} else if (difficulty == 3) {
				diff = "Medium";
			} else {
				diff = "Hard";
			}
			JOptionPane.showMessageDialog(Game.frame, "Game difficulty set to: "
					+ diff, "Game", JOptionPane.PLAIN_MESSAGE);		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Pauses the game and saves it into a file
	public void pause() throws IOException {
		playing = false;
		isGamePaused = true;

		//Saves game
		allBricks.saveBricks();
		status.setText("Pause");
		JOptionPane.showMessageDialog(Game.frame, "Game Paused - Press \"Resume\" to "
				+ "continue the game.", "Game", JOptionPane.PLAIN_MESSAGE);		
	}
	
	//Resumes the game
	public void resume() throws IOException {
		playing = true;
		status.setText("Running...");
		isGamePaused = true;
		reset();
		isGamePaused = false;
		JOptionPane.showMessageDialog(Game.frame, "Game Resumed", "Game", JOptionPane.PLAIN_MESSAGE);	

	}
	
	//Check if game is paused
	public boolean getPause() {
		return isGamePaused;
	}

}
