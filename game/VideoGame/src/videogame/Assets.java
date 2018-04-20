/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 * Class to store the game assets
 * @author diego martinez
 */
public class Assets {
    //backgrounds
    public static BufferedImage background1; // to store background image for level 1
    public static BufferedImage background2; // to store background image for level 2
    public static BufferedImage background3; // to store background image for level 3
    public static BufferedImage background4; // to store background image for level 4
    public static BufferedImage shadow;      // to store the shadow surrounding the player
    public static BufferedImage hidden;
    public static BufferedImage blood;      // to store blood when enemy is hit.
    //Game objects
    public static BufferedImage player;     // to store the player's image
    public static BufferedImage playerUp[]; // to store animations going up.
    public static BufferedImage playerDown[]; //to store animations going down.
    public static BufferedImage playerLeft[]; //to store animations going left.
    public static BufferedImage playerRight[]; //to store animations going right.
    public static BufferedImage monster; // to store monter's image.
    public static BufferedImage key; // to store key's image.
    public static BufferedImage monsterUp[]; // to store animations going up.
    public static BufferedImage monsterDown[]; //to store animations going down.
    public static BufferedImage monsterLeft[]; //to store animations going left.
    public static BufferedImage monsterRight[]; //to store animations going right.
    
    
    //menu related images
    public static BufferedImage start;      //to store the starting image
    public static BufferedImage pause;      //to store the pausing image
    public static BufferedImage startMenu;      //to store the pausing image
    public static BufferedImage continueMenu;      //to store the pausing image
    public static BufferedImage creditsMenu;      //to store the pausing image
    public static BufferedImage helpMenu;      //to store the pausing image
    
    public static BufferedImage continueGame;   //to store the continue game image
    public static BufferedImage gameOver;    //to store the gameOver image
    public static BufferedImage win;        //to store the winning image
    
    //sounds
    public static SoundClip rain;           // to store rain ambient sound.
    public static SoundClip ambientMusic; // to store ambient music.

    /**
     * initializing the images of the game
     */
    public static void init() {
        background1 = ImageLoader.loadImage("/images/nivel1.png");
        background2 = ImageLoader.loadImage("/images/nivel2.png");
        background3 = ImageLoader.loadImage("/images/nivel3.png");
        background4 = ImageLoader.loadImage("/images/nivel4.png");
        blood = ImageLoader.loadImage("/images/blood.png");
        shadow = ImageLoader.loadImage("/images/shadow.png");
        hidden = ImageLoader.loadImage("/images/hidden.png");
        start = ImageLoader.loadImage("/images/mortyStart.png");
        pause = ImageLoader.loadImage("/images/pause.png");
        player = ImageLoader.loadImage("/images/player.png");
        monster = ImageLoader.loadImage("/images/monster.png");
        continueGame = ImageLoader.loadImage("/images/continue.png");
        key = ImageLoader.loadImage("/images/key.png");
        gameOver = ImageLoader.loadImage("/images/gameOver.png");
        // menu
        
        startMenu = ImageLoader.loadImage("/images/nGameMenu.jpg");
        continueMenu = ImageLoader.loadImage("/images/continueMenu.jpg");
        creditsMenu = ImageLoader.loadImage("/images/creditsMenu.jpg");
        helpMenu = ImageLoader.loadImage("/images/helpMenu.jpg");
        //win = ImageLoader.loadImage("/images/win.png");
        
        
        rain = new SoundClip("/sounds/rain.aiff");
        ambientMusic = new SoundClip("/sounds/ambientMusic.wav");
        
        Spreadsheet playerSs = new Spreadsheet(player);
        Spreadsheet monsterSs = new Spreadsheet(monster);
        playerUp = new BufferedImage[3];
        playerDown = new BufferedImage[3];
        playerLeft = new BufferedImage[3];
        playerRight = new BufferedImage[3];
        
        monsterUp = new BufferedImage[3];
        monsterDown = new BufferedImage[3];
        monsterLeft = new BufferedImage[3];
        monsterRight = new BufferedImage[3];
        
        for(int i = 0; i < 3; i++){
            playerUp[i] = playerSs.crop(i * 47, 192, 50, 62);
            playerDown[i] = playerSs.crop(i * 47, 0, 50, 62);
            playerLeft[i] = playerSs.crop(i * 47, 66, 50, 62);
            playerRight[i] = playerSs.crop(i * 47, 130, 50, 62);
        }
        
        for(int i = 0; i < 3; i++){
            monsterUp[i] = monsterSs.crop(i * 47, 192, 50, 62);
            monsterDown[i] = monsterSs.crop(i * 47, 0, 50, 62);
            monsterLeft[i] = monsterSs.crop(i * 47, 66, 50, 62);
            monsterRight[i] = monsterSs.crop(i * 47, 130, 50, 62);
        }
        
    }    
}
