/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package videogame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.awt.geom.Ellipse2D;
/**
 * Class to manage items on the game
 * @author diego martinez
 */
public abstract class Item {
    protected int x;        // to store x position
    protected int y;        // to store y position
    protected int width;        // to store width
    protected int height;        // to store height
    protected int ellipseWidth;  // to store ellipse Width;
    protected int ellipseHeight; // to store ellipse Height;
    
    /**
     * Set the initial values to create the item
     * @param x <b>x</b> position of the object
     * @param y <b>y</b> position of the object
     */
    public Item(int x, int y, int width, int height, int ellipseWidth,
            int ellipseHeight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.ellipseWidth = ellipseWidth;
        this.ellipseHeight = ellipseHeight;
    }
    /**
     * To get item height
     * @return height
     */
    public int getHeight() {
        return height;
    }
    /**
     * To get item width
     * @return width
     */
    public int getWidth() {
        return width;
    }
    /**
     * Get x value
     * @return x 
     */
    public int getX() {
        return x;
    }

    /**
     * Get y value
     * @return y 
     */
    public int getY() {
        return y;
    }

    /**
     * Set x value
     * @param x to modify
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Set y value
     * @param y to modify
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * To set item height
     * @param height 
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * To set item width
     * @param width 
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public int getEllipseHeight() {
        return ellipseHeight;
    }

    public int getEllipseWidth() {
        return ellipseWidth;
    }

    public void setEllipseHeight(int ellipseHeight) {
        this.ellipseHeight = ellipseHeight;
    }

    public void setEllipseWidth(int ellipseWidth) {
        this.ellipseWidth = ellipseWidth;
    }
    /**
     * To get item bounds
     * @return rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), (getWidth() / 2) + (getWidth() / 4)
                , (getHeight() / 2) + (getHeight() / 4));
    }
    
    /**
     * To check if one item intersects another
     * @param obj
     * @return boolean if intersects
     */
    public boolean intersects(Object obj) {
        return (obj instanceof Item && this.getBounds().intersects(((Item) obj).getBounds()));
    }
    /**
     * to get ellipse bounds
     * @return ellipse
     */
    public Ellipse2D.Double getRadius(){
        return new Ellipse2D.Double((getX() - getEllipseWidth() / 2),
                (getY() - getEllipseHeight() / 2), getEllipseWidth(),
                getEllipseHeight());
    }
    
    public boolean detects(Object obj){
       return(obj instanceof Item && 
               this.getRadius().intersects(((Item) obj).getBounds()));
    }
    
    /**
     * To update positions of the item for every tick
     */
    public abstract void tick();
    
    /**
     * To paint the item
     * @param g <b>Graphics</b> object to paint the item
     */
    public abstract void render(Graphics g);
}
