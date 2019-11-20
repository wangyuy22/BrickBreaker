/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.awt.*;

/**
 * A basic game object starting in the upper left corner of the game court. It is displayed as a
 * circle of a specified color.
 */
public class Circle extends GameObj {
	public static final int SIZE = 45;
	private boolean launched = false;



	private Color color;

	public Circle(int courtWidth, int courtHeight, Color color, int posX, int posY, int velX, int velY) {
		super(velX, velY, posX, posY, SIZE, SIZE, courtWidth, courtHeight);
		this.color = color;
	}

	public boolean hasLaunched() {
		return this.launched;
	}
	
	//Checks if the circle is launched
	public void launch(int x, int y) {
		
		//Creating triangle relative to mouse position and ball location
		double newY = Math.abs(y - this.getPy());
		double newX = Math.abs(x - this.getPx());
		
		//Calculates angle
		double angle = Math.atan(newY / newX);
		
		//Calculates the x and y velocities realtive to angle
		int xVel = (int) (Math.cos(angle) * 8);
		int yVel = (int) (Math.sin(angle) * 8);
		
		
		//Ensures the the velocities don't round down to 0, as velocity is an int
		if (Math.abs(yVel) < 1) {
			yVel = 1;
		}
		if (Math.abs(xVel) < 1) {
			xVel = 1;
		}

		//x velocity
		if (x > this.getPx()) {
			this.setVx(xVel); 
		} else if (x < this.getPx()){
			this.setVx(-1 * xVel); 
		} else {
			this.setVx(1); 
		}

		//y velocity
		if (y > this.getPy()) {
			this.setVy(yVel); 
		} else if (y < this.getPy()){
			this.setVy(-1 * yVel); 
		} else {
			this.setVy(1); 
		}
		this.launched = true;


	}
	//When reset is clicked velocity is 0
	public void resetLaunch() {
		launched = false;
		setVx(0);
		setVy(0);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
	}
}