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
    //characters 
    public static BufferedImage background; // to store background image
    public static BufferedImage rick;     // to store the player's image
    public static BufferedImage rick_gun;   //to store the player shooting image
    public static BufferedImage bad;     //to store the brick image
    public static BufferedImage shield;     //to store the shield image
    public static BufferedImage shield2;     //to store the shield image
    public static BufferedImage shield3;     //to store the shield image
    public static BufferedImage shield4;     //to store the shield image
    public static BufferedImage shield5;     //to store the shield image
    public static BufferedImage bonusShip;   //to store the bonus ship image
    
    //menu related images
    public static BufferedImage start;      //to store the starting image
    public static BufferedImage pause;      //to store the pausing image
    public static BufferedImage continueGame;   //to store the continue game image
    public static BufferedImage gameOver;    //to store the gameOver image
    public static BufferedImage win;        //to store the winning image
    public static BufferedImage bonus;      //to store the bonus image
    
    //sounds
    public static SoundClip alienShot;      //to store alien shot sound
    public static SoundClip playerShot;     //to store player shot sound
    public static SoundClip shieldHit;      //to store shield hit sound
    public static SoundClip playerHit;      //to store player hit sound

    /**
     * initializing the images of the game
     */
    public static void init() {
        background = ImageLoader.loadImage("/images/background.png");
        bad = ImageLoader.loadImage("/images/nebulon.png");
        start = ImageLoader.loadImage("/images/mortyStart.png");
        pause = ImageLoader.loadImage("/images/pause.png");
        rick = ImageLoader.loadImage("/images/rick.png");
        rick_gun = ImageLoader.loadImage("/images/rick_gun.png");
        shield = ImageLoader.loadImage("/images/portal.png");
        shield2 = ImageLoader.loadImage("/images/portal2.png");
        shield3 = ImageLoader.loadImage("/images/portal3.png");
        shield4 = ImageLoader.loadImage("/images/portal4.png");
        shield5 = ImageLoader.loadImage("/images/portal5.png");
        continueGame = ImageLoader.loadImage("/images/continue.png");
        gameOver = ImageLoader.loadImage("/images/gameOver.png");
        win = ImageLoader.loadImage("/images/win.png");
        bonusShip = ImageLoader.loadImage("/images/ship.png");
        bonus = ImageLoader.loadImage("/images/bonus.png");
        
        alienShot = new SoundClip("/sounds/alienBeam.wav");
        playerShot = new SoundClip("/sounds/bulletSound.wav");
        shieldHit = new SoundClip("/sounds/shieldSound.wav");
        playerHit = new SoundClip("/sounds/hitmarkerSound.wav");
    }    
}
