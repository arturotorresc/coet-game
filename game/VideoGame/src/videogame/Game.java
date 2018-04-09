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
    private Shield shieldOne;       // to use a shield
    private Shield shieldTwo;       // to use a shield
    private ArrayList<Ball> bullets;  //to use bullets
    private ArrayList<EnemyBullet> enemyBullets; //to use enemy bullets
    private int enemiesShot;        //to store the amount of enemies shot
    private ArrayList<Brick> bricks; //to use enemies
    private int direction;          //to control enemies direction
    private boolean moveX;          // to control enemies movement on X
    private KeyManager keyManager;  // to manage the keyboard
    private boolean pause;          //to pause the game
    private int vidas;              //to store lives
    private int score;              //to store score
    //to control game status (0-not started, 1-playing, 2-gameOver, 3-Win)
    private int status;             
    private boolean gameOver;       //to control the game ending
    private int shootTmpEn;           //timer to control enemy shooting
    private int shootTmpPl;           //timer to control player shooting
    private Random r;               //to use a random number
    private Files file;              //File to save and load the game
    private int bonusTmp;           //timer to control bonus ship
    private boolean bonus;          //to store if got bonus
    private Bonus bonusShip;        //to store the bonus ship
    private int bonusImgTmp;        //timer to control bonus image being showed
    
    
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
     * Get the enemies arraylist
     * @return bricks
     */
    public ArrayList<Brick> getBricks() {
        return bricks;
    }
    /**
     * Set the enemies arraylist
     * @param bricks 
     */
    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }
    /**
     * get the amount of enemies shot
     * @return enemiesShot
     */
    public int getEnemiesShot() {
        return enemiesShot;
    }
    /**
     * set the amount of enemies shot
     * @param enemiesShot 
     */
    public void setEnemiesShot(int enemiesShot) {
        this.enemiesShot = enemiesShot;
    }    
    /**
     * Get the shield one
     * @return shieldOne
     */
    public Shield getShieldOne() {
        return shieldOne;
    }
    /**
     * Set the shield one
     * @param shieldOne 
     */
    public void setShieldOne(Shield shieldOne) {
        this.shieldOne = shieldOne;
    }
    /**
     * Get the shield two
     * @return shieldTwo
     */
    public Shield getShieldTwo() {
        return shieldTwo;
    }
    /**
     * Set the shield two
     * @param shieldTwo 
     */
    public void setShieldTwo(Shield shieldTwo) {
        this.shieldTwo = shieldTwo;
    }    
    /**
     * Check if enemies should move on X
     * @return moveX
     */
    public boolean isMoveX() {
        return moveX;
    }
    /**
     * Set if the enemies should move on X
     * @param moveX 
     */
    public void setMoveX(boolean moveX) {
        this.moveX = moveX;
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
     * initializing the variables of the game
     */
    private void init() {
         display = new Display(title, getWidth(), getHeight());  
         Assets.init();
         player = new Player(700, getHeight()/2 - 90, 70, 180, this);
         shieldOne = new Shield(575, getHeight()/3 - 75, 30, 100, this);
         shieldTwo = new Shield(575, getHeight()*2/3 - 50, 30, 100, this);
         bonusShip = new Bonus(25, getHeight()+100, 70, 60, this);
         bonusShip.setMove(false);
         bullets = new ArrayList<Ball>();  
         enemyBullets = new ArrayList<EnemyBullet>();
         bricks = new ArrayList<Brick>(); 
         int width_brick = 45;
         int height_brick = 120;
         for (int i = 0; i < 9; i++) {
             for(int j = 0; j < 3; j++) {
                 Brick brick = new Brick(i * width_brick + 20, j * height_brick + 70, width_brick, height_brick, this);
                 bricks.add(brick);
             }
         }
         bonusTmp = 0;
         enemiesShot = 0;
         canShoot = true;
         shieldOne.setShotsReceived(0);
         shieldTwo.setShotsReceived(0);
         r = new Random();
         direction = 1;
         vidas = 100;
         score = 0;
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
        bricks.clear();
        bullets.clear();
        enemyBullets.clear();        
        int width_brick = 45;
        int height_brick = 120;
        for (int i = 0; i < 9; i++) {
             for(int j = 0; j < 3; j++) {
                 Brick brick = new Brick(i * width_brick + 20, j * height_brick + 70, width_brick, height_brick, this);
                bricks.add(brick);
            }
        }
        enemiesShot = 0;
        bonusTmp = 0;
        bonusShip.setY(getHeight()+100);
        bonusShip.setMove(false);
        canShoot = true;
        shieldOne.setY(getHeight()/3 - 75);
        shieldTwo.setY(getHeight()*2/3 - 50);
        shieldOne.setShotsReceived(0);
        shieldTwo.setShotsReceived(0);
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
                shootTmpEn++;
                bonusTmp++;
                bonusShip.tick();
                player.tick();
                bullets.forEach((bullet) -> {
                    bullet.tick();
                });
                enemyBullets.forEach((bull) -> {
                    bull.tick();
                });            
                bricks.forEach((brick) -> {
                    brick.tick();
                });
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
           
            for (int j = 0; j < bullets.size(); j++) {
                Ball bullet = (Ball) bullets.get(j);
                //clearing bullets when crashing with shield
                if (bullet.intersects(shieldOne) || bullet.intersects(shieldTwo)) {
                    bullets.remove(bullet);
                    j--;
                }
                //checking if got bonus
                if(bullet.intersects(bonusShip)) {
                    score += 500;
                    bonusShip.setY(getHeight()+100);
                    bonusShip.setMove(false);
                    bonus = true;
                    bonusTmp = 0;
                }
                //damaging enemies with player's bullets
                for (int i = 0; i < bricks.size(); i++) {
                    Brick brick = (Brick) bricks.get(i);
                    if(bullet.intersects(brick) && (bullet.getY() > brick.getY() + 5
                            && bullet.getY() < brick.getY() + brick.getHeight() - 5)
                            && (bullet.getX() >= brick.getX() + brick.getWidth() -5)) {
                        bricks.remove(brick);
                        enemiesShot++;  //count the amount of enemies shot
                        bullets.remove(bullet);
                        score += 100;
                        Assets.playerHit.play();
                        i--;
                        j--;
                    }                
                }                
            }
            
            //random aliens shooting
            if (shootTmpEn >= 50) {
                for(int i = 0; i < bricks.size(); i++) {
                    if (r.nextInt(27) == i) {
                        EnemyBullet bullet = new EnemyBullet(bricks.get(i).getX()+60,
                                bricks.get(i).getY(), 15, 4, this);
                        enemyBullets.add(bullet);
                        Assets.alienShot.play();
                    }
                }
                shootTmpEn = 0;
            }
            
            //damaging shield and player with enemy bullets
            for (int i = 0; i < enemyBullets.size(); i++) {
                EnemyBullet bullet = (EnemyBullet) enemyBullets.get(i);
                if (bullet.intersects(shieldOne)) {
                    enemyBullets.remove(bullet);
                    shieldOne.setShotsReceived(shieldOne.getShotsReceived()+1);
                    Assets.shieldHit.play();
                    i--;
                } else if(bullet.intersects(shieldTwo)) {
                    enemyBullets.remove(bullet);
                    shieldTwo.setShotsReceived(shieldTwo.getShotsReceived()+1);
                    Assets.shieldHit.play();
                    i--;
                }
                if (bullet.intersects(player)) {
                    enemyBullets.remove(bullet);
                    Assets.playerHit.play();
                    setVidas(getVidas() - 15);
                    i--;
                }
                if(bullet.getX() > getWidth()) {
                    enemyBullets.remove(bullet);
                    i--;
                }
            }
            //set bonus ship to move at the second 20
            if(bonusTmp >= 1000) {
                bonusShip.setMove(true);
                bonusTmp = 0;
            }
        }     
        //move shields out the window when destroyed
        if (shieldOne.getShotsReceived() >= 5) {
            shieldOne.setY(-300);
        }
        if (shieldTwo.getShotsReceived() >= 5) {
            shieldTwo.setY(-300);
        }
            
        //If lives == 0 game is over with status 2 (lose)
        if (vidas <= 0) {
            gameOver = true;
            vidas = 0;
            status = 2;
        }       
        //If enemiesShot == 27(all bricks destroyed)
        //game is over with status 3(win)
        if (enemiesShot >= 27) {
            status = 3;
            gameOver = true;
        }
        
        //moving aliens
        bricks.forEach((brick) -> {
            if(brick.getY() < 0) {
                setDirection(1);
                setMoveX(true);
            } else if(brick.getY() + brick.getHeight() >= getHeight()) {
                setDirection(-1);
                setMoveX(true);
            }
            if(brick.getX() + brick.getWidth() >= player.getX()) {
                setVidas(0);
            }
        });        
        if (isMoveX()) {
            bricks.forEach((brick) -> {
               brick.setX(brick.getX() + 15);
            });
            setMoveX(false);
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
            g.drawImage(Assets.background, 0, 0, width, height, null);
            player.render(g);
            bonusShip.render(g);
            shieldOne.render(g);
            shieldTwo.render(g);
            bullets.forEach((bullet) -> {
                bullet.render(g);
            });
            bricks.forEach((brick) -> {
                brick.render(g);
            });
            enemyBullets.forEach((bull) -> {
                bull.render(g);
            });
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
            if(bonus) {
                g.drawImage(Assets.bonus, getWidth()/2 - 200, 
                        getHeight()/2 - 175, 400, 350, null);
                bonusImgTmp++;
            }
            if(bonusImgTmp >= 75) {
                bonus = false;
                bonusImgTmp = 0;
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
