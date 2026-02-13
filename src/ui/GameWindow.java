package ui;

import java.awt.Component;

import javax.swing.JFrame;

import model.GameModel;
import model.PlayerSprite;
import model.ZombieSprite;



// so you can see all the stuff game component made
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

		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
	}

}
