package ui;

import java.awt.Component;

import javax.swing.JFrame;

import model.GameModel;
import model.PlayerSprite;
import model.ZombieSprite;


public class GameWindow {
	JFrame frame;
	public GameWindow() {
		frame = new JFrame("CSSE220 Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new GameComponent());
	}


	public  void show() {


//		frame.add(new GameComponent(model));
		
		
//		frame.add(new GameComponent(player));

		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
	}

}
