package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;

import model.GameModel;
import model.ZombieSprite;

public class GameComponent extends JComponent {

	
	
	private GameModel model;
//	private ZombieSprite zombie;


	public GameComponent(GameModel model) {
		this.model = model;
	}
//	public GameComponent(ZombieSprite zombie) {
//		this.zombie = zombie;
//	}


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;

	// Minimal placeholder to test  it’s running
//	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);
	
//	zombie.draw(g2);

	// TODO: draw based on model state
		
	}
}
