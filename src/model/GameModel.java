package model;
 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
 
public class GameModel {
    private int x, y;
    private int width, height;
    private BufferedImage sprite;
    
    public GameModel(int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }
    
    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, width, height, null);
        }
    }
 
    // Methods for the Player to move
 
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
 
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
 
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    
    public BufferedImage getSprite() { return sprite; }
}
