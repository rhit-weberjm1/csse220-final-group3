package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ZombieSprite {
		
	
	private int x,y;
	private static final int width = 70; // can adjust size when maze exists
	private static final int height = 80;
	private static BufferedImage zombie = null;
	private static boolean triedLoad = false;
	
	// add movements up here
	
	
	public ZombieSprite(int x, int y) {
		this.x= x;
		this.y = y;
		loadSpriteOnce();
		
	}
	
	
	//will implement image once I have them..
    private static void loadSpriteOnce() {
		if (triedLoad) return;
		triedLoad = true;
		try {
			zombie = ImageIO.read(PlayerSprite.class.getResource("zombie.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("zombie");
		}

		
	}	
    
	
	public void draw(Graphics2D g2) {
		if (zombie !=null) {
			g2.drawImage(zombie,x,y,width,height,null);
		}
		else {
			g2.setColor(Color.GREEN);
			g2.fillRect(x, y, width, height);
		}
		
		
		//movement below
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
