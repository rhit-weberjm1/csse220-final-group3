package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/*
 * Draws the zombie enemy.
 */


public class ZombieSprite implements Collidable{
		
	
	private int x,y;
	private static final int width = 70; // can adjust size when maze exists
	private static final int height = 80;
	private static BufferedImage zombie = null;
	private static boolean triedLoad = false;
	
	
	
	
	public ZombieSprite(int x, int y) {
		this.x= x;
		this.y = y;
		loadSpriteOnce();
		
	}
	
	
	/*
	 * Load in zombie sprite
	 */
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
    
	
    //draw sprite
    
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


	@Override
	public void onCollisionWithWall(Maze wall) {
		// TODO Auto-generated method stub
		// add a dx = -dx
		//and dy=-dy
	}


	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		Rectangle r = new Rectangle(
				x +20,
				y,
				35,
				75
				);
		return r;
	}
	
}
