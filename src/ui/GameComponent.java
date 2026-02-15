package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Maze;
import model.PlayerSprite;
import model.ZombieSprite;
import model.GemSprite;

public class GameComponent extends JPanel {

    private enum State { START_SCREEN, PLAYING, GAME_OVER, WIN }
    private State currentState = State.START_SCREEN;

    private PlayerSprite player;
    private Maze maze;
    private ArrayList<ZombieSprite> zombies;
    private ArrayList<GemSprite> gems;
    private static BufferedImage ground;
    
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Timer timer;
    private int currentLevel = 1;

    public GameComponent() {
        setFocusable(true);
        try {
            ground = ImageIO.read(getClass().getResource("ground.png"));
        } catch (Exception e) { }

        loadLevel(1);

        // Timer running at ~60 FPS
        timer = new Timer(16, e -> { 
            if (currentState == State.PLAYING) {
                updateGameLogic();
            }
            repaint(); 
        });
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { 
                int k = e.getKeyCode();
                
                if (currentState == State.START_SCREEN) {
                    if (k == KeyEvent.VK_ENTER) currentState = State.PLAYING;
                }
                else if (currentState == State.GAME_OVER || currentState == State.WIN) {
                    if (k == KeyEvent.VK_R) restartGame(); 
                }
                else if (currentState == State.PLAYING) {
                    if (k == KeyEvent.VK_W) upPressed = true;
                    if (k == KeyEvent.VK_S) downPressed = true;
                    if (k == KeyEvent.VK_A) leftPressed = true;
                    if (k == KeyEvent.VK_D) rightPressed = true;
                    if (k == KeyEvent.VK_SPACE) handleCatapult();
                }
            } 

            @Override
            public void keyReleased(KeyEvent e) {
                int k = e.getKeyCode();
                if (k == KeyEvent.VK_W) upPressed = false;
                if (k == KeyEvent.VK_S) downPressed = false;
                if (k == KeyEvent.VK_A) leftPressed = false;
                if (k == KeyEvent.VK_D) rightPressed = false;
            }
        });
    }

    private void loadLevel(int level) {
        // FIX: Player size set to 40, 40 to fit in 50x50 maze blocks
        player = new PlayerSprite(60, 60, 40, 40); 
        
        maze = new Maze("level" + level + ".txt");
        zombies = maze.getLoadedZombies();
        gems = maze.getLoadedGems();
        
        // Safety check if level is empty
        if (zombies.isEmpty() && level == 1) {
            zombies.add(new ZombieSprite(400, 200));
        }
    }

    private void restartGame() {
        currentLevel = 1;
        loadLevel(1);
        currentState = State.PLAYING;
    }

    private void updateGameLogic() {
        if (player.getLives() <= 0) {
            currentState = State.GAME_OVER;
            return;
        }

        player.updateStatus();

        // FIX: Speed increased to 15. 
        // This is "Arcade Speed". Very fast response.
        int speed = 15; 
        
        int dx = 0;
        int dy = 0;

        // ... rest of movement logic is the same ...
        if (upPressed) dy -= speed;
        if (downPressed) dy += speed;
        if (leftPressed) dx -= speed;
        if (rightPressed) dx += speed;

        if (dx != 0 || dy != 0) {
            if (!maze.collides(player.getFutureBounds(dx, 0))) player.move(dx, 0);
            if (!maze.collides(player.getFutureBounds(0, dy))) player.move(0, dy);
        }

        // Zombie Logic
        for (ZombieSprite z : zombies) {
            z.update(maze); // FIX: Pass maze to zombie so it sees walls
            
            if (z.getBounds().intersects(player.getBounds())) {
                player.loseLife();
            }
        }

        // Gem Logic
        boolean allCollected = true;
        for (GemSprite g : gems) {
            if (!g.isCollected()) {
                allCollected = false;
                if (player.getBounds().intersects(g.getBounds())) {
                    player.collectGem();
                    g.collect();
                }
            }
        }
        
        if (allCollected) {
            currentLevel++;
            // Try to load next level, if fails, you win
            try {
                // Check if file exists by trying to load a dummy maze
                Maze nextMaze = new Maze("level" + currentLevel + ".txt");
                if (nextMaze.getLoadedGems().isEmpty() && nextMaze.getLoadedZombies().isEmpty()) {
                     throw new Exception("Level empty");
                }
                loadLevel(currentLevel);
                player.setX(60);
                player.setY(60);
            } catch (Exception e) {
                currentState = State.WIN;
            }
        }
    }

    private void handleCatapult() {
        for (ZombieSprite z : zombies) {
            if (z.getBounds().intersects(player.catapultBound())) {
                if (player.getPlayerX() > z.getZombieX()) z.pushZombieForward();
                else z.pushZombieBack();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (currentState == State.PLAYING || currentState == State.GAME_OVER || currentState == State.WIN) {
             if (ground != null) g2.drawImage(ground, 0, 0, getWidth(), getHeight(), null);
             else {
                 g2.setColor(Color.LIGHT_GRAY);
                 g2.fillRect(0, 0, getWidth(), getHeight());
             }

             maze.drawWalls(g2);
             for (GemSprite gem : gems) gem.draw(g2);
             for (ZombieSprite z : zombies) z.draw(g2);
             player.draw(g2);

             g2.setFont(new Font("Arial", Font.BOLD, 18));
             g2.setColor(Color.WHITE);
             g2.drawString("Lives: " + player.getLives(), 20, 30);
             g2.drawString("Level: " + currentLevel, 120, 30);
             g2.drawString("Gems: " + player.getGems(), 220, 30);
        }

        // Overlays
        if (currentState == State.START_SCREEN) {
            drawOverlay(g2, "ZOMBIE MAZE", "WASD to Move. SPACE to Push.", "Press ENTER to Start", Color.CYAN);
        }
        else if (currentState == State.GAME_OVER) {
            drawOverlay(g2, "GAME OVER", "The zombies got you!", "Press R to Restart", Color.RED);
        }
        else if (currentState == State.WIN) {
            drawOverlay(g2, "VICTORY!", "You collected all gems!", "Press R to Restart", Color.GREEN);
        }
    }
    
    private void drawOverlay(Graphics2D g2, String title, String sub1, String sub2, Color color) {
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(color);
        g2.setFont(new Font("Arial", Font.BOLD, 50));
        g2.drawString(title, 180, 250);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawString(sub1, 200, 300);
        g2.drawString(sub2, 230, 350);
    }
}