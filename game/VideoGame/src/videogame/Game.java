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
    private Camera cam;             // to use the cam
    private ArrayList<Powerup> keys;            // to use the key
    private Enemy enemy;            //to use the enemy
    private boolean hasKey;
    private int hasKeyTmp;
    private boolean changeMusic;    // choose music depending on state.
    private Menu menu;              //to use the main menu
    private PauseMenu pauseMenu;    //to use the pause menu
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

    private boolean keysBlocked; //to block keys when moving automatically
    private int keysBlockTmp; // to control the blocking of keys temporarily
    private int cantKeys; //to control how many keys the player haves
    private boolean keysAlert; //to alert of missing keys
    private int keysAlertTmp; //to control showing of message
    private boolean keysAlert2; //to alert of missing keys
    private int keysAlertTmp2; //to control showing of message

    private boolean creditsFlag;  // creditsFlag 
    private int credits;

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
        keysBlocked = false;
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
     * To get how many keys the player has
     * @return cantKeys
     */
    public int getCantKeys() {
        return cantKeys;
    }
    /**
     * to set how many keys the player has
     * @param cantKeys 
     */
    public void setCantKeys(int cantKeys) {
        this.cantKeys = cantKeys;
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
     * Initialize all objects for collisions
     */
    private void initObstacles() {
        obstacles = new ArrayList<Obstacle>();        
        obstacles.add(new Obstacle(0, 0, 3200, 20, false, 0, 0));
        obstacles.add(new Obstacle(-10, 0, 1, 1536, false, 0, 0));
        obstacles.add(new Obstacle(3199, 0, 1, 1536, false, 0, 0));
        
        obstacles.add(new Obstacle(0, 20, 490, 60, false, 0, 0)); // 1 p
        obstacles.add(new Obstacle(495, 130, 10, 50, true, 0, 0)); // 1 ob
        obstacles.add(new Obstacle(240, 90, 200, 10, false, 0, 0)); // 1 ob
        obstacles.add(new Obstacle(135, 80, 70, 28, true, 0, 0)); // 1 ob
        
        obstacles.add(new Obstacle(525, 0, 40, 340, false, 0, 0)); // 2 p
        obstacles.add(new Obstacle(1265, 0, 35, 370, false, 0, 0)); // 2 p
        
        obstacles.add(new Obstacle(1305, 20, 1050, 70, false, 0, 0)); // 3 p
        obstacles.add(new Obstacle(2375, 20, 45, 135, false, 0, 0)); // 3 p
        obstacles.add(new Obstacle(2375, 325, 45, 135, false, 0, 0)); // 3 p
        obstacles.add(new Obstacle(1305, 325, 475, 45, false, 0, 0)); //3 p
        obstacles.add(new Obstacle(1900, 325, 475, 45, false, 0, 0)); // 3 p
        for(int i = 0; i < 8; i++) {
            obstacles.add(new Obstacle(1390+(i*128), 120, 5, 25, true, 0, 0)); // 3 ob
            obstacles.add(new Obstacle(1390+(i*128), 242, 5, 20, true, 0, 0)); // 3 ob
        }
        for(int i = 0; i < 7; i++) {
            obstacles.add(new Obstacle(1455+(i*128), 105, 5, 5, false, 0, 0)); // 3 ob
        }
        
        
        obstacles.add(new Obstacle(2420, 20, 780, 60, false, 0, 0)); // 4 p
        obstacles.add(new Obstacle(2885, 85, 200, 10, false, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(3085, 90, 115, 10, false, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(3130, 180, 70, 50, true, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(3120, 180, 1, 125, false, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(3120, 390, 85, 110, true, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(2825, 510, 375, 80, false, 0, 0)); // 4 p
        obstacles.add(new Obstacle(2325, 510, 380, 80, false, 0, 0)); // 4 p
        obstacles.add(new Obstacle(2570, 395, 90, 5, true, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(2857, 395, 90, 5, true, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(2775, 230, 50, 40, true, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(2430, 330, 15, 35, false, 0, 0)); // 4 ob
        obstacles.add(new Obstacle(2430, 430, 15, 35, false, 0, 0)); // 4 ob        
        
        obstacles.add(new Obstacle(0, 480, 180, 80, false, 0, 0)); // 5 p
        obstacles.add(new Obstacle(300, 480, 460, 80, false, 0, 0)); // 5 p
        obstacles.add(new Obstacle(330, 560, 110, 25, true, 0, 0)); // 5 ob
        obstacles.add(new Obstacle(525, 560, 110, 25, true, 0, 0)); // 5 ob
        obstacles.add(new Obstacle(750, 570, 15, 170, false, 0, 0)); // 5 ob
        obstacles.add(new Obstacle(425, 715, 5, 45, true, 0, 0)); // 5 ob
        obstacles.add(new Obstacle(685, 715, 5, 45, true, 0, 0)); // 5 ob
        obstacles.add(new Obstacle(520, 690, 70, 5, true, 0, 0)); //5 ob
        obstacles.add(new Obstacle(520, 815, 70, 5, true, 0, 0)); // 5 ob
        
        obstacles.add(new Obstacle(815, 510, 690, 120, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(1580, 510, 670, 120, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(1505, 510, 75, 80, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(2280, 510, 45, 430, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(780, 482, 45, 410, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(2067, 776, 81, 20, true, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1977, 730, 60, 70, true, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1800, 632, 10, 118, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1700, 745, 90, 10, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1380, 650, 105, 5, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1078, 1536, 695, 5, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(1780, 1390, 490, 155, false, 0, 0)); //6 p
        obstacles.add(new Obstacle(2215, 960, 55, 95, false, 0, 0)); // 6 p
        obstacles.add(new Obstacle(989, 631, 15, 75, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(940, 700, 55, 5, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(940, 705, 15, 160, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(940, 870, 245, 5, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1200, 870, 3, 545, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1205, 1415, 345, 10, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1550, 1260, 5, 160, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1550, 1250, 85, 10, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1740, 1250, 345, 10, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(2090, 1090, 5, 160, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1870, 1090, 230, 10, false, 0, 0)); //6 ob
        obstacles.add(new Obstacle(1870, 900, 5, 190, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1710, 900, 160, 5, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1710, 880, 5, 20, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(2060, 1165, 65, 10, true, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1287, 1100, 485, 5, false, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1430, 1045, 60, 65, true, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(910, 780, 55, 10, true, 0, 0)); // 6 ob
        obstacles.add(new Obstacle(1240, 1375, 10, 55, true, 0, 0)); // 6 ob
        
        obstacles.add(new Obstacle(2720, 530, 15, 25, false, 0, 0)); // 7 p
        obstacles.add(new Obstacle(2800, 530, 20, 25, false, 0, 0)); // 7 p
        obstacles.add(new Obstacle(2400, 590, 25, 35, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2485, 590, 25, 30, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2565, 590, 20, 35, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2635, 590, 10, 25, false, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2860, 590, 35, 20, false, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(3050, 590, 35, 20, false, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2990, 658, 10, 35, false, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2925, 680, 5, 10, false, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2890, 740, 135, 30, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2945, 770, 20, 5, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2935, 840, 50, 30, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2550, 840, 50, 30, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2550, 740, 100, 30, true, 0, 0)); // 7 ob
        obstacles.add(new Obstacle(2560, 770, 20, 5, true, 0, 0)); // 7 ob
        
        obstacles.add(new Obstacle(0, 1466, 1070, 50, false, 0, 0)); // 8 p
        obstacles.add(new Obstacle(0, 995, 1070, 50, false, 0, 0)); // 8 p
        obstacles.add(new Obstacle(360, 1045, 65, 15, true, 0, 0)); // 8 ob
        obstacles.add(new Obstacle(585, 1045, 75, 15, true, 0, 0)); // 8 ob
        obstacles.add(new Obstacle(390, 1060, 15, 15, true, 0, 0)); // 8 ob
        obstacles.add(new Obstacle(635, 1060, 15, 15, true, 0, 0)); // 8 ob
        obstacles.add(new Obstacle(0, 1175, 10, 20, true, 0, 0));// 8 ob
        obstacles.add(new Obstacle(0, 1265, 10, 25, true, 0, 0)); // 8 ob
                
        obstacles.add(new Obstacle(2370, 1466, 800, 50, false, 0, 0)); //9 p
        obstacles.add(new Obstacle(2310, 1100, 45, 436, false, 0, 0)); //9 p
        obstacles.add(new Obstacle(2355, 960, 375, 115, false, 0, 0)); // 9 p
        obstacles.add(new Obstacle(2795, 960, 375, 115, false, 0, 0)); // 9 p
        obstacles.add(new Obstacle(2730, 960, 65, 60, false, 0, 0)); // 9 p
        obstacles.add(new Obstacle(2540, 1090, 100, 5, false, 0, 0)); // 9 ob
        obstacles.add(new Obstacle(2355, 1100, 90, 15, false, 0, 0)); //9 ob
        obstacles.add(new Obstacle(3090, 1100, 90, 15, false, 0, 0)); //9 ob
        obstacles.add(new Obstacle(2450, 1230, 345, 110, true, 0, 0)); //9 ob
        
    }
    
    /**
     * initializing the variables of the game
     */
    private void init() {
        display = new Display(title, getWidth(), getHeight());
        Assets.init();
        player = new Player(getWidth() / 2, getHeight() / 2, 50, 62, 700, 700, this);
        initObstacles();
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
        keys = new ArrayList<Powerup>();
        keys.add(new Powerup(485, 1110, 35, 35, 0, 0, this));
        keys.add(new Powerup(2513, 1385, 35, 35, 0, 0, this));
        keys.add(new Powerup(2745, 755, 35, 35, 0, 0, this));
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
        creditsFlag = false;
        menu = new Menu(this);
        pauseMenu = new PauseMenu(this);
        cantKeys = 0;
        
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
        vidas = 3;
        score = 0;
        gameOver = false;
        pause = false;
        keys = new ArrayList<Powerup>();
        keys.add(new Powerup(485, 1110, 35, 35, 0, 0, this));
        keys.add(new Powerup(2513, 1385, 35, 35, 0, 0, this));
        keys.add(new Powerup(2745, 755, 35, 35, 0, 0, this));
        hasKey = false;
        cantKeys = 0;
        mute = false;
        menu = new Menu(this);
        restartMusicFlag = false;
        pauseMenu = new PauseMenu(this);
        creditsFlag = false;
        Assets.gameoverMusic.stop();
        Assets.ambientMusic.play();
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

        if (this.menu.getVar() > 5) {
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
        if (this.getKeyManager().enter && !this.isStarted() && menu.getVar() == 3){
             creditsFlag = true;
         }
        if(this.getKeyManager().enter && !this.isStarted() && menu.getVar() == 5){
            System.exit(0);
        }

    }
    
    private void scrollThroughPauseMenu() {

        if (this.getKeyManager().down && scrollDown) {
            scrollDown = false;
            this.pauseMenu.setVar(this.pauseMenu.getVar() + 1);
        }

        if (this.getKeyManager().up && scrollUp) {
            scrollUp = false;
            this.pauseMenu.setVar(this.pauseMenu.getVar() - 1);
        }

        if (!this.getKeyManager().down) {
            scrollDown = true;
        }

        if (!this.getKeyManager().up) {
            scrollUp = true;
        }

        if (this.pauseMenu.getVar() > 3) {
            this.pauseMenu.setVar(1);
        }

        if (this.pauseMenu.getVar() < 1) {
            this.pauseMenu.setVar(2);
        } 
        

    }

    private void startChaseMusic() {
        if (this.enemy.detects(this.player) && switchMusicFlag && !mute) {
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
     * To get if keys are blocked
     * @return keysBlocked
     */
    public boolean isKeysBlocked() {
        return keysBlocked;
    }
    /**
     * To set if keys are blocked
     * @param keysBlocked 
     */
    public void setKeysBlocked(boolean keysBlocked) {
        this.keysBlocked = keysBlocked;
    }
    
    

    /**
     * To tick the game
     */
    private void tick() {
        cam.tick(player);

        keyManager.tick();

        muteB();

//        if (restartMusicFlag) {
//            restartMusicFlag = false;
//            Assets.ambientMusic.getLooping();
//            Assets.ambientMusic.play();
//            Assets.rain.getLooping();
//            Assets.rain.play();
//
//        }

        //pause and unpause the game
        if (this.getKeyManager().p) {
            pause = !pause;
        }
        
        
        if(pause){
            scrollThroughPauseMenu();
            
            if (this.getKeyManager().enter && pauseMenu.getVar() == 1){
                file.saveFile(this);
                gameSavedMsg = true;
            }
            if(this.getKeyManager().enter && pauseMenu.getVar() == 2){
                pause = !pause;
            }
            if(this.getKeyManager().enter && pauseMenu.getVar() == 3){
                restart();
            }
        }
        
        if (gameSavedMsg)
            gameSavedMsgTmp++;

        if (gameSavedMsgTmp >= 70) {
            gameSavedMsg = false;
            gameSavedMsgTmp = 0;
        }           
          
        if (creditsFlag) {
            credits++;
         }
        if (credits >= 150) {
            creditsFlag = false;
            credits = 0;
        }

        //Checks to see whether the enemy attacked the player. 
        this.hitPlayer();

       if(!mute){
            scrollThroughMenu();
            startChaseMusic();
       }

        //Allows sprinting by the player.
        sprint();

        if ((gameOver || pause) && this.getKeyManager().r) {
            restart();
        }

        if (!pause && !gameOver) {
            if (this.isStarted()) {
                player.tick();
               // enemy.tick();
            }
            //wait 1.5 seconds to shoot again
            if (shootTmpPl >= 25) {
                canShoot = true;
                shootTmpPl = 0;
            }
        }
        
        //If lives == 0 game is over with status 2 (lose)
        if (vidas == 0) {
            gameOver = true;
            vidas = -1;
            status = 2;
            this.vidas = -1;
            Assets.gameoverMusic.setLooping(true);
            Assets.chaseMusic.stop();
            Assets.gameoverMusic.play();
        }
               

        obstacles.forEach((obs) ->{
           if(player.intersects(obs)) {
               switch(player.getDirection()) {
                   case 'u':
                       player.setY(player.getY()+3);
                       break;
                   case 'd':
                       player.setY(player.getY()-3);
                       break;
                   case 'r':
                       player.setX(player.getX()-3);
                       break;
                   case 'l':
                       player.setX(player.getX()+3);
                       break;
               }
               if (this.getKeyManager().isHide() && obs.isHideable()) {
                    player.setVisible(false);
               }
           }
        });
        
        if ((player.getX() > 2730 && player.getX() < 2745) &&
                (player.getY() > 1030 && player.getY() < 1049) &&
                player.getDirection() == 'u') {
            keysBlocked = true;
            player.setX(1529);
            player.setY(605);
            player.setDirection('d');            
        }
        else if ((player.getX() > 1521 && player.getX() < 1536) &&
                (player.getY() > 600 && player.getY() < 615) &&
                player.getDirection() == 'u') {
            if(cantKeys == 0) {
                keysAlert2 = true;
            } else {
                keysBlocked = true;
                player.setX(2744);
                player.setY(1046);
                player.setDirection('d');
            }            
        }
        
        if(keysAlert2)
            keysAlertTmp2++;
        
        if(keysAlertTmp2 >= 75) {
            keysAlert2 = false;
            keysAlertTmp2 = 0;
        }
        
        if (keysBlocked) {
            keysBlockTmp++;
            player.setY(player.getY()+2);
        }
        
        if (keysBlockTmp >= 25) {
            keysBlocked = false;
            keysBlockTmp = 0;
        }
        
        keys.forEach((key) -> {
            key.tick();
            if (player.intersects(key)) {
                cantKeys++;                    
                key.setMoving(true, cantKeys);
            }            
        }); 
              

        if ((player.getX() > 10 && player.getX() < 45) && player.getY() < 85) {
            if (cantKeys < 3) {
                keysAlert = true;
            }
            //AQUI VA EL FINAL!!!!
        }
        
        if(keysAlert)
            keysAlertTmp++;
        
        if(keysAlertTmp >= 75) {
            keysAlert = false;
            keysAlertTmp = 0;
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
            
          //  obstacles.forEach((obs) -> {
          //      obs.render(g);
          //  });

            if (this.player.getY() > this.enemy.getY()) {
                enemy.render(g);
                player.render(g);
            } else {
                player.render(g);
                enemy.render(g);
            }
            keys.forEach((key) -> {
                key.render(g);
            });
            
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

            if(!this.isStarted() || (pauseMenu.getVar() == 3 && this.getKeyManager().enter)){
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
           
            
            if (gameOver) {
                g.drawImage(Assets.gameOver, -220, -150, 1768, 829, null);
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
            if(creditsFlag){
               g.drawImage(Assets.creditspic, 0, 0, 800, 500, null);

            }
             if (pause) {
                pauseMenu.render(g);
            }
            g.setColor(Color.white);
            g.setFont(new Font("default", Font.BOLD, 18));
            
            if(keysAlert) {
                if(3 - cantKeys > 1)
                    g.drawString("" + (3 - cantKeys) + " keys missing!", 
                            player.getX()-30, player.getY()-30);
                else
                    g.drawString("" + (3 - cantKeys) + " key missing!", 
                            player.getX()-30, player.getY()-30);
            }
            if(keysAlert2) {
                g.drawString("1 key missing!", player.getX()-30, player.getY()-30);
            }
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
