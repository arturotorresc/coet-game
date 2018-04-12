/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Random
 */
public class Animation {
    
    private int speed; // for the speed of every frame.
    private int index; // to get the index of next frame to paint.
    private long lastTime; // to save the previous time of the animation.
    private long timer; // to accumulate the time of the animation.
    private BufferedImage[] frames; // to store every frame.
    
    public Animation(BufferedImage[] frames, int speed){
        this.frames = frames; // storing frames.
        this.speed = speed; // assigning speed.
        index = 0;  // start frames in 0.
        timer = 0;  // start timer in 0.
        lastTime = System.currentTimeMillis(); // store last time .
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
    
    public BufferedImage getIdleFrame(){
        this.setIndex(0);
        return frames[1];
    }
    
    public void tick(){
        
        timer += System.currentTimeMillis() - lastTime;
        
        lastTime = System.currentTimeMillis();
        
        if(timer > speed){
            index++;
            timer = 0;
        }
        
        if(index >= frames.length){
            index = 0;
        }
        
    }
    
    
}
