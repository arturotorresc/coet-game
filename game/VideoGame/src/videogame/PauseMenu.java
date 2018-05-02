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
    private Game g;

    
    public PauseMenu(Game g){ 
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
              g.drawImage(Assets.pauseGame, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 2: 
              g.drawImage(Assets.pauseContinue, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            case 3: 
              g.drawImage(Assets.pauseMain, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
            default:
              g.drawImage(Assets.pauseGame, this.g.getPlayer().getX() - 400, 
                      this.g.getPlayer().getY() - 250, 800, 500, null);
            break;
        }
        

    }
    
}

