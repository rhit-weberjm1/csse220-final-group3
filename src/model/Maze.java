package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Maze class that loads images
 */


public class Maze{
	private int x, y;
    private static BufferedImage grass;
    private static boolean triedLoad = false;

	
	
    public Maze(int x, int y) {
    	this.x = x;
    	this.y = y;
        loadSpriteOnce();
    }
    
    private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;
		try {
			grass = ImageIO.read(PlayerSprite.class.getResource("grasstemp.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("grass");
		}


	}	
    
    public void drawWalls(Graphics2D g2) {
    	  
    	if (grass != null) {
			g2.drawImage(grass, x, y, 35, 35, null);
		}else {
			g2.setColor(Color.GREEN);
            g2.fillRect(x, y, 35, 35);
		}
          
    }
	

		
	
		
	}
	
	
	
	

