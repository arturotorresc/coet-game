/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Class to use the ball(bullets) in the game
 * @author Diego martinez
 */
public class Ball extends Item{

    private Game game;
    /**
     * Ball constructor
     * @param x x position
     * @param y y position
     * @param width x size
     * @param height y size
     * @param game 
     */
    public Ball(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    
    /**
     * To tick the ball
     */
    @Override
    public void tick() {
        setX(getX() - 5);
    }
    /**
     * To render the ball
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
