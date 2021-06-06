package src.flappyBird;
import render.*;

import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class FlappyBird extends graphMechanics implements ActionListener {

	public static FlappyBird flappyBird;   // static instance of FlappyBird
	public Renderer renderer;

	public static int ticks;


	public FlappyBird() {

		JFrame jframe = new JFrame();
		Timer timer = new Timer(20, this);

		renderer = new Renderer();
		rand = new Random();


		jframe.add(renderer);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(WIDTH, HEIGHT);
		jframe.addMouseListener(this);
		jframe.addKeyListener(this);
		jframe.setResizable(false);
		jframe.setVisible(true);
		jframe.setTitle("Flappy Bird");

		bird = new Rectangle(WIDTH/2 - 10, HEIGHT/2 - 10, 20, 20);

		columns = new ArrayList<Rectangle>();
		
		addColumn(true);
		addColumn(true);
		addColumn(true);
		addColumn(true);

		timer.start();

	}


	public static void paintColumn(Graphics g, Rectangle column) {

		g.setColor(Color.green.darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int speed = 10;
		ticks += 1;

		if (started) {

			for (int i = 0; i < columns.size(); i++) {

				Rectangle column = columns.get(i);
				column.x -= speed;
			}

			if (ticks % 2 == 0 && yMotion < 15) {

				yMotion += 2;
			}

			for (int i = 0; i < columns.size(); i++) {

				Rectangle column = columns.get(i);

				if (column.x + column.width < 0) {
					columns.remove(column);

					if (column.y == 0) {

						addColumn(false);
					}
				}

			}

			bird.y += yMotion;

			for (Rectangle column: columns) {

				if (column.y == 0 && bird.x + bird.width/2 > column.x + column.width/2 - 10 && bird.x + bird.width/2 < column.x + column.width/2 + 10 && !gameOver) {
					score++;
				}

				if (column.intersects(bird)) {

					gameOver = true;

					if (bird.x <= column.x) 
						bird.x = column.x -bird.width;
					else {
						if (column.y != 0) {
							bird.y = column.y - bird.height;	
						} 
						else if (bird.y < column.height) {
							bird.y = column.height;
						}
					}

					bird.x = column.x  - bird.width;
				}
			}

			if (bird.y > HEIGHT - 120 || bird.y < 0) {

				gameOver = true;
			}

			if (bird.y + yMotion >= HEIGHT - 120) {
				bird.y = HEIGHT - 120 - bird.height;
			}
		}

		renderer.repaint();

	}


	public static void repaint(Graphics g) {

		g.setColor(Color.CYAN);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g.setColor(Color.green);
		g.fillRect(0, HEIGHT-120, WIDTH, 120);

		g.setColor(Color.pink);
		g.fillRect(0, HEIGHT - 120, WIDTH, 20);

		g.setColor(Color.magenta.darker());
		g.fillRect(bird.x, bird.y, bird.width, bird.height);

		for (Rectangle column: columns) {
			paintColumn(g, column);
		}

		g.setColor(Color.black);
		g.setFont(new Font("Arial", 1, 100));

		if (!started) {
			g.drawString("Click to Start", 100, HEIGHT/2 - 60);
		}
		if (gameOver) {
			g.drawString("GAME OVER!", 230, HEIGHT/2 - 80);
			g.setFont(new Font("Arial", 1, 50));
			g.drawString("Score: " + String.valueOf(score), 250, HEIGHT/2 - 20);
		}

		if (!gameOver && started) {
			g.setFont(new Font("Arial", 1, 50));
			g.drawString(String.valueOf(score), WIDTH/2 - 25, 50);
		}

	}

	public static void main(String[] args) {
		flappyBird = new FlappyBird();
	}
}

