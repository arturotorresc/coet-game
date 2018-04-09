/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * KeyManager class to manage the keys on the game
 * @author diego martinez
 */
public class KeyManager implements KeyListener {
    
    public boolean up;      // flag to move up the player
    public boolean down;    // flag to move down the player
    public boolean left;    // flag to move left the player
    public boolean right;   // flag to move right the player
    public boolean space;   //flag to space
    public boolean p;       //flag to pause the game
    public boolean r;       //flag to restart the game
    public boolean s;       //flag to shoot
    public boolean g;       //flag to save the game
    public boolean l;       //flag to loaf the game

    private boolean keys[];  // to store all the flags for every key
    
    public KeyManager() {
        keys = new boolean[256];
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }
    /**
     * To manage keys pressed
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // set true to every key pressed except P and R
        if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_R
                || e.getKeyCode() == KeyEvent.VK_S)
            keys[e.getKeyCode()] = false;
        else 
            keys[e.getKeyCode()] = true;
    }
    /**
     * To manage keys released
     * @param e 
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // set false to every key released except P and R
        if (e.getKeyCode() == KeyEvent.VK_P || e.getKeyCode() == KeyEvent.VK_R
                || e.getKeyCode() == KeyEvent.VK_S)
            keys[e.getKeyCode()] = true;
        else 
            keys[e.getKeyCode()] = false;
    }
    
    /**
     * to enable or disable moves on every tick
     */
    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        space = keys[KeyEvent.VK_SPACE];
        p = keys[KeyEvent.VK_P];
        r = keys[KeyEvent.VK_R];
        s = keys[KeyEvent.VK_S];
        g = keys[KeyEvent.VK_G];
        l = keys[KeyEvent.VK_L];
        keys[KeyEvent.VK_S] = false;
        keys[KeyEvent.VK_P] = false;
        keys[KeyEvent.VK_R] = false;
    }
}
