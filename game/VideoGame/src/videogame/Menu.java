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
 
    private int var = 1; 
    private Player player;

    
    public Menu(){        
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
              g.drawImage(Assets.startMenu,0, 0, 800, 500, null);
            break;
            case 2: 
              g.drawImage(Assets.continueMenu, 0, 0, 800, 500, null);
            break;
            case 3: 
              g.drawImage(Assets.creditsMenu, 0, 0, 800, 500, null);
            break;
            case 4: 
              g.drawImage(Assets.helpMenu, 0, 0, 800, 500, null);
            break;
            default:
             g.drawImage(Assets.startMenu, 0, 0, 800, 500, null);
            break;
        }
        

    }
    
}
