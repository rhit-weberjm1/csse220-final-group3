package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Maze class that creates and draws blocks
 */
public class Maze {
	// fields
	private ArrayList<MazeBlock> blocks;
	private int size = 30;
	private int startX;
	private int startY;
	
	
	// constructor
	public Maze() {
		this.startX = 0;
		this.startY = 0;
		blocks = new ArrayList<>();
		loadMaze();
		System.out.println("maze created");
	}
	
	//read file
	private void loadMaze() { 
		File file = new File("mazeLayout.txt");
		try {
			Scanner scanner = new Scanner(file);
			
			int row = 0;
			
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
					
				for (int col = 0; col < line.length(); col++) {
					char c = line.charAt(col);
					
					if (c == '1') {
						blocks.add(new MazeBlock(startX + col * size, startY + row * size));
					}
				}
				row++;
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("mazeLayout1.txt not found");
		}
	}
	
	public void drawWalls(Graphics2D g2) {
		for (MazeBlock block : blocks) {
			block.drawBlock(g2);
		}
	}
	
	public boolean collides(Rectangle r) {
		for (MazeBlock block : blocks) {
			if (block.getBounds().intersects(r)) {
				return true;
			}
		}
		return false;
	}

}
