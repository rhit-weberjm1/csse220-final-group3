package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Maze class that loads images
 */


public class MazeBlock{
	private int x, y;
    private static BufferedImage block;
    private static boolean triedLoad = false;

	
	//is just the ground
    public MazeBlock(int x, int y) {
    	this.x = x;
    	this.y = y;
        loadSpriteOnce();
    }
    
    public Rectangle getBounds() {
    	return new Rectangle(x, y, 30, 30);
    }
    
    
    //loads the ground
    private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;
		try {
			block = ImageIO.read(PlayerSprite.class.getResource("diamond-block.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("wall");
		}


	}	
    
    
    //disclaimer, this is a wall, its a bright green grass block    
    //Use a different png image
    
    public void drawBlock(Graphics2D g2) {
    	  
    	if (block != null) {
			g2.drawImage(block, x, y, 30, 30, null);
		}else {
			g2.setColor(Color.BLUE);
            g2.fillRect(x, y, 30, 30);
		}
	}
}
	
	
	
	

