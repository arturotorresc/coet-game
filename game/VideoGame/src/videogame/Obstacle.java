/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Random
 */
public class Obstacle extends Item {
    
    private boolean hideable;
    private BufferedImage drawing;

    public Obstacle(int x, int y, int width, int height, boolean hideable, BufferedImage drawing) {
        super(x, y, width, height);
        this.hideable = hideable;
        this.drawing = drawing;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(drawing, getX(), getY(), getWidth(), getHeight(), null);//To change body of generated methods, choose Tools | Templates.
    }
    
}
