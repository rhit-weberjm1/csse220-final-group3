package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


/*
 * Draws the gem sprite, a collectible.
 * 
 * 
 */


public class GemSprite {

	private int x,y;
	private static final int width = 10;
	private static final int height = 20;
	
	private static BufferedImage sprite = null;
	private static boolean triedLoad = false;
	private boolean collected = false;
	
	
	//constructor
	public GemSprite(int x, int y) {
		this.x = x;
		this.y = y;
		
	}
	
	
	//loads in the sprite image
	 private static void loadSpriteOnce() {
			if (triedLoad) return;
			triedLoad = true;
			try {
				sprite = ImageIO.read(PlayerSprite.class.getResource("player.png"));
				System.out.println("loaded");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println("sprite");
			}
			
	 }
	 
	 public Rectangle getBounds() {
			Rectangle r = new Rectangle(
					x,
					y,
					width,
					height
					);
			return r;
		}
	  
	 public void collect() {
		 collected = true;
	 }
	 
	 public boolean isCollected() {
		 return collected;
	 }
	 
	 //draw the sprite image, if no image just draw a little rectangle
	
	public void draw(Graphics2D g2) {
		if (collected) return;
		if (sprite !=null) {
			g2.drawImage(sprite,x,y,width,height,null);
		}
		else {
			g2.setColor(Color.YELLOW);
			g2.fillRect(x, y, width, height);
		}
		
	}
	
	
	
	
	
}
