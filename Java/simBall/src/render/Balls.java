package src.render;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import src.main.*;

public class Balls extends JPanel {

    private List<Ball> ballsUp;

    public Balls() {
        ballsUp = new ArrayList<Ball>(25);  // initial CAPACITY = 25 <don't confuse capacity with SIZE>

        for (int index = 0; index < 10 + randomgen.random(90); index++) {
            ballsUp.add(new Ball(new Color(randomgen.random(255), randomgen.random(255), randomgen.random(255)))); // RGB values
        }
    } 

    @Override
    protected void paintComponent(Graphics g) {
    /*  The ANTIALIASING hint controls whether or not the geometry rendering methods of a Graphics2D object will attempt to reduce aliasing artifacts along the edges of shapes.
    * VALUE_ANTIALIAS_ON -> Antialiasing hint value -- rendering is done with antialiasing.
    * The rendering hints are used to make the drawing smooth. */



        super.paintComponent(g);
        setBackground(Color.pink);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Ball ball : ballsUp) {
            ball.paint(g2d);
        }
    /* The RENDERING hint is a general hint that provides a high level recommendation as to whether to bias algorithm choices more for speed or quality when evaluating tradeoffs. This hint could be consulted for any rendering or image manipulation operation, but decisions will usually honor other, more specific hints in preference to this hint.
     * VALUE_RENDER_QUALITY -> Rendering hint value -- rendering algorithms are chosen with a preference for output quality. */
        g2d.dispose();
    }

    public List<Ball> getBalls() {
        return ballsUp;
    }
}
