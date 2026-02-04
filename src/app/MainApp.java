package app;

import javax.swing.SwingUtilities;

import ui.GameWindow;

/**
 * Class: MainApp
 * @author Put your team name here
 * <br>Purpose: Top level class for CSSE220 Project containing main method 
 * Entry point for the final project.
 */
public class MainApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new GameWindow().show();
		});
	}
	

}