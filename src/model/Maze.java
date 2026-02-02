package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Maze extends GameModel{

	public Maze(int x, int y, int height, int width, BufferedImage sprite) {
		super(x, y, height, width, sprite);
		// TODO Auto-generated constructor stub
		
	}
	
	public void groundMaze(Graphics2D g2, BufferedImage sprite) {
		try {
			sprite = ImageIO.read(Maze.class.getResource("minecraft grass block.webp"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.draw(g2);
		
		
	}
	
	
	
	
}
