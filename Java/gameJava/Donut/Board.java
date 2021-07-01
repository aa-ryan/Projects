import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

public class Board extends JPanel
{

	@Override
	public void paintComponent(Graphics g)
	{
		/* It prints the component as if you hadn't overridden the paintComponent method.
		 * If you have a background color set for instance, this is typically painted by the class you're extending. */
		super.paintComponent(g);

		drawDonut(g);

	}

	private void drawDonut(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;

		/*  The ANTIALIASING hint controls whether or not the geometry rendering methods of a Graphics2D object will attempt to reduce aliasing artifacts along the edges of shapes. */
		/* VALUE_ANTIALIAS_ON -> Antialiasing hint value -- rendering is done with antialiasing. */
		/* The rendering hints are used to make the drawing smooth. */

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
								RenderingHints.VALUE_ANTIALIAS_ON);

		/* The RENDERING hint is a general hint that provides a high level recommendation as to whether to bias algorithm choices more for speed or quality when evaluating tradeoffs. This hint could be consulted for any rendering or image manipulation operation, but decisions will usually honor other, more specific hints in preference to this hint.
		* VALUE_RENDER_QUALITY -> Rendering hint value -- rendering algorithms are chosen with a preference for output quality. */

		rh.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		Dimension size = getSize();
		double w = size.getWidth();
		double h = size.getHeight();



		/* Constructs and initializes an Ellipse2D from the specified coordinates. */
		Ellipse2D e = new Ellipse2D.Double(0, 0, 100, 150);
		/* Stroking – is a process of drawing a shape’s outline applying stroke width, line style, and color attribute */
		g2d.setStroke(new BasicStroke(1));
		/* A BasicStroke object holds information about the line width, join style, end-cap style, and dash style. This information is used when a Shape is rendered with the draw method. */
		g2d.setColor(Color.gray);

		for (double deg = 0; deg < 360; deg += 5) {
			/* An affine transform is a transformation such as translate, rotate, scale, or shear in which parallel lines remain parallel even after being transformed. */
			AffineTransform at = AffineTransform.getTranslateInstance(w/2 , h/2);
			at.rotate(Math.toRadians(deg));
			g2d.draw(at.createTransformedShape(e));

		}

	}

}
