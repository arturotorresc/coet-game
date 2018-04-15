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
    private int drawing; // determine enemy's look.
    private Animation enemyUp;  // animation up.
    private Animation enemyDown; // animation down.
    private Animation enemyLeft; // animation left.
    private Animation enemyRight; // animation right.
    private char direction; // determine enemy's direction.
    private int patrolX; // determine the initial position to begin patrol in x.
    private int patrolY; // determine the initial position to begin patrol in y.
    private boolean returning; //indicates the state of the enemy's patrol.

    public Enemy(int x, int y, int width, int height,
            int ellipseWidth, int ellipseHeight, int type, int speed,
            int drawing, Game game) {
        
        super(x, y, width, height, ellipseWidth, ellipseHeight);
        this.type = type;
        this.game = game;
        this.speed = speed;
        this.drawing = drawing;
        this.direction = 'd';
        
        this.patrolX = this.getX();
        this.patrolY = this.getY();
        this.returning = false;
        
        int size = this.enemyCircleSize(type);
        this.setEllipseHeight(size);
        this.setEllipseWidth(size);
        this.setAnimations(drawing);
    }
    
    /**
     * enemy circle size will determine the diameter of the circle.
     * @param type
     * @return width
     */
    
    /**
     * set which animation the enemy will have.
     * @param drawing 
     */
    private void setAnimations(int drawing){
        switch(drawing){
            case 1:
                this.enemyUp = new Animation(Assets.monsterUp, 110);
                this.enemyDown = new Animation(Assets.monsterDown, 110);
                this.enemyRight = new Animation(Assets.monsterRight, 110);
                this.enemyLeft = new Animation(Assets.monsterLeft, 110);
                break;
            case 2:
                break;
        }
    }
    
    private int enemyCircleSize(int type){
        int size = 0;
        switch(type){
            case 1:
                size = 450;
                break;
            case 2:
                size = 550;
                break;
            case 3:
                size = 700;
                break;
        }
        return size;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
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
    
    private void patrol(int type){
        //type is what type of patrolling will the enemy execute.
        // 0 is horizontally
        // 1 is vertically  
        switch(type){
            case 0:
                if(this.getX() >= this.patrolX 
                        && this.getX() <= this.patrolX + 200
                        && !this.returning){
                    this.setX(this.getX() + speed);
                    this.setDirection('r');
                }else {
                    this.returning = true;
                    this.setX(this.getX() - speed);
                    this.setDirection('l');
                }
                
                if(this.getX() == this.patrolX){
                    this.returning = false;
                }
                break;
            case 1:
                if(this.getY() >= this.patrolY && this.getY() <= this.patrolY + 20){
                    this.setY(this.getY() + speed);
                    this.setDirection('d');
                }else if(this.getY() > this.patrolY + 20){
                    this.setY(this.getY() - speed);
                    this.setDirection('u');
                }
                break;
        }
        
    }
    
    private void chase(){
        if(game.getPlayer().getY() > this.getY()){
            this.setY(this.getY() + speed);
            this.setDirection('d');
        }else{
            this.setY(this.getY() - speed);
            this.setDirection('u');
        }

        if(game.getPlayer().getX() > this.getX()){
            this.setX(this.getX() + speed);
            this.setDirection('r');
        }else{
            this.setX(this.getX() - speed);
            this.setDirection('l');
        }
    }
    
    private boolean canChase(){
        return this.detects(game.getPlayer()) 
                && this.game.getPlayer().isVisible()
                && !this.intersects(this.game.getPlayer());
    }

    @Override
    public void tick() {
        //If the enemy detects the player, start chasing them.

        if(this.canChase()){
            this.chase();
            this.patrolX = this.getX();
            this.patrolY = this.getY();
        }else if(!this.detects(game.getPlayer())) {
            this.patrol(0);
        }   
    }

    @Override
    public void render(Graphics g) {
        if(this.detects(game.getPlayer()) && this.game.getPlayer().isVisible()){
            switch(this.getDirection()){
                case 'u':
                    g.drawImage(enemyUp.getCurrentFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'd':
                    g.drawImage(enemyDown.getCurrentFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'l':
                    g.drawImage(enemyLeft.getCurrentFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'r':
                    g.drawImage(enemyRight.getCurrentFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
            }
        }else{
            switch(this.getDirection()){
                case 'u':
                    g.drawImage(enemyUp.getIdleFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'd':
                    g.drawImage(enemyDown.getIdleFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'l':
                    g.drawImage(enemyLeft.getIdleFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
                case 'r':
                    g.drawImage(enemyRight.getIdleFrame(), getX(), getY()
                            , getWidth(), getHeight(), null);
                    break;
            }
        }    
    }
}
