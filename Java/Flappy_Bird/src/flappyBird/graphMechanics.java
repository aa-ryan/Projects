package src.flappyBird;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class graphMechanics implements MouseListener, KeyListener {

	public final static int WIDTH = 1200, HEIGHT = 800;

	public static Rectangle bird;

	public static boolean gameOver, started;

	public static int yMotion, score;

	public static ArrayList<Rectangle> columns;

	public Random rand;

	public void jump() {

		if (gameOver) {

			bird = new Rectangle(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20);
			columns.clear();
			yMotion = 0;
			score = 0;

			columns = new ArrayList<Rectangle>();
			
			addColumn(true);
			addColumn(true);
			addColumn(true);
			addColumn(true);

			gameOver = false;

		} 

		if (!started) {

			started = true;
		} 
		else if (!gameOver) {
			if (yMotion > 0)
				yMotion = 0;
			else 
				yMotion -= 10;
		}
	}

	public void addColumn(boolean start) {

		int space = 300;
		int width = 100;
		int height = 50 + rand.nextInt(300);

		if (start) {
			
		columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
		columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) *300, 0, width, HEIGHT - height - space));

		} else {

		columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
		columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));

		}
	}

		@Override
		public void mouseClicked(MouseEvent e) {
			jump();
		}

		@Override
		public void mousePressed(MouseEvent e){}
		@Override
		public void mouseEntered(MouseEvent e){}
		@Override
		public void mouseExited(MouseEvent e){}
		@Override
		public void mouseReleased(MouseEvent e){}
		@Override
		public void keyTyped(KeyEvent e){}
		@Override
		public void keyPressed(KeyEvent e){
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
				jump();
		}
		@Override
		public void keyReleased(KeyEvent e){}
}
