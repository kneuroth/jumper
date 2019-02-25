/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import com.kneuroth.jumper.window.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Kelly
 */
public class KeyInput extends KeyAdapter{
    
    Handler handler;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        //Check entire list of objects in the game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            //If this is the player
            if(tempObject.getId() == ObjectId.Player){
                
                if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)&& tempObject.getVelX() < tempObject.MAX_X_SPEED){
                    tempObject.setVelX(tempObject.MAX_X_SPEED);
                }
                if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)&& tempObject.getVelX() > -tempObject.MAX_X_SPEED){
                    tempObject.setVelX(-tempObject.MAX_X_SPEED);
                }
                
                
                
                
                if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-13);
                }
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        //Check entire list of objects in the game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            //If this is the player
            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D){
                    tempObject.setVelX(-tempObject.MAX_X_SPEED);
                }
                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A){
                    tempObject.setVelX(tempObject.MAX_X_SPEED);
                }
                
            }
        }
    }
    
}
