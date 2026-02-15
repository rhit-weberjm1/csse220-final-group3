package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ZombieSprite implements Collidable {

    private int x, y;
    // Resize zombie slightly to fit better too (40x40)
    private static final int width = 40; 
    private static final int height = 40;
    private static BufferedImage zombie = null;
    private static boolean triedLoad = false;

    private boolean movingLeft = true;
    private int distanceMoved = 0;
    private int patrolRange = 150; 
    
    // FIX: Match player speed logic roughly, slightly slower than player
    private int speed = 4; 

    public ZombieSprite(int x, int y) {
        this.x = x;
        this.y = y;
        if (Math.random() > 0.5) movingLeft = false;
        loadSpriteOnce();
    }

    public int getZombieX() { return x; }
    public int getZombieY() { return y; }

    private static void loadSpriteOnce() {
        if (triedLoad) return;
        triedLoad = true;
        try {
            zombie = ImageIO.read(PlayerSprite.class.getResource("zombie.png"));
        } catch (Exception e) { }
    }

    // FIX: Now accepts Maze to check for walls
    public void update(Maze maze) {
        int dx = 0;
        
        if (movingLeft) dx = -speed;
        else dx = speed;
        
        // Create a rectangle for where we WANT to be
        Rectangle futureBounds = new Rectangle(x + dx, y, width, height);
        
        // If we hit a wall, turn around immediately
        if (maze.collides(futureBounds)) {
            movingLeft = !movingLeft;
            distanceMoved = 0;
        } else {
            // Otherwise, move
            x += dx;
            distanceMoved += Math.abs(dx);
            
            // Turn around if we walked too far (patrol logic)
            if (distanceMoved >= patrolRange) {
                movingLeft = !movingLeft;
                distanceMoved = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (zombie != null) {
            g2.drawImage(zombie, x, y, width, height, null);
        } else {
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y, width, height);
        }
    }

    public void pushZombieBack() { x += 50; }
    public void pushZombieForward() { x -= 50; }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    @Override
    public void onCollisionWithWall(Maze wall) { }
}