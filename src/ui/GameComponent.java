package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import model.GameModel;
import model.Maze;
import model.PlayerSprite;
import model.ZombieSprite;



public class GameComponent extends JComponent {

	
	
//	private GameModel model;
	private PlayerSprite player;
	private static BufferedImage ground = null;
	private static boolean triedLoad = false;

//	public GameComponent(GameModel model) {
//		this.model = model;
//	}
	public GameComponent(PlayerSprite player) {
		this.player= player;
	
	}


	
	



	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
//	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);
	
		
		
		player.draw(g2);
	
	// TODO: draw based on model state
		
	}
}
