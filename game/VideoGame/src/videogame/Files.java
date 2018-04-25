/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class to save and load files for saving the game
 * @author diego martinez
 */
public class Files {
    
    public static void saveFile(Game game){
        // define objects
        PrintWriter printWriter;
        try {
            // creating file object.
            printWriter = new PrintWriter(new FileWriter("data.txt"));
            // writing the game
            // player position
            printWriter.println("" + game.getPlayer().getX() + "," + 
                    game.getPlayer().getY());
            // player's lives
            printWriter.println("" + game.getVidas());
            // enemies' position
            printWriter.println("" + game.getEnemy().getX() + "," +
                    game.getEnemy().getY());
            // actual level
            printWriter.println("" + game.getLevel());
            // has key? (boolean)
            printWriter.println("" + game.isHasKey());
            
            printWriter.close();
            
        } catch(IOException ioe) {
            System.out.println("Se te llenó el disco duro. " + ioe.toString());
        }
    }
    
    public static void loadFile(Game game){
        BufferedReader bufferedReader;
        
        try {
            
            bufferedReader = new BufferedReader(new FileReader("data.txt"));
            //get the first line.
            String line = bufferedReader.readLine();
            //getting every token from the line.
            String[] tokens = line.split(",");
            // Setting the x and y values of the player.
            game.getPlayer().setX(Integer.parseInt(tokens[0]));
            game.getPlayer().setY(Integer.parseInt(tokens[1]));
            
            line = bufferedReader.readLine();
            // Setting lives for game.
            game.setVidas(Integer.parseInt(line));
            
            line = bufferedReader.readLine();
            tokens = line.split(",");
            // Setting the x and y values of the enemy
            game.getEnemy().setX(Integer.parseInt(tokens[0]));
            game.getEnemy().setY(Integer.parseInt(tokens[1]));
            
            line = bufferedReader.readLine();
            // Setting the actual level
            game.setLevel(Integer.parseInt(line));
            
            line = bufferedReader.readLine();
            // Setting if player has key
            game.setHasKey(Boolean.parseBoolean(line));
            
            
        } catch(IOException ioe){
            System.out.println("Juego no ha sido guardado" + ioe.toString());
        }
        
    }
}
