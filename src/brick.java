import java.awt.*;

public class brick extends GameObj {
	public static final int WIDTH = 80;
	public static final int HEIGHT = 30;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public int posX;
	public int posY;
	private int hits;
	private int indexX;
	private int indexY;


	private Color color;

	public brick(int courtWidth, int courtHeight, int hits, int xPos, int yPos, int i, int j) {
		super(INIT_VEL_X, INIT_VEL_Y, xPos, yPos, WIDTH, HEIGHT, courtWidth, courtHeight);
		this.hits = hits;
		if (hits == 1) {
			this.color = Color.BLUE;
		} else if (hits == 2) {
			this.color = Color.RED;
		} else if (hits == 3) {
			this.color = Color.MAGENTA;
		} else if (hits == 4) {
			this.color = Color.CYAN;
		}
		indexX = i;
		indexY = j;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
        g.setColor(this.color);
        g.fillRect(this.getPx(), this.getPy(), this.getWidth() - 2, this.getHeight() - 2);		
	}
	
	public int getHits() {
		return hits;
	}
	
	public void decreaseHit() {
		hits = hits - 1;
		if (hits == 1) {
			this.color = Color.BLUE;
		} else if (hits == 2) {
			this.color = Color.RED;
		} else if (hits == 3) {
			this.color = Color.MAGENTA;
		} else if (hits == 4) {
			this.color = Color.CYAN;
		}
	}
	
	public boolean isHitZero() {
		return hits == 0;
	}
	
	public int getX() {
		return indexX;
	}
	
	public int getY() {
		return indexY;
	}
}
