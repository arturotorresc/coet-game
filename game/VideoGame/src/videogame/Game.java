/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

/**
 * Space invaders style game with Rick and Morty theme
 * @author diego martinez
 */
public class Game implements Runnable {
    private BufferStrategy bs;      // to have several buffers when displaying
    private Graphics g;             // to paint objects
    private Display display;        // to display in the game
    String title;                   // title of the window
    private int width;              // width of the window
    private int height;             // height of the window
    private Thread thread;          // thread to create the game
    private boolean running;        // to set the game
    private boolean started;        //to know when the game starts
    private Player player;          // to use a player
    private boolean canShoot;       // to control player's shooting
    private ArrayList<Ball> bullets;  //to use bullets
    private ArrayList<EnemyBullet> enemyBullets; //to use enemy bullets
    private ArrayList<Obstacle> obstacles; //to implement obstacles.
    private int shootTmpPl;        //timer to control shooting
    private int direction;          //to control enemies direction
    private KeyManager keyManager;  // to manage the keyboard
    private boolean pause;          //to pause the game
    private int vidas;              //to store lives
    private int score;              //to store score
    //to control game status (0-not started, 1-playing, 2-gameOver, 3-Win)
    private int status;             
    private int level;              //to control the actual level being played
    private boolean gameOver;       //to control the game ending
    private Random r;               //to use a random number
    private Files file;              //File to save and load the game
    
    
    /**
     * to create title, width and height and set the game is still not running
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height  to set the height of the window
     */
    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        running = false;
        started = false;
        status = 0;
        keyManager = new KeyManager();
    }

    /**
     * Check if game is started
     * @return started
     */
    public boolean isStarted() {
        return started;
    }
    /**
     * Set if the game is started
     * @param started - boolean
     */
    public void setStarted(boolean started) {
        this.started = started;
    }
    /**
     * Get the score
     * @return score
     */
    public int getScore() {
        return score;
    }
    /**
     * Set the score
     * @param score 
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * Get enemies direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }
    /**
     * Set enemies direction
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }    
    /**
     * To get the width of the game window
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }
    /**
     * To get the height of the game window
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }
    /**
     * Get the player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * set the player
     * @param player 
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * To get the amoutn of lives of the game
     * @return an <code>int</code> value with vidas
     */
    public int getVidas() {
        return vidas;
    }
    /**
     * To set the amount of lives left
     * @param vidas 
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    /**
     * To get the game's status
     * @return status
     */
    public int getStatus() {
        return status;
    }
    /**
     * To set the game's status
     * @param status 
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * To get the actual level being played
     * @return level
     */
    public int getLevel() {
        return level;
    }
    /**
     * To set the actual level being played
     * @param level 
     */
    public void setLevel(int level) {
        this.level = level;
    }    
    
    /**
     * initializing the variables of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(700, getHeight()/2 - 90, 70, 180, this);
         bullets = new ArrayList<Ball>();  
         enemyBullets = new ArrayList<EnemyBullet>();
         obstacles = new ArrayList<Obstacle>();
         canShoot = true;
         shootTmpPl = 0;
         r = new Random();
         direction = 1;
         vidas = 100;
         score = 0;
         level = 1;
         gameOver = false;
         pause = false;
         display.getJframe().addKeyListener(keyManager);
    }
    /**
     * To restart the game when is over
     */
    public void restart() {
        continueGame();
        setStatus(0);
        setLevel(1);
        bullets.clear();
        enemyBullets.clear();
        canShoot = true;
        shootTmpPl = 0;
        r = new Random();
        direction = 1;
        vidas = 100;
        score = 0;
        gameOver = false;
        pause = false;
    }
    /**
     * To continue the game after loosing a live
     */
    public void continueGame() {
        player.setY(getHeight()/2 - 90);
        bullets.clear();
        enemyBullets.clear();
        setStarted(false);
    }
    /**
     * To run the game
     */
    @Override
    public void run() {
        init();
        // frames per second
        int fps = 50;
        // time for each tick in nano segs
        double timeTick = 1000000000 / fps;
        // initializing delta
        double delta = 0;
        // define now to use inside the loop
        long now;
        // initializing last time to the computer time in nanosecs
        long lastTime = System.nanoTime();
        while (running) {
            // setting the time now to the actual time
            now = System.nanoTime();
            // acumulating to delta the difference between times in timeTick units
            delta += (now - lastTime) / timeTick;
            // updating the last time
            lastTime = now;
            
            // if delta is positive we tick the game
            if (delta >= 1) {
                tick();
                render();
                delta --;
            }
        }
        stop();
    }
    /**
     * To get the key manager
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }
    /**
     * To tick the game
     */
    private void tick() {
        keyManager.tick();
        
        //pause and unpause the game
        if (this.getKeyManager().p) 
            pause = !pause;
        
        if(!this.isStarted()) {
            if (this.getKeyManager().l) {
                file.loadFile(this);
            }
        }
        
        if(pause && getKeyManager().g) {
            file.saveFile(this);
        }
        
        // starting the game
        if (this.getKeyManager().space && !this.isStarted()) {
            setStarted(true);
        }
        
        if ((gameOver || pause) && this.getKeyManager().r)
            restart();
        
        if (!pause && !gameOver) {
            if(this.isStarted()) {
                player.tick();
            }
            
            //player shooting
            if (canShoot) {
                if (getKeyManager().s) {
                    Ball bullet = new Ball(getPlayer().getX() - 15, 
                            getPlayer().getY() + 70, 15, 4, this);
                    bullets.add(bullet);
                    Assets.playerShot.play();
                    canShoot = false;
                }
            } else {
                shootTmpPl++;
            }
            //wait 1.5 seconds to shoot again
            if (shootTmpPl >= 25) {
                canShoot = true;
                shootTmpPl = 0;
            }                       
        }     
            
        //If lives == 0 game is over with status 2 (lose)
        if (vidas <= 0) {
            gameOver = true;
            vidas = 0;
            status = 2;
        }       
    }
    /**
     * To render the game
     */
    private void render() {
        // get the buffer strategy from the display
        bs = display.getCanvas().getBufferStrategy();
        /* if it is null, we define one with 3 buffers to display images of
        the game, if not null, then we display every image of the game but
        after clearing the Rectanlge, getting the graphic object from the 
        buffer strategy element. 
        show the graphic and dispose it to the trash system
        */
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
        }
        else
        {
            g = bs.getDrawGraphics();
            switch(getLevel()) {
                case 1:
                    g.drawImage(Assets.background1, 0, 0, width, height, null);
                    break;
                case 2:
                    g.drawImage(Assets.background2, 0, 0, width, height, null);
                    break;
                case 3:
                    g.drawImage(Assets.background3, 0, 0, width, height, null);
                    break;
                case 4:
                    g.drawImage(Assets.background4, 0, 0, width, height, null);
                    break;
            }
            g.drawImage(Assets.shadow, player.getX()-800, player.getY()-500, width*2, height*2, null);
            player.render(g);
            
            //draw the different menus depending on game status
            if(!this.isStarted())
                if (status == 0)
                    g.drawImage(Assets.start, width/2 - 250, 95, 500, 400, null);
                else if(status == 1)
                    g.drawImage(Assets.continueGame, getWidth()/2 - 125, 
                            getHeight()/2 - 75, 250, 150, null);                
            if(pause)
                g.drawImage(Assets.pause, getWidth()/2 - 200, 
                        getHeight()/2 - 175, 400, 350, null);
            if(gameOver) {
                if (status == 2)
                    g.drawImage(Assets.gameOver, getWidth()/2 - 200, 
                            getHeight()/2 - 175, 400, 350, null);
                else if (status == 3)
                    g.drawImage(Assets.win, getWidth()/2 - 200, 
                            getHeight()/2 - 175, 400, 350, null);
            }
            g.setColor(Color.white);
            g.setFont(new Font("default", Font.BOLD, 18));
            //draw the score and lives 
            g.drawString("Vida: " + Integer.toString(vidas) + "%", 25, getHeight()-15);
            g.drawString("Score: " + Integer.toString(score), 160, getHeight()-15);
            bs.show();
            g.dispose();
        }
       
    }
    
    /**
     * setting the thread for the game
     */
    public synchronized void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    
    /**
     * stopping the thread
     */
    public synchronized void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }           
        }
    }
}
