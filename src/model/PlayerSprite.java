package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class PlayerSprite implements Collidable {
    private int x, y, height, width;
    private static BufferedImage sprite = null;
    private static boolean triedLoad = false;
    private int lives = 3;
    
    // Invincibility Logic
    private boolean invincible = false;
    private long timeInvincible; 

    private int gems;

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
        } catch (Exception e) {
            System.out.println("player sprite missing");
        }
    }

    public void draw(Graphics2D g2) {
        // Visual indicator for invincibility
        if (invincible) {
            g2.setColor(Color.RED);
            g2.fillRect(x, y, width, height); // Draw red box behind/over player to show damage
            return; 
        }

        if (sprite != null) {
            g2.drawImage(sprite, x, y, width, height, null);
        } else {
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, width, height);
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    // --- THESE WERE MISSING ---
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    // --------------------------

    public int getPlayerX() { return x; }
    public int getPlayerY() { return y; }

    public void loseLife() {
        if (invincible) return;
        
        lives--;
        invincible = true;
        timeInvincible = System.currentTimeMillis() + 1500; // 1.5 seconds invincibility
        System.out.println("Hit! Lives left: " + lives);
    }

    public void updateStatus() {
        if (invincible && System.currentTimeMillis() > timeInvincible) {
            invincible = false;
        }
    }

    public int getLives() { return lives; }

    public void collectGem() { gems++; }
    public int getGems() { return gems; }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 5, y + 5, width - 10, height - 10);
    }

    public Rectangle getFutureBounds(int dx, int dy) {
        return new Rectangle(x + dx, y + dy, width, height);
    }
    
    public Rectangle catapultBound() {
        return new Rectangle(x - 90, y, width + 90, height);
    }

    @Override
    public void onCollisionWithWall(Maze wall) { }
}