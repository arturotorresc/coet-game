/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Coet game
 *
 * @author
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
    private Camera cam;
    private Powerup key;
    private Enemy enemy;
    private boolean hasKey;
    private boolean changeMusic;    // choose music depending on state.
    private Menu menu;
    private boolean scrollDown;        // flag to scroll through the menu.
    private boolean scrollUp;
    private Timer hitByEnemy;       // to display blood for a period of time.
    private TimerTask bloodAnimation; // task for the blood animation
    private boolean renderBlood; // flag to know if the player was hit.
    private boolean timerFlag;  // flag to know when to start a timer.
    private boolean sprintFlag; // flag to cooldown sprint by the player.
    public boolean switchMusicFlag; // flag to know if music can be switched.
    private SwitchMusic switchMusic; // to control the switching of music.
    private boolean gameSavedMsg; // to show a message when game is saved
    private int gameSavedMsgTmp; //to control the message showed temporarily
    private boolean mute; // to mute the game 
    private boolean restartMusicFlag; // to restartMusic
    private Timer introTimer;   // to show the intro if its a new game.
    private TimerTask showTimer; // show the timer.
    private boolean introFlag;  // introflag to only show the intro once.

    /**
     * to create title, width and height and set the game is still not running
     *
     * @param title to set the title of the window
     * @param width to set the width of the window
     * @param height to set the height of the window
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
     *
     * @return started
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * Set if the game is started
     *
     * @param started - boolean
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * to get if player haskey
     *
     * @return hasKey
     */
    public boolean isHasKey() {
        return hasKey;
    }

    /**
     * to set if player hasKey
     *
     * @param hasKey
     */
    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    /**
     * Get the score
     *
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the score
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get enemies direction
     *
     * @return direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Set enemies direction
     *
     * @param direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * To get the width of the game window
     *
     * @return an <code>int</code> value with the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * To get the height of the game window
     *
     * @return an <code>int</code> value with the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set the player
     *
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the enemy
     *
     * @return enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Set the enemy
     *
     * @param enemy
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * To get the array of obstacles
     *
     * @return obstacles
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    /**
     * To get the amoutn of lives of the game
     *
     * @return an <code>int</code> value with vidas
     */
    public int getVidas() {
        return vidas;
    }

    /**
     * To set the amount of lives left
     *
     * @param vidas
     */
    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    /**
     * To get the game's status
     *
     * @return status
     */
    public int getStatus() {
        return status;
    }

    /**
     * To set the game's status
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * To get the actual level being played
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * To set the actual level being played
     *
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
        player = new Player(getWidth() / 2, getHeight() / 2, 50, 62, 700, 700, this);
        obstacles = new ArrayList<Obstacle>();
        canShoot = true;
        shootTmpPl = 0;
        r = new Random();
        direction = 1;
        vidas = 3;
        score = 0;
        level = 1;
        gameOver = false;
        pause = false;
        display.getJframe().addKeyListener(keyManager);
        cam = new Camera(0, 0);
        enemy = new Enemy(getWidth() / 2 + 300, getHeight() / 2, 62, 77, 0, 0, 1, 1, 1, this);
        key = new Powerup(400, 200, 50, 50, 0, 0);
        hasKey = false;
        renderBlood = false;
        timerFlag = true;
        hitByEnemy = new Timer();
        scrollDown = true;
        scrollUp = true;
        switchMusicFlag = true;
        sprintFlag = true;
        mute = false;
        restartMusicFlag = false;
        introFlag = false;

        menu = new Menu();

        Assets.rain.setLooping(true);
        Assets.rain.play();
        Assets.ambientMusic.setLooping(true);
        Assets.ambientMusic.play();
    }

    /**
     * To restart the game when is over
     */
    public void restart() {
        continueGame();
        setStatus(0);
        setLevel(1);
        canShoot = true;
        shootTmpPl = 0;
        r = new Random();
        direction = 1;
        vidas = 100;
        score = 0;
        gameOver = false;
        pause = false;
        key = new Powerup(400, 200, 50, 50, 0, 0);
        hasKey = false;
        mute = false;
        menu = new Menu();
        restartMusicFlag = false;
    }

    /**
     * To continue the game after loosing a live
     */
    public void continueGame() {
        player.setY(getHeight() / 2 - 31);
        player.setX(getWidth() / 2 - 25);
        enemy.setX(getWidth() / 2 + 300);
        enemy.setY(getHeight() / 2);
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
                delta--;
            }
        }
        stop();
    }

    private void hitPlayer() {
        if(this.enemy.intersects(player)){
            this.setVidas(this.getVidas()-1);
            Assets.monsterAttack.play();
            if(this.enemy.getX() > this.player.getX()){
                this.enemy.setX(this.enemy.getX() + 100);
            } else {
                this.enemy.setX(this.enemy.getX() - 100);
            }
            renderBlood = true;
            if (timerFlag) {
                timerFlag = false;
                this.startBloodAnimation();

            }
        }
    }

    private void startBloodAnimation() {
        bloodAnimation = new TimerTask() {
            public void run() {
                renderBlood = false;
                timerFlag = true;
            }
        };
        hitByEnemy.schedule(bloodAnimation, 150);
    }

    private void scrollThroughMenu() {

        if (this.getKeyManager().down && scrollDown) {
            scrollDown = false;
            this.menu.setVar(this.menu.getVar() + 1);
        }

        if (this.getKeyManager().up && scrollUp) {
            scrollUp = false;
            this.menu.setVar(this.menu.getVar() - 1);
        }

        if (!this.getKeyManager().down) {
            scrollDown = true;
        }

        if (!this.getKeyManager().up) {
            scrollUp = true;
        }

        if (this.menu.getVar() > 4) {
            this.menu.setVar(1);
        }

        if (this.menu.getVar() < 1) {
            this.menu.setVar(4);
        }

        // starting the game
        if (this.getKeyManager().enter && !this.isStarted() && menu.getVar() == 1) {
            this.intro();
        }
        if (this.getKeyManager().enter && !this.isStarted() && menu.getVar() == 2) {
            file.loadFile(this);
            setStarted(true);
        }

    }

    private void startChaseMusic() {
        if (this.enemy.detects(this.player) && switchMusicFlag) {
            switchMusicFlag = false;
            switchMusic = new SwitchMusic(Assets.ambientMusic,
                    Assets.chaseMusic, true, 25600);

            Timer switchingFlag = new Timer();
            TimerTask switchFlagTask = new TimerTask() {
                public void run() {
                    switchMusicFlag = true;
                }
            };

            switchingFlag.schedule(switchFlagTask, 25600);
        }
    }
    
    private void muteB() {
        if (this.getKeyManager().mute && !mute) {
            Assets.chaseMusic.stop();
            Assets.rain.stop();
            Assets.ambientMusic.stop();
            Assets.monsterAttack.stop();
            mute = true;
        } else if (this.getKeyManager().mute && mute) {
            Assets.ambientMusic.play();
            mute = false;
            restartMusicFlag = true;
        }
    }
    
    private void intro() {
        /*
        Timer introTimer;   // to show the intro if its a new game.
        TimerTask showTimer; // show the timer.
        introFlag;
        */
        introFlag = true;
        introTimer = new Timer();
        showTimer = new TimerTask() {
            public void run() {
                introFlag = false;
                setStarted(true);
            }
        };
        introTimer.schedule(showTimer, 15000);
    }
    private void sprint() {
        if (this.getKeyManager().isSprint() && sprintFlag) {
            sprintFlag = false;
            this.player.sprint();

            Timer activateSprintFlag = new Timer();
            TimerTask sprintFlagTask = new TimerTask() {
                public void run() {
                    sprintFlag = true;
                }
            };

            // Reactivate the sprint flag every 15 seconds.
            activateSprintFlag.schedule(sprintFlagTask, 15000);
        }
    }

    /**
     * To get the key manager
     *
     * @return keyManager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * To tick the game
     */
    private void tick() {
        cam.tick(player);

        keyManager.tick();

        muteB();

        if (restartMusicFlag) {
            restartMusicFlag = false;
            Assets.ambientMusic.getLooping();
            Assets.ambientMusic.play();
            Assets.rain.getLooping();
            Assets.rain.play();

        }

        //pause and unpause the game
        if (this.getKeyManager().p) {
            pause = !pause;
        }

        if (pause && getKeyManager().g) {
            file.saveFile(this);
            gameSavedMsg = true;
        }

        //if game is saved start a timer to show message
        if (gameSavedMsg) {
            gameSavedMsgTmp++;
        }

        if (gameSavedMsgTmp >= 75) {
            gameSavedMsg = false;
            gameSavedMsgTmp = 0;
        }

        if (hasKey) {
            key.setX(player.getX() + 10);
            key.setY(player.getY() - 240);
        }

        //Checks to see whether the enemy attacked the player. 
        this.hitPlayer();

        scrollThroughMenu();
        startChaseMusic();

        //Allows sprinting by the player.
        sprint();

        if ((gameOver || pause) && this.getKeyManager().r) {
            restart();
        }

        if (!pause && !gameOver) {
            if (this.isStarted()) {
                player.tick();
                enemy.tick();
            }
            //wait 1.5 seconds to shoot again
            if (shootTmpPl >= 25) {
                canShoot = true;
                shootTmpPl = 0;
            }
        }

        if (this.getKeyManager().isHide()) {

        }

        //If lives == 0 game is over with status 2 (lose)
        if (vidas <= 0) {
            gameOver = true;
            vidas = 0;
            status = 2;
        }
        if (player.intersects(key)) {
            hasKey = true;
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
        } else {
            g = bs.getDrawGraphics();
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(cam.getX(), cam.getY()); //begin of cam

            switch (getLevel()) {
                case 1:
                    g.drawImage(Assets.main_lvl, 0, 0, 3200, 1536, null);
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

            if (this.player.getY() > this.enemy.getY()) {
                enemy.render(g);
                player.render(g);
            } else {
                player.render(g);
                enemy.render(g);
            }
            key.render(g);
            if (this.getPlayer().isVisible()) {
                g.drawImage(Assets.shadow, player.getX() - 1500 - player.getWidth(), player.getY() - 950 - player.getHeight(),
                        this.getWidth() * 4, this.getHeight() * 4, null);
            } else {
                g.drawImage(Assets.hidden, player.getX() - 1500 - player.getWidth(), player.getY() - 950 - player.getHeight(),
                        this.getWidth() * 4, this.getHeight() * 4, null);
            }

            //Render blood animation.
            if (renderBlood) {
                g.drawImage(Assets.blood, player.getX() - 1500 - player.getWidth(), player.getY() - 950 - player.getHeight(),
                        this.getWidth() * 4, this.getHeight() * 4, null);
            }
            //draw the different menus depending on game status

            if(!this.isStarted()){
                menu.render(g);
            }
            
            if(introFlag){
                g.drawImage(Assets.intro,0, 0, 800, 500, null);
            }
//                if (status == 0)
//                    g.drawImage(Assets.start, width/2 - 250, 95, 500, 400, null);
//                else if(status == 1)
//                    g.drawImage(Assets.continueGame, getWidth()/2 - 125, 
//                            getHeight()/2 - 75, 250, 150, null);                
            if (pause) {
                g.drawImage(Assets.pause, getWidth() / 2 - 200,
                        getHeight() / 2 - 175, 400, 350, null);
            }
            if (gameOver) {
                /*if (status == 2)
                    g.drawImage(Assets.gameOver, getWidth()/2 - 200, 
                            getHeight()/2 - 175, 400, 350, null);
                else if (status == 3)
                    g.drawImage(Assets.win, getWidth()/2 - 200, 
                            getHeight()/2 - 175, 400, 350, null);
                 */
            }
            if (mute) {
                g.drawImage(Assets.mute, player.getX() - 200, player.getY() + 215, 30, 30, null);
            }
            //draw the score and lives 
            if(this.isStarted()){
                if(this.vidas == 3){
                    g.drawImage(Assets.full_hearts, player.getX()+255, player.getY()-260,
                        125, 75, null);
                }else if(this.vidas == 2){
                    g.drawImage(Assets.half_hearts, player.getX()+255, player.getY()-260,
                        125, 75, null);
                }else if(this.vidas == 1){
                    g.drawImage(Assets.one_heart, player.getX()+255, player.getY()-260,
                        125, 75, null);
                }
            }
            g.setColor(Color.white);
            g.setFont(new Font("default", Font.BOLD, 18));
            if(gameSavedMsg) {
                g.drawString("Game saved!", player.getX()-35, player.getY()-150);
            }

            bs.show();
            g2d.translate(-cam.getX(), -cam.getY()); //end of cam
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
