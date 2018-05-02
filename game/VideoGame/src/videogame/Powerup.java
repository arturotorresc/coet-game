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
    private Game g;
    private boolean moving;
    private int cantKeys;

    public Powerup(int x, int y, int width, int height, int ellipseWidth, int ellipseHeight, Game g) {
        super(x, y, width, height, ellipseWidth, ellipseHeight);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.g = g;
        this.moving = false;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving, int cantKeys) {
        this.moving = moving;
        this.cantKeys = cantKeys;
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
        if(this.isMoving()) {
            this.setX(g.getPlayer().getX()+(30*this.cantKeys) - 50);
            this.setY(g.getPlayer().getY()- 40);
        }            
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.key, getX(), getY(), getWidth(), getHeight(), null);//To change body of generated methods, choose Tools | Templates.
    }
    
}
