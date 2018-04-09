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
public class Brick extends Item{

    private int width;
    private int height;
    private Game game;
    
    public Brick(int x, int y, int width, int height, Game game) {
        super(x, y, width, height);
        this.game = game;
    }
    
    /**
     * To tick the brick
     */
    @Override
    public void tick() {
        setY(getY() + game.getDirection());
    }
    /**
     * To render the brick
     * @param g 
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bad, getX(), getY(), getWidth(), getHeight(), null);
    }
}
