import java.awt.EventQueue;
import javax.swing.JFrame;

public class Donut extends JFrame
{
	public Donut() {
		initUI();
	}

	private void initUI() {
		add (new Board());

		setSize(400, 400);
		setTitle("Jonut");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		EventQueue.invokeLater(() -> {
			Donut d = new Donut();
			d.setVisible(true);
		});
	}
}
