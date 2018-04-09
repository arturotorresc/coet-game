/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class to use the player in the game
 * @author diego martinez
 */
public class Player extends Item{

    private int direction;
    private int width;
    private int height;
    private Game game;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    /**
     * To get player's direction
     * @return direction
     */
    public int getDirection() {
        return direction;
    }
    /**
     * To set player's direction
     * @param direction 
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }
    /**
     * To tick the player
     */
    @Override
    public void tick() {
        // moving player depending on flags
        if (game.getKeyManager().up) {
           setY(getY() - 5);
        }
        if (game.getKeyManager().down) {
           setY(getY() + 5);
        }
        if (game.getKeyManager().left) {
            setX(getX() - 5);
        }
        if (game.getKeyManager().right) {
            setX(getX() + 5);
        }
        // collision with walls
        if (getY() + 180 >= game.getHeight()) {
            setY(game.getHeight() - 180);
        }
        else if (getY() <= 0) {
            setY(0);
        }
        if (getX() + 20 >= game.getWidth()) {
            setX(game.getWidth() - 20);
        }
        else if (getX() <= 0) {
            setX(0);
        }
    }
    /**
     * To render the player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rick_gun, getX(), getY(), getWidth(), getHeight(), null);        
    }
}
