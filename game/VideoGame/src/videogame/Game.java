/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author antoniomejorado
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
    private boolean gameOver;       // to stop the game.
    private Player player;    // to use a player
    private ArrayList<Enemy> enemies; //To store an enemies collection.
    private ArrayList<Bullet> bullets;
    private KeyManager keyManager;  // to manage the keyboard
    
    
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
        gameOver = false;
        keyManager = new KeyManager();
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
     * initializing the display window of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(0, getHeight() - 100, 1, 100, 100, this);
         
         // create the Array collection of enemies.
         enemies = new ArrayList<Enemy>();
         // adding enemies to the collection.
        
         for(int i = 0; i < 10; i++){
             
             enemies.add(new Enemy((int) (Math.random() * (getWidth()-80)), 
                     -(int) (Math.random() * 2 * getHeight()), 80, 80, this));
             
         }
         
         //Create collection of bullets.
         bullets = new ArrayList<Bullet>();
         
         display.getJframe().addKeyListener(keyManager);
    }
    
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
        while (running && !gameOver) {
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

    public KeyManager getKeyManager() {
        return keyManager;
    }
    
    private void tick() {
        keyManager.tick();
        // avancing player with colision
        player.tick();
        
        //moving the enemies.
        Iterator itr = enemies.iterator();
        
        while(itr.hasNext()){
            
            Enemy enemy = (Enemy) itr.next();
            enemy.tick();
            
            //check if collision for gameover. 
            if(player.intersects(enemy)){
                
                gameOver = true;
            }
            
            //reset positions if gettings out of the screen.
            if(enemy.getY() >= getHeight()){
                enemy.setX((int) (Math.random() * (getWidth()-80)));
                enemy.setY(-(int) (Math.random() * 2 * (getHeight())));
            }
        }
        
        //check if a bullet must be added.
        if (this.getKeyManager().space){
             
             int posX = this.player.getX() + this.player.getWidth() / 2 - 10;
             int posY = this.player.getY();
             bullets.add(new Bullet(posX, posY, 20, 20, this));
         }
        
        //tick of bullets
        itr = bullets.iterator();
        while(itr.hasNext()){
            
            Bullet bullet = (Bullet) itr.next();
            bullet.tick();
            
            //Check if bullet collides.
            Iterator itr2 = enemies.iterator();
            while(itr2.hasNext()){
                
                Enemy enemy = (Enemy) itr2.next();
                
                if(bullet.intersects(enemy)){
                    
                    enemy.setX((int) (Math.random() * (getWidth()-80)));
                    enemy.setY(-(int) (Math.random() * 2 * (getHeight())));
                    bullets.remove(bullet);
                    //Updates iterators to avoid out-of-bounds
                    itr = bullets.iterator();
                }
            }
            
            //Checks to see if bullets are out of screen.
            if(bullet.getY() <= -20){
                bullets.remove(bullet);
                    //Updates iterators to avoid out-of-bounds
                itr = bullets.iterator();
            }
            
        }   
    }
    
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
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            
            //moving the enemies.
            Iterator itr = enemies.iterator();

            while(itr.hasNext()){

                Enemy enemy = (Enemy) itr.next();
                enemy.render(g);
            }
            
            //render bullets
            itr = bullets.iterator();
            while(itr.hasNext()){
                Bullet bullet = (Bullet) itr.next();
                bullet.render(g);
            }
            
            
            bs.show();
            g.dispose();
        }
    }
    
    /**
     * setting the thead for the game
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
