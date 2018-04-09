/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class to use the bricks for the game
 * @author Diego martinez
 */
public class Shield extends Item{

    private int shotsReceived;
    private int width;
    private int height;
    private Game game;
    /**
     * Shield's constructor
     * @param x x position
     * @param y y position
     * @param width x size
     * @param height y size
     * @param game 
     */
    public Shield(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    /**
     * Get the shot received by the shield
     * @return shotsReceived
     */
    public int getShotsReceived() {
        return shotsReceived;
    }
    /**
     * Set the shots received by the shield
     * @param shotsReceived 
     */
    public void setShotsReceived(int shotsReceived) {
        this.shotsReceived = shotsReceived;
    }
    
    /**
     * To tick the brick
     */
    @Override
    public void tick() {
    }
    /**
     * To render the brick
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        //draw the shield depending on how damaged it is
        switch(shotsReceived) {
            case 0:
                g.drawImage(Assets.shield, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 1:
                g.drawImage(Assets.shield2, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 2:
                g.drawImage(Assets.shield3, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 3:
                g.drawImage(Assets.shield4, getX(), getY(), getWidth(), getHeight(), null);
                break;
            case 4:
                g.drawImage(Assets.shield5, getX(), getY(), getWidth(), getHeight(), null);
                break;
            default:                
        }       
    }
}