/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;
import java.awt.Graphics;

/**
 *
 * @author Alicia
 */
public class Menu{
 
    private int var; 
    private Player player;
    private Game g;

    
    public Menu(Game g){ 
        this.var = 1;
        this.g = g;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }
    

       
    public void render(Graphics g) {
      
        
        switch(var){
            case 1:
              g.drawImage(Assets.startMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 2: 
              g.drawImage(Assets.continueMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 3: 
              g.drawImage(Assets.creditsMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 4: 
              g.drawImage(Assets.helpMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 5: 
              g.drawImage(Assets.exitMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            default:
             g.drawImage(Assets.exitMenu, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
        }
        

    }
    
}
