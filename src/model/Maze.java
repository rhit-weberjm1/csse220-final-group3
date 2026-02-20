package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {

    private ArrayList<MazeBlock> blocks = new ArrayList<>();
    private ArrayList<ZombieSprite> loadedZombies = new ArrayList<>();
    private ArrayList<GemSprite> loadedGems = new ArrayList<>();

    private int size = 50;
    private int startX = 0;
    private int startY = 0;
    
    private int[][] grid;
    private int rows;
    private int cols;
    
    private BufferedImage cachedMaze;

    // Default constructor
    public Maze() {
        loadMaze("mazeLayout.txt");
        System.out.println("maze created");
    }

    // Constructor with filename
    public Maze(String levelFile) {
        loadMaze(levelFile);
    }

    private void loadMaze(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("Level file " + filename + " not found!");
            return;
        }

        try {
            // First read all lines into a list
            ArrayList<String> lines = new ArrayList<>();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            rows = lines.size();
            cols = lines.get(0).length();

            grid = new int[rows][cols];

            for (int row = 0; row < rows; row++) {
                String line = lines.get(row);

                for (int col = 0; col < cols; col++) {
                    char c = line.charAt(col);

                    int pixelX = startX + col * size;
                    int pixelY = startY + row * size;

                    if (c == '1') {
                        grid[row][col] = 1; // wall
                        blocks.add(new MazeBlock(pixelX, pixelY));
                    } 
                    else if (c == '3') {
                        loadedZombies.add(new ZombieSprite(pixelX, pixelY));
                    } 
                    else if (c == '4') {
                        loadedGems.add(new GemSprite(pixelX, pixelY));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        cachedMaze = new BufferedImage(cols * size, rows * size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = cachedMaze.createGraphics();

        for (MazeBlock block : blocks) {
            block.drawBlock(g2);
        }

        g2.dispose();
    }

    public ArrayList<ZombieSprite> getLoadedZombies() {
        return loadedZombies;
    }

    public ArrayList<GemSprite> getLoadedGems() {
        return loadedGems;
    }

    public void drawWalls(Graphics2D g2) {
//        for (MazeBlock block : blocks) {
//            block.drawBlock(g2);
//        }
    	if (cachedMaze != null) {
    		g2.drawImage(cachedMaze,  0 ,  0 , null);
    	}
    }

    public boolean collides(Rectangle r) {
    	 // Get tile positions of rectangle corners
        int leftCol = r.x / size;
        int rightCol = (r.x + r.width - 1) / size;
        int topRow = r.y / size;
        int bottomRow = (r.y + r.height - 1) / size;

        // Check all tiles the rectangle touches
        for (int row = topRow; row <= bottomRow; row++) {
            for (int col = leftCol; col <= rightCol; col++) {

                // Check bounds safety
                if (row < 0 || col < 0 || row >= rows || col >= cols) {
                    return true; // treat outside map as wall
                }

                if (grid[row][col] == 1) {
                    return true;
                }
            }
        }

        return false;
    }
}