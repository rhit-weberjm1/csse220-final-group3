package model;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//import starter.Ball;
 
public class PlayerSprite{
	private int x, y, height, width, step;
    private static BufferedImage sprite = null;
    private static boolean triedLoad = false;
 
    public PlayerSprite(int x, int y, int height, int width) {
    	this.height = height;
    	this.width = width;
    	this.x = x;
    	this.y = y;
        loadSpriteOnce();
    }
 
    private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;
		try {
			sprite = ImageIO.read(PlayerSprite.class.getResource("steve.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("sprite");
		}

		
	}	


    public void draw(Graphics2D g2) {
  
    	if (sprite != null) {
			g2.drawImage(sprite, x, y, width, height, null);
		}else {
			g2.setColor(Color.BLUE);
            g2.fillRect(x, y, width, height);
		}
          
    }
    
    public void moveLeft(int step) {
    	x -= step;
    }
    
    public void moveRight(int step) {
    	x += step;
    }
    
    public void moveUp (int step) {
    	y -= step;
    }
    
    public void moveDown (int step) {
    	y += step;
    }
    
    


   
}