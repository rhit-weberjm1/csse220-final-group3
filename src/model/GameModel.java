package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GameModel {
	private int x, y, height, width;
	private BufferedImage sprite;
	
	public GameModel (int x, int y, int height, int width, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.sprite = sprite;
		
	}
	
	public void drawModel (Graphics2D g2) {
		g2.drawImage(sprite, x, y, width, height, null);
	}
	
}
