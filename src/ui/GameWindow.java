package ui;

import javax.swing.JFrame;

import model.GameModel;
import model.PlayerSprite;
import model.ZombieSprite;

public class GameWindow {

	public static void show() {
		// Minimal model instance (empty for now, by design)
//		GameModel model = new GameModel(0, 0, 0, 0, null);


		JFrame frame = new JFrame("CSSE220 Final Project");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//		frame.add(new GameComponent(model));
		
		PlayerSprite player = new PlayerSprite(10,10);
		
		frame.add(new GameComponent(player));


		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null); // center on screen (nice UX, still minimal)
		frame.setVisible(true);
		}

}
