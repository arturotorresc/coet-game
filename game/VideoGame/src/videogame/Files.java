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
            printWriter.println("" + game.getPlayer().getX() + "," + 
                    game.getPlayer().getY());
            printWriter.println("" + game.getScore() + "," + game.getVidas());
            
            //TODO: check what will be saved and loaded in the game 
            
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
            tokens = line.split(",");
            // Setting score and lives for game.
            game.setScore(Integer.parseInt(tokens[0]));
            game.setVidas(Integer.parseInt(tokens[1]));
            
            //TODO: check what will be saved and loaded in the game         
            
            
        } catch(IOException ioe){
            System.out.println("Juego no ha sido guardado" + ioe.toString());
        }
        
    }
}
