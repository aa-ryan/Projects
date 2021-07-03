package src.main;   // need to specify package even if in same folder to use function of other *.java files

import java.awt.*;
import javax.swing.*;
import src.render.*;

public class Ball {

    private Color color;
    private Point location;
    private Point speed;
    private Dimension size;

    public Ball(Color color) {

        setColor(color);

        speed = new Point(10 - randomgen.random(20), 10 - randomgen.random(20));
        size = new Dimension(35, 35);

    }

    public Dimension getSize() {
        return size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public Point getLocation() {
        return location;
    }

    public Point getSpeed() {
        return speed;
    }

    public void setSpeed(Point speed) {
        this.speed = speed;
    }

    public void paint(Graphics2D g2d) {

        Point p = getLocation();
        if (p != null) {
            g2d.setColor(getColor());
            Dimension size = getSize();
            g2d.fillOval(p.x, p.y, size.width, size.height);
        }

    }
}

class BounceEngine implements Runnable {

    private Balls parent;

    public BounceEngine(Balls parent) {
        this.parent = parent;
    }

    @Override
    public void run() {

        int width = getParent().getWidth();
        int height = getParent().getHeight();

        // Randomize the starting position...
        for (Ball ball : getParent().getBalls()) {
            int x = randomgen.random(width);
            int y = randomgen.random(height);

            Dimension size = ball.getSize();

            if (x + size.width > width) {
                x = width - size.width;
            }
            if (y + size.height > height) {
                y = height - size.height;
            }

            ball.setLocation(new Point(x, y));

        }

        while (getParent().isVisible()) {

            /* Repaint the balls pen... */
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    getParent().repaint();
                }
            });

            /* This is little dangrous, as it's possible */
            /* for a repaint to occur while we're updating... */
            for (Ball ball : getParent().getBalls()) {
                move(ball);
            }

            /* Some small delay... */
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

    public Balls getParent() {
        return parent;
    }

    public void move(Ball ball) {

        Point p = ball.getLocation();
        Point speed = ball.getSpeed();
        Dimension size = ball.getSize();

        int vx = speed.x;
        int vy = speed.y;

        int x = p.x;
        int y = p.y;

        if (x + vx < 0 || x + size.width + vx > getParent().getWidth()) {
            vx *= -1;
        }
        if (y + vy < 0 || y + size.height + vy > getParent().getHeight()) {
            vy *= -1;
        }
        x += vx;
        y += vy;

        ball.setSpeed(new Point(vx, vy));
        ball.setLocation(new Point(x, y));

    }
}

