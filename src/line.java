import java.awt.Color;
import java.awt.Graphics;

public class line {
	
	private int startX;
	private int startY;
	private int endX;
	private int endY;	
	public line (int sx, int sy, int ex, int ey) {
		startX = sx;		startX = sx;
		startY = sy;
		endX = ex;
		endY = ey;

	}
	
	public int startXGetter() {
		return this.startX;
	}
	
	public int startYGetter() {
		return this.startY;
	}
	
	public int endXGetter() {
		return this.endX;
	}
	
	public int endYGetter() {
		return this.endY;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(startX, startY, endX, endY);
	}
}
