/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 * Class to manage the bonus item on the game
 * @author Diego martinez
 */
public class Bonus extends Item{

    private Game game;
    private boolean move;
    /**
     * Bonus constructor
     * @param x x position
     * @param y y position
     * @param width x size
     * @param height y size
     * @param game 
     */
    public Bonus(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    /**
     * Check if ship should move
     * @return move
     */
    public boolean isMove() {
        return move;
    }
    /**
     * Set if ship should move
     * @param move 
     */
    public void setMove(boolean move) {
        this.move = move;
    }
    
    /**
     * To tick the ball
     */
    @Override
    public void tick() {
        if(isMove())
            setY(getY() - 1);
        
        if(getY() < 0) {
            setMove(false);
            setY(game.getHeight()+100);
        }
    }
    /**
     * To render the ball
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bonusShip, getX(), getY(), getWidth(), getHeight(), null);
    }
}
