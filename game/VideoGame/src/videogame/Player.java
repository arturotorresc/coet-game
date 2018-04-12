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
    
    private int width;
    private int height;
    private char direction; // To know which direction the player is facing.
    private Game game;
    private boolean visible; // This is false when the player is hidden.
    
    private Animation playerUp;
    private Animation playerDown;
    private Animation playerRight;
    private Animation playerLeft;
    
    public Player(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
        visible = true;
        
        this.playerUp = new Animation(Assets.playerUp, 110);
        this.playerDown = new Animation(Assets.playerDown, 110);
        this.playerLeft = new Animation(Assets.playerLeft, 110);
        this.playerRight = new Animation(Assets.playerRight, 110);
        this.direction = 'u';
          
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
    public char getDirection() {
        return direction;
    }
    /**
     * To set player's direction
     * @param direction 
     */
    public void setDirection(char direction) {
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
           this.setDirection('u');
           this.playerUp.tick();
        }
        if (game.getKeyManager().down) {
           setY(getY() + 5);
           this.setVisible(true);
           this.setDirection('d');
           this.playerDown.tick();
        }
        if (game.getKeyManager().left) {
            setX(getX() - 5);
            this.setVisible(true);
            this.setDirection('l');
            this.playerLeft.tick();
        }
        if (game.getKeyManager().right) {
            setX(getX() + 5);
            this.setVisible(true);
            this.setDirection('r');
            this.playerRight.tick();
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
            if(game.getKeyManager().right || game.getKeyManager().left
                    || game.getKeyManager().down || game.getKeyManager().up){
                switch(this.getDirection()){
                    case 'u':
                        g.drawImage(playerUp.getCurrentFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'd':
                        g.drawImage(playerDown.getCurrentFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'l':
                        g.drawImage(playerLeft.getCurrentFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'r':
                        g.drawImage(playerRight.getCurrentFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                }
            }else{
                switch(this.getDirection()){
                    case 'u':
                        g.drawImage(playerUp.getIdleFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'd':
                        g.drawImage(playerDown.getIdleFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'l':
                        g.drawImage(playerLeft.getIdleFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                    case 'r':
                        g.drawImage(playerRight.getIdleFrame(), getX(), getY()
                                , getWidth(), getHeight(), null);
                        break;
                }
            }
        }        
    }
}
