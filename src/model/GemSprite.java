package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GemSprite {

    private int x, y;
    // Dimensions of the gem itself
    private static final int width = 15;
    private static final int height = 25;
    
    // Size of the maze block it sits in
    private static final int BLOCK_SIZE = 50;
    
    private static BufferedImage sprite = null;
    private static boolean triedLoad = false;
    private boolean collected = false;
    
    public GemSprite(int x, int y) {
        this.x = x;
        this.y = y;
        loadSpriteOnce();
    }
    
    private static void loadSpriteOnce() {
        if (triedLoad) return;
        triedLoad = true;
        try {
            sprite = ImageIO.read(PlayerSprite.class.getResource("gem.png")); // Check your filename!
        } catch (Exception e) { }
    }
     
    public Rectangle getBounds() {
        // Hitbox is slightly larger than the visual gem to make collecting easier
        // We center the hitbox logic too:
        int offsetX = (BLOCK_SIZE - width) / 2;
        int offsetY = (BLOCK_SIZE - height) / 2;
        return new Rectangle(x + offsetX, y + offsetY, width, height);
    }
      
    public void collect() { collected = true; }
     
    public boolean isCollected() { return collected; }
     
    public void draw(Graphics2D g2) {
        if (collected) return;

        // MATH TO CENTER THE GEM
        // Formula: Position + (ContainerSize - ObjectSize) / 2
        int drawX = x + (BLOCK_SIZE - width) / 2;
        int drawY = y + (BLOCK_SIZE - height) / 2;

        if (sprite != null) {
            g2.drawImage(sprite, drawX, drawY, width, height, null);
        }
        else {
            g2.setColor(Color.YELLOW);
            g2.fillOval(drawX, drawY, width, height); // Changed to Oval for nicer default look
            g2.setColor(Color.ORANGE);
            g2.drawOval(drawX, drawY, width, height);
        }
    }
}