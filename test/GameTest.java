import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.io.IOException;

import org.junit.Test;

public class GameTest {
	
	@Test
	public void testBrickMapEmpty() throws IOException{
		int[][] test = new int[8][8];
		brickArr arr = new brickArr(test, 400, 400);
		boolean tf = arr.isEmpty();
		assertTrue(tf);		
	}
	
	@Test
	public void testBrickMapNotEmpty() throws IOException{
		int[][] test = new int[8][8];
		test[4][4] = 2;
		brickArr arr = new brickArr(test, 400, 400);
		boolean tf = arr.isEmpty();
		assertFalse(tf);		
	}
	
	@Test
	public void testDecreaseHit() throws IOException{
		int[][] test = new int[8][8];
		test[4][4] = 2;
		brickArr arr = new brickArr(test, 400, 400);
		brick b = arr.getBrick(4, 4);
		b.decreaseHit();
		int h = b.getHits();
		assertEquals(1, h);
		b.decreaseHit();
		assertTrue(b.isHitZero());
		}
	
	@Test
	public void testRemoveBrick() throws IOException{
		int[][] test = new int[8][8];
		test[4][4] = 2;
		brickArr arr = new brickArr(test, 400, 400);
		brick b = arr.getBrick(4, 4);
		int h = b.getHits();
		assertEquals(2, h);
		arr.removeBrick(b);;
		assertTrue(arr.isEmpty());
		}
	
	@Test
	public void testBallLaunchSmallX() throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 300, 450, 0, 0);
		ball.launch(300, 200);
		assertEquals(1, ball.getVx());
		}
	
	@Test
	public void testBallLaunchSmallY() throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 300, 450, 0, 0);
		ball.launch(200, 450);
		assertEquals(1, ball.getVy());
		}
	
	@Test
	public void testHitGroundYes() throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 300, 500, 0, 0);
		assertTrue(ball.hitGround());
		}
	
	@Test
	public void testHitGroundNo()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 300, 450, 0, 0);
		assertTrue(ball.hitGround());
		}
	
	@Test
	public void testHitWallUp()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 300, 3, 0, -4);
		assertEquals(Direction.UP, ball.hitWall());
		}
	
	@Test
	public void testHitWallRight()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 398, 490, 3, 11);
		assertEquals(Direction.RIGHT, ball.hitWall());
	}
	
	@Test
	public void testHitWallLeft()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 0, 490, -3, 11);
		assertEquals(Direction.LEFT, ball.hitWall());
	}
	
	@Test
	public void testWillIntersect()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 398, 490, 0, -11);
		Circle ball2 = new Circle(400, 400, Color.black, 398, 470, 3, 11);
		assertTrue(ball.willIntersect(ball2));
	}
	
	@Test
	public void testWillNotIntersect()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 398, 490, 0, -11);
		Circle ball2 = new Circle(400, 400, Color.black, 398, 370, 3, 11);
		assertFalse(ball.willIntersect(ball2));
	}
	
	@Test
	public void testIntersects()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 398, 490, 0, 0);
		Circle ball2 = new Circle(400, 400, Color.black, 377, 460, 0, 0);
		assertTrue(ball.intersects(ball2));
	}
	
	@Test
	public void testDoesNotIntersect()throws IOException{
		Circle ball = new Circle(400, 400, Color.black, 398, 490, 0, 0);
		Circle ball2 = new Circle(400, 400, Color.black, 277, 260, 0, 0);
		assertFalse(ball.intersects(ball2));
	}
}


