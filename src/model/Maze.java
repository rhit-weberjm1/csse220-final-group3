package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Maze {
    private ArrayList<MazeBlock> blocks = new ArrayList<>();
    private ArrayList<ZombieSprite> loadedZombies = new ArrayList<>();
    private ArrayList<GemSprite> loadedGems = new ArrayList<>();
    
    private int size = 50;

    // Modified Constructor: Takes a String filename
    public Maze(String levelFile) {
        loadMaze(levelFile);
    }

    private void loadMaze(String filename) { 
        File file = new File(filename); 
        
        // If the level file doesn't exist, try loading default to prevent crash
        if (!file.exists()) {
            System.out.println("Level file " + filename + " not found!");
            return;
        }

        try {
            Scanner scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int col = 0; col < line.length(); col++) {
                    char c = line.charAt(col);
                    int pixelX = col * size;
                    int pixelY = row * size;

                    if (c == '1') {
                        blocks.add(new MazeBlock(pixelX, pixelY));
                    } else if (c == '3') {
                        loadedZombies.add(new ZombieSprite(pixelX, pixelY));
                    } else if (c == '4') {
                        loadedGems.add(new GemSprite(pixelX, pixelY));
                    }
                }
                row++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<ZombieSprite> getLoadedZombies() { return loadedZombies; }
    public ArrayList<GemSprite> getLoadedGems() { return loadedGems; }

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