package model;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

//import starter.Ball;


/*
 * Builds steve, the player sprite
 */
 
public class PlayerSprite implements Collidable{
	private int x, y, height, width, step;
    private static BufferedImage sprite = null;
    private static boolean triedLoad = false;
    
    
    //constructor
 
    public PlayerSprite(int x, int y, int height, int width) {
    	this.height = height;
    	this.width = width;
    	this.x = x;
    	this.y = y;
    	
    	//function call to load in the sprite
        loadSpriteOnce();
    }
 
    //loads in the sprite if there is one, return false if none
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

    
    //draw the sprite, if image or not

    public void draw(Graphics2D g2) {
  
    	if (sprite != null) {
			g2.drawImage(sprite, x, y, width, height, null);
		}else {
			g2.setColor(Color.BLUE);
            g2.fillRect(x, y, width, height);
		}
          
    }
    
    
    
    //player movement
    
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


	@Override
	public Rectangle getBounds() {
		Rectangle r = new Rectangle(
				x,
				y,
				height,
				width
				);
		return r;
	}

//	@Override
	public void onCollisionWithWall(Maze wall) {
		// TODO Auto-generated method stub
		step = 0;
	}
    
    
    
    

   
}