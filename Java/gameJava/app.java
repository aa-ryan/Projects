package gameJava;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class app extends JFrame  {

	public app() {
		initUI();
	}

	private void initUI() {
		// Entry point of the game.
		add(new Board());

		setSize(250, 200);

		setTitle("APP");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {  // lamda function in java () is for parameter list
		EventQueue.invokeLater(() -> {
			app ex = new app();
			ex.setVisible(true);
		});
	}
	
}
