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
    private int lives = 3;
    private boolean invincible = false;
    private int timeInvincible;
    private int gems;
    
    
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

    public void move(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public int getPlayerX() {
		return x;
	}
	
	public int getPlayerY() {
		return y;
	}
	

////	
//	public void onCollisionWithWall(Maze wall) {
//		// TODO Auto-generated method stub
//		step = 0;
//	}
//    

    public void loseLife() {
    	if (invincible) return;
    	
    	lives--;
    	invincible = true;
    	timeInvincible = (int) (System.currentTimeMillis() + 1000);
    	
    }
    
    public void updateStatus() {
    	if (invincible && System.currentTimeMillis() > timeInvincible) {
    		invincible = false;
    	}
    }
    
    public int getLives() {
    	return lives;
    }
    
    public void collectGem() {
    	gems++;
    	
    }
    
    public int getGems() {
    	return gems;
    }

	@Override
	public Rectangle getBounds() {
		Rectangle r = new Rectangle(x + 50,y,35,75);
		return r;
	}
	
	public Rectangle getFutureBounds(int dx, int dy) {
	    return new Rectangle(x + dx, y + dy, width, height);
	}
	
	public Rectangle catapultBound() {
		return new Rectangle(x-90, y, width+90, height);
	}

	@Override
	public void onCollisionWithWall(Maze wall) {
		// TODO Auto-generated method stub
		
	}
    
	
    
    


   
}