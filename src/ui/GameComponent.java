package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

import model.GameModel;
import model.Maze;
import model.PlayerSprite;
import model.ZombieSprite;




public class GameComponent extends JPanel {

	
	
//	private GameModel model;
	private PlayerSprite player;

	private Maze wall;
	private static BufferedImage ground;
	private static boolean triedLoad = false;

	public GameComponent() {
		try {
			ground = ImageIO.read(getClass().getResource("ground.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ground");
		}
		player =  new PlayerSprite(200, 250, 70, 130);
		wall = new Maze(0,0);
		
	
	}
	
	




	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
	// Minimal placeholder to test  it’s running

		g2.drawImage(ground, 0, 0, 600, 600, null);

		

		player.draw(g2);

		wall.drawWalls(g2);
		
	// TODO: draw based on model state
		
	}

}

