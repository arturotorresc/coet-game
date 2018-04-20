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
public class Powerup extends Item {
    private int x; 
    private int y;

    public Powerup(int x, int y, int width, int height, int ellipseWidth, int ellipseHeight) {
        super(x, y, width, height, ellipseWidth, ellipseHeight);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.key, getX(), getY(), getWidth(), getHeight(), null);//To change body of generated methods, choose Tools | Templates.
    }
    
}
