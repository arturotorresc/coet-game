/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;

/**
 *
 * @author Random
 */
public class Enemy extends Item{
    private Game game;
    
    public Enemy(int x, int y, int width, int height, Game game) {
        super(x, y, height, width);
        this.game = game;
    }

    @Override
    public void tick() {
       
        setY(getY() + 1);
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.enemy, getX(), getY(), getWidth(), getHeight(), null);
    }
}