package ee.ut.jjanno.terrain.window;


import java.awt.HeadlessException;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	View view = new View();

	public MainWindow(String title) throws HeadlessException {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 768);

		add(view);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow("Simple Terrain");
		window.view.mainLoop();
	}

}