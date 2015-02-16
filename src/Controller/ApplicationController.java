package Controller;

import View.ApplicationView;
import View.Main;

public class ApplicationController {

	public static void home() {
		// prikaz home GUI-a
		ApplicationView.home();
	}

	public static void main(String[] args) {

		/*TODO  povezivanje sa bazom */
		Main.init();
		home();

	}
}
