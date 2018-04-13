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
public class Enemy extends Item {
    
    private int type; // to determine which kind of enemy it is.
    private Game game; // to store the game.
    private int speed;  // movement speed of the enemy
    private BufferedImage drawing; // determine enemy's look.

    public Enemy(int x, int y, int width, int height,
            int ellipseWidth, int ellipseHeight, int type, int speed,
            BufferedImage drawing, Game game) {
        
        super(x, y, width, height, ellipseWidth, ellipseHeight);
        this.type = type;
        this.game = game;
        this.speed = speed;
        this.drawing = drawing;
        
        int size = this.enemyCircleSize(type);
        this.setEllipseHeight(size);
        this.setEllipseWidth(size);
    }
    
    /**
     * enemy circle size will determine the diameter of the circle.
     * @param type
     * @return width
     */
    
    public int enemyCircleSize(int type){
        int size = 0;
        switch(type){
            case 1:
                size = 350;
                break;
            case 2:
                size = 400;
                break;
            case 3:
                size = 500;
                break;
        }
        return size;
    }
    
    /**
     * canMove will detect if there are obstacles for the enemy in its path.
     * @return true or false depending on whether it can move.
     */
   /* public boolean canMove(){
        for(Obstacle obstacle: game.getObstacles()){
            if(this.detects(obstacle)){
                
            }
        }
    } */

    @Override
    public void tick() {
        //If the enemy detects the player, start chasing them.
        if(this.detects(game.getPlayer())){
            
            System.out.println("Detected\n");
            
            if(game.getPlayer().getX() > this.getX()){
                this.setX(this.getX() + speed);
            }else{
                this.setX(this.getX() - speed);
            }
            
            if(game.getPlayer().getY() > this.getY()){
                this.setY(this.getY() + speed);
            }else{
                this.setY(this.getY() - speed);
            }
            
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(drawing, getX(), getY(), getWidth(), getHeight(), null);
    }
    
    
}
