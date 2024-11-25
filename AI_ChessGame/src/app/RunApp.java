package app;

import javax.swing.JFrame;

import view.MainView;

public class RunApp {
	JFrame window;

	public RunApp() {
		window = new JFrame();
		window.setTitle("Chess Game");
		MainView view = new MainView();
		window.add(view);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		new RunApp();
	}

}
