/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

/**
 *
 * @author Alicia
 */
public class Camera {
    public float x; 
    public float y; 
    
    public Camera(float x, float y){
        this.x = x; 
        this.y = y; 
        
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void tick(Player player){
        x = -player.getX() + 400;
        y = -player.getY() + 250;
    }    
    
}
