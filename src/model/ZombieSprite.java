package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ZombieSprite {
		
	
	private int x,y;
	private static final int width = 20; // can adjust size when maze exists
	private static final int height = 20;
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	
	// add movements up here
	
	
	public ZombieSprite(int x, int y) {
		this.x= x;
		this.y = y;
		
		
	}
	
	
	//will implement image once I have them..
	
	
	
//	private static void loadSpriteOnce() {
//		if (triedLoad) return;
//		triedLoad = true;
//		
//		try {
//			break;
//		}
//		
//		catch (IOException | IllegalArgumentException ex) {
//			sprite = null; 
//			
//		}
	
	public void draw(Graphics2D g2) {
		if (sprite !=null) {
			g2.drawImage(sprite,x,y,width,height,null);
		}
		else {
			g2.setColor(Color.GREEN);
			g2.fillRect(x, y, width, height);
		}
		
		
		//movement below
	}
	
}
