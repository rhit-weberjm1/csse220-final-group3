package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;

import model.GameModel;
import model.PlayerSprite;
import model.ZombieSprite;

public class GameComponent extends JComponent {

	
	
//	private GameModel model;
	private PlayerSprite player;


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
