package View;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;

public class Main {

	private static JFrame window = null;
	protected static int windowWidth = 400;
	protected static int windowHeight = 500;

	/**
	 * In this method we create main frame on which we'll
	 * put content panels
	 */
	public static void init() {
		window = new JFrame("BitBook");
		window.setSize(windowWidth, windowHeight);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
	}

	/**
	 * In this method we validate changes on frame,
	 * then repaint it
	 * and then set frame visible
	 */
	protected static void setVisible() {
		window.validate();
		window.repaint();
		window.setVisible(true);
		
	}

	/**
	 * In this method we put new contect panel on frame
	 * and then call method "setVisible" from above so we can
	 * make it visible
	 */
	protected static void replaceContent(Container panel) {
		window.setContentPane((Container)panel);
		setVisible();
	}
}
