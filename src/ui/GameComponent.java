package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.GameModel;
import model.Maze;
import model.PlayerSprite;
import model.ZombieSprite;

/**
 * This is where all the sprites and images are drawn
 */


public class GameComponent extends JPanel {

	
	
//	private GameModel model;
	private PlayerSprite player;

	private Maze wall;
	
	private ZombieSprite zombie;

	private static BufferedImage ground;
	private static boolean triedLoad = false;
	private Timer timer;
	private int step = 1;
	private int distanceMoved = 0;
	private boolean movingLeft = true;
	
	private ArrayList<ZombieSprite> zombies = new ArrayList<>();

	public GameComponent() {
		 setFocusable(true);
		 requestFocusInWindow();
		try {
			ground = ImageIO.read(getClass().getResource("ground.png"));
			System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("ground");
		}
		player =  new PlayerSprite(200, 250, 70, 130);
		wall = new Maze();
		zombie = new ZombieSprite(400, 200);
		
		// zombie movement
		timer = new Timer(20, e -> { 
			
			
			//I have the zombie stop moving when he touches steve FOR NOW
			
			if(zombie.getBounds().intersects(player.getBounds())) {
				step = 0;
			}
			
			if (movingLeft) {
				zombie.moveLeft(step); 
			}
			else {
				zombie.moveRight(step);
			}
			
			distanceMoved += step;
			
			// switch direction
			if (distanceMoved >= 100) {
				movingLeft = !movingLeft;
				distanceMoved = 0;
			
				
			}
			repaint(); 
		});
		timer.start();
		
		//key events, button presses
		
		addKeyListener(new KeyAdapter() {
			  @Override
			  public void keyPressed(KeyEvent e) {
			    if (e.getKeyCode() == KeyEvent.VK_A) {
			      player.moveLeft(20);
			      repaint();
			    }else if (e.getKeyCode() == KeyEvent.VK_D) {
			    	player.moveRight(20);
				    repaint();
			    }else if (e.getKeyCode() == KeyEvent.VK_W) {
			    	player.moveUp(20);
				    repaint();
			    }else if (e.getKeyCode() == KeyEvent.VK_S) {
			    	player.moveDown(20);
				    repaint();
			    }
			  }
		});
	
	}
	
	


	//draw them

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
	// Minimal placeholder to test  it’s running

		g2.drawImage(ground, 0, 0, 600, 600, null);

		

		player.draw(g2);

		wall.drawWalls(g2);
		
		zombie.draw(g2);
		
	// TODO: draw based on model state
		
	}
	

	  

}

