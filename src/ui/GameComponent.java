package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
import model.GemSprite;

/**
 * This is where all the sprites and images are drawn
 */


public class GameComponent extends JPanel {

	
	
//	private GameModel model;
	private PlayerSprite player;

	private Maze wall;
	
	private ZombieSprite zombie;
	private ZombieSprite zombie2;
	private ZombieSprite zombie3;
	
	private GemSprite gem1;
	private GemSprite gem2;
	private GemSprite gem3;


	private static BufferedImage ground;
	private static boolean triedLoad = false;
	private Timer timer;
	private int step = 1;
	private int distanceMoved = 0;
	private boolean movingLeft = true;
	private boolean gameOver = false;
	private boolean touchingZombie = false;
	
	private ArrayList<ZombieSprite> zombies = new ArrayList<>();

	public GameComponent() {
		 setFocusable(true);
		try {
			ground = ImageIO.read(getClass().getResource("ground.png"));
			//System.out.println("loaded");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//System.out.println("ground");
		}
		player =  new PlayerSprite(10, 480, 70, 130);
		wall = new Maze();
		zombie = new ZombieSprite(400, 200);
		zombie2 = new ZombieSprite(200,400);
		zombie3 = new ZombieSprite(400, 60);
		gem1 = new GemSprite(400, 425);
		gem2 = new GemSprite(150, 350);
		gem3 = new GemSprite(350, 100);
		zombies.add(zombie);
		zombies.add(zombie2);
		zombies.add(zombie3);
		
		
		
		// zombie movement
		timer = new Timer(10, e -> { 
			
			if (player.getLives() <= 0) {
				gameOver = true;
				timer.stop();
			}
			
			player.updateStatus();
			//I have the zombie stop moving when he touches steve FOR NOW
			
			
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
			
			
			if (movingLeft) {
				zombie2.moveLeft(step); 
			}
			else {
				zombie2.moveRight(step);
			}
			
			distanceMoved += step;
			
			// switch direction
			if (distanceMoved >= 100) {
				movingLeft = !movingLeft;
				distanceMoved = 0;
			
				
			}
			
			
			if (movingLeft) {
				zombie3.moveLeft(step); 
			}
			else {
				zombie3.moveRight(step);
			}
			
			boolean currentlyTouchingZombie =
			        zombie.getBounds().intersects(player.getBounds()) ||
			        zombie2.getBounds().intersects(player.getBounds()) ||
			        zombie3.getBounds().intersects(player.getBounds());

			if (currentlyTouchingZombie && !touchingZombie) {
			    player.loseLife();
			}

			// update state
			touchingZombie = currentlyTouchingZombie;

			distanceMoved += step;
			
			// switch direction
			if (distanceMoved >= 200) {
				movingLeft = !movingLeft;
				distanceMoved = 0;	
			}
			
			if (!gem1.isCollected() && player.getBounds().intersects(gem1.getBounds())) {
				player.collectGem();
				gem1.collect();
			}
			
			if (!gem2.isCollected() && player.getBounds().intersects(gem2.getBounds())) {
				player.collectGem();
				gem2.collect();
			}
			
			if (!gem3.isCollected() && player.getBounds().intersects(gem3.getBounds())) {
				player.collectGem();
				gem3.collect();
			}
			repaint(); 
		});
		timer.start();
		
		//key events, button presses
		
		addKeyListener(new KeyAdapter() {
			  @Override
			  public void keyPressed(KeyEvent e) { 
				  if (gameOver) return;
				  if (e.getKeyCode() == KeyEvent.VK_A) { 
						  player.moveLeft(20); 
						  repaint();
				  }
				  else if (e.getKeyCode() == KeyEvent.VK_D) {
						  player.moveRight(20); 
						  repaint();
				  }
				  else if (e.getKeyCode() == KeyEvent.VK_W) { 
						  player.moveUp(20); 
						  repaint(); 
				  }
				  else if (e.getKeyCode() == KeyEvent.VK_S) { 
						  player.moveDown(20); 
						  repaint(); 
				  }else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					  for(int i = 0; i < zombies.size(); i++) {
						  if (zombies.get(i).getBounds().intersects(player.catapultBound())){
							  if (player.getPlayerX() > zombies.get(i).getZombieX()) {
								  zombies.get(i).pushZombieFoward();
							  }else {
								  zombies.get(i).pushZombieBack();
							  }
							 
						  }
					  }
					  
					  repaint(); 
				  }
//				  if (e.getKeyCode() == KeyEvent.VK_A) {
//					    if (!wall.collides(player.getFutureBounds(-20, 0))) {
//					        player.moveLeft(20);
//					    }
//					}
//					else if (e.getKeyCode() == KeyEvent.VK_D) {
//					    if (!wall.collides(player.getFutureBounds(20, 0))) {
//					        player.moveRight(20);
//					    }
//					}
//					else if (e.getKeyCode() == KeyEvent.VK_W) {
//					    if (!wall.collides(player.getFutureBounds(0, -20))) {
//					        player.moveUp(20);
//					    }
//					}
//					else if (e.getKeyCode() == KeyEvent.VK_S) {
//					    if (!wall.collides(player.getFutureBounds(0, 20))) {
//					        player.moveDown(20);
//					    }
//					}
//					repaint();

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
		zombie2.draw(g2);
		zombie3.draw(g2);
		
		g2.setFont(new Font("comic sans", Font.BOLD, 16));
		Rectangle wordRect = new Rectangle(20, 5, 80, 40);
		g2.draw(wordRect);
		g2.setColor(Color.CYAN);
		g2.fill(wordRect);
		g2.setColor(Color.BLACK);
		g2.drawString("Lives: " + player.getLives(), 20, 20);
		g2.drawString("Gems: " + player.getGems(), 20, 40);
		
		if (!gem1.isCollected()) {
			gem1.draw(g2);
		}
		
		if (!gem2.isCollected()) {
			gem2.draw(g2);
		}
		
		if (!gem3.isCollected()) {
			gem3.draw(g2);
		}
		
		if (gameOver) {
			g2.setColor(Color.BLACK);
		    g2.fillRect(0, 0, getWidth(), getHeight());

		    g2.setColor(Color.RED);
		    g2.setFont(g2.getFont().deriveFont(48f));
		    g2.drawString("GAME OVER", 170, 300);
		}
		
		//hitbox indicators
		g2.setColor(Color.RED);
		g2.draw(player.getBounds());

		g2.setColor(Color.GREEN);
		g2.draw(zombie.getBounds());
		g2.draw(zombie2.getBounds());
		g2.draw(zombie3.getBounds());

		
		
		g2.setColor(Color.WHITE);

		g2.drawString("W A S D to move, SPACE to catapult a zombie", 210, 550);

	
	// TODO: draw based on model state
		
	}
	

	  

}

