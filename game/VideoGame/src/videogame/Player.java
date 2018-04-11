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
    private boolean visible; // This is false when the player is hidden.
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        visible = true;
    }
    
    /**
     * modifies the visible attribute to true or false.
     * @param visible 
     */

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * isVisible
     * @return true or false depending on whether the player is hidden.
     */
    
    public boolean isVisible() {
        return visible;
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
           this.setVisible(true);
        }
        if (game.getKeyManager().down) {
           setY(getY() + 5);
           this.setVisible(true);
        }
        if (game.getKeyManager().left) {
            setX(getX() - 5);
            this.setVisible(true);
        }
        if (game.getKeyManager().right) {
            setX(getX() + 5);
            this.setVisible(true);
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
    
    public void hide(Obstacle o){
        if(o.intersects(this) && o.isHideable()){
            this.setVisible(false);
        }
    }
    /**
     * To render the player
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        if(this.isVisible()){
            g.drawImage(Assets.player, getX(), getY(), getWidth(), getHeight(), null);
        }        
    }
}
