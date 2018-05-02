/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.Point;
import static java.lang.Math.abs;

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
    private Point patrolA; // determine point A for patrol.
    private Point patrolB; // determine point B for patrol.
    private char activePatrol;   // determines if patrol is in point A or B
    private boolean wasChasing; // determine if the enemy chased the player.

    public Enemy(int x, int y, int width, int height,
            int ellipseWidth, int ellipseHeight, int type, int speed,
            int drawing, Game game) {
        
        super(x, y, width, height, ellipseWidth, ellipseHeight);
        this.type = type;
        this.game = game;
        this.speed = speed;
        this.drawing = drawing;
        this.direction = 'd';
        
        int typeOfPatrol = ThreadLocalRandom.current().nextInt(0, 2);
        this.patrolA = new Point(100, 100);
        if(typeOfPatrol == 0){
            this.patrolB = new Point(300, 100);
        }else{
            this.patrolB = new Point(100, 300);
        }
        
        int size = this.enemyCircleSize(type);
        this.setEllipseHeight(size);
        this.setEllipseWidth(size);
        this.setAnimations(drawing);
        
        this.wasChasing = false;
        this.activePatrol = 'A';
    }

    public Point getPatrolA() {
        return patrolA;
    }

    public void setPatrolA(Point patrolA) {
        this.patrolA = patrolA;
    }

    public Point getPatrolB() {
        return patrolB;
    }

    public void setPatrolB(Point patrolB) {
        this.patrolB = patrolB;
    }
       
    
    /**
     * set which animation the enemy will have.
     * @param drawing 
     */
    private void setAnimations(int drawing){
        switch(drawing){
            case 1:
                this.enemyUp = new Animation(Assets.monsterUp, 180);
                this.enemyDown = new Animation(Assets.monsterDown, 180);
                this.enemyRight = new Animation(Assets.monsterRight, 180);
                this.enemyLeft = new Animation(Assets.monsterLeft, 180);
                break;
            case 2:
                break;
        }
    }
    
    /**
     * enemy circle size will determine the diameter of the circle.
     * @param type
     * @return width
     */
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
                size = 950;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Animation getEnemyUp() {
        return enemyUp;
    }

    public void setEnemyUp(Animation enemyUp) {
        this.enemyUp = enemyUp;
    }

    public Animation getEnemyDown() {
        return enemyDown;
    }

    public void setEnemyDown(Animation enemyDown) {
        this.enemyDown = enemyDown;
    }

    public Animation getEnemyLeft() {
        return enemyLeft;
    }

    public void setEnemyLeft(Animation enemyLeft) {
        this.enemyLeft = enemyLeft;
    }

    public Animation getEnemyRight() {
        return enemyRight;
    }

    public void setEnemyRight(Animation enemyRight) {
        this.enemyRight = enemyRight;
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
    
    private boolean returnToStation(){
        return (this.getX() != patrolA.getX() || this.getY() != patrolA.getY())
                && (this.wasChasing || 
               (this.getX() == patrolB.getX() && this.getY() == patrolB.getY()));
    }
    
    private void goToPoint(Point p){
        if(p.getY() > this.getY()){
            this.setY(this.getY() + speed);
            this.setDirection('d');
            this.enemyDown.tick();
        }else if(p.getY() < this.getY()){
            this.setY(this.getY() - speed);
            this.setDirection('u');
            this.enemyUp.tick();
        }else if(p.getX() > this.getX()){
            this.setX(this.getX() + speed);
            this.setDirection('r');
            this.enemyRight.tick();
        }else if(p.getX() < this.getX()){
            this.setX(this.getX() - speed);
            this.setDirection('l');
            this.enemyLeft.tick();
        }
    }
        
    private void patrol(){                 
        if(activePatrol == 'B') {
            this.goToPoint(patrolA);
        }
        else if (activePatrol == 'A')
            this.goToPoint(patrolB);
        
        this.wasChasing = false;
    }
    
    private void chase(){
        if (abs(game.getPlayer().getX() - this.getX()) < 200) {
            if(game.getPlayer().getY() > this.getY()){
                this.setY(this.getY() + speed);
                this.setDirection('d');
                this.enemyDown.tick();           
            }else if(game.getPlayer().getY() < this.getY()){
                this.setY(this.getY() - speed);
                this.setDirection('u');
                this.enemyUp.tick();            
            } else if(game.getPlayer().getX() > this.getX()){
                this.setX(this.getX() + speed);
                this.setDirection('r');
                this.enemyRight.tick();
            } else if(game.getPlayer().getX() < this.getX()){
                this.setX(this.getX() - speed);
                this.setDirection('l');
                this.enemyLeft.tick();
            }
        } else {
            if(game.getPlayer().getX() > this.getX()){
                this.setX(this.getX() + speed);
                this.setDirection('r');
                this.enemyRight.tick();
            } else if(game.getPlayer().getX() <= this.getX()){
                this.setX(this.getX() - speed);
                this.setDirection('l');
                this.enemyLeft.tick();
            } else if(game.getPlayer().getY() > this.getY()){
                this.setY(this.getY() + speed);
                this.setDirection('d');
                this.enemyDown.tick();           
            }else if(game.getPlayer().getY() <= this.getY()){
                this.setY(this.getY() - speed);
                this.setDirection('u');
                this.enemyUp.tick();            
            }
        }
        
        this.wasChasing = true;
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
        }else if(!this.detects(game.getPlayer())) {
            this.patrol();
        }   
        
        if (this.getX() == patrolA.getX() && this.getY() == patrolA.getY())
            activePatrol = 'A';
        if (this.getX() == patrolB.getX() && this.getY() == patrolB.getY())
            activePatrol = 'B';
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
