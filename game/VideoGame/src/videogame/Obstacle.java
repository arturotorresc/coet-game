/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Random
 */
public class Obstacle extends Item {
    
    private boolean hideable;

    public Obstacle(int x, int y, int width, int height, boolean hideable,
            int ellipseWidth, int ellipseHeight) {
        super(x, y, width, height, ellipseWidth, ellipseHeight);
        this.hideable = hideable;
    }
    
    /**
     * This function is to determine whether the player can
     * hide in the obstacle.
     * @return hideable
     */

    public boolean isHideable() {
        return hideable;
    }
    
    /**
     * modify the attribute.
     * @param hideable 
     */

    public void setHideable(boolean hideable) {
        this.hideable = hideable;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) { 
        g.setColor(Color.red);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
