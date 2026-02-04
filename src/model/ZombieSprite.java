package model;
 
import java.awt.Color;
import java.awt.Graphics2D;
 
public class ZombieSprite extends GameModel {
 
 
    public ZombieSprite(int x, int y) {
        super(x, y, 20, 20, null);
    }
 
    @Override
    public void draw(Graphics2D g2) {
        if (getSprite() != null) {
            super.draw(g2);
        } else {
            g2.setColor(Color.GREEN);
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }
    
    public void autoMove() {
        setX(getX() + 1);
    }
}