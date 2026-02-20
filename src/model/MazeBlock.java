package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MazeBlock {
    private int x, y;
    private static BufferedImage block;
    private static boolean triedLoad = false;
    
    private static final int SIZE = 50; 

    public MazeBlock(int x, int y) {
        this.x = x;
        this.y = y;
        loadSpriteOnce();
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, SIZE, SIZE);
    }
    
    private static void loadSpriteOnce() {
        if (triedLoad) return;
        triedLoad = true;
        try {
            block = ImageIO.read(MazeBlock.class.getResource("diamond-block.png"));
        } catch (Exception e) {
            System.out.println("Block image not found, using color fallback");
        }
    }   
    
    public void drawBlock(Graphics2D g2) {
        if (block != null) {
            g2.drawImage(block, x, y, SIZE, SIZE, null);
        } else {
            g2.setColor(Color.CYAN);
            g2.fillRect(x, y, SIZE, SIZE);
            g2.setColor(Color.BLUE);
            g2.drawRect(x, y, SIZE, SIZE);
        }
    }
}