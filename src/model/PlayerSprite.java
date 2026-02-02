package model;
 
import java.awt.Color;
import java.awt.Graphics2D;
 
public class PlayerSprite extends GameModel {
    private int dx = 4;
    private int dy = 4;
 
    public PlayerSprite(int x, int y) {
        super(x, y, 20, 20, null);
    }
 
    @Override
    public void draw(Graphics2D g2) {
        if (getSprite() != null) {
            super.draw(g2);
        } else {
            g2.setColor(Color.BLUE);
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }
    
    public void move(int keyX, int keyY) {
        setX(getX() + (keyX * dx));
        setY(getY() + (keyY * dy));
    }
}