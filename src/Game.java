/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	static final JFrame frame = new JFrame("Brick Breaker");

	public void run() {
		// NOTE : recall that the 'final' keyword notes immutability even for local variables.

		// Top-level frame in which game components live
		// Be sure to change "TOP LEVEL FRAME" to the name of your game
		frame.setLocation(300, 300);

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel status = new JLabel("Running...");
		status_panel.add(status);

		// Main playing area
		final GameCourt court = new GameCourt(status);
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset button, we define it as an
		// anonymous inner class that is an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed, actionPerformed() will be called.
		final JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					court.reset();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		control_panel.add(reset);

		//Set to easy
		final JButton easy = new JButton("Easy");
		easy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setDifficulty(2);
			}
		});
		control_panel.add(easy);

		//Set to medium
		final JButton medium = new JButton("Medium");
		medium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setDifficulty(3);
			}
		});
		control_panel.add(medium);

		//Set to hard
		final JButton hard = new JButton("Hard");
		hard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.setDifficulty(4);
			}
		});
		control_panel.add(hard);
		
		//Pause button
		final JButton pause = new JButton("Pause");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					court.pause();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		control_panel.add(pause);
		
		//Resume button
		final JButton resume = new JButton("Resume");
		resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					try {
						court.resume();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			
		});
		control_panel.add(resume);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		try {
			court.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
	}

	/**
	 * Main method run to start and run the game. Initializes the GUI elements specified in Game and
	 * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}