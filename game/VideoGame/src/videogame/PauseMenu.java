/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
public class PauseMenu{
 
    private int var; 
    private Player player;

    
    public PauseMenu(){ 
        this.var = 1;
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
              g.drawImage(Assets.pauseGame,0, 0, 800, 500, null);
            break;
            case 2: 
              g.drawImage(Assets.pauseContinue, 0, 0, 800, 500, null);
            break;
            case 3: 
              g.drawImage(Assets.pauseMain, 0, 0, 800, 500, null);
            break;
            default:
              g.drawImage(Assets.pauseGame,0, 0, 800, 500, null);
            break;
        }
        

    }
    
}

