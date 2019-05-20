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
    
    private boolean S;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        //Check entire list of objects in the game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            //If this is the player
            if(tempObject.getId() == ObjectId.Player){
                
                
                if(!tempObject.onLeftWall)
                    if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A)&& tempObject.getVelX() > -tempObject.MAX_X_SPEED){
                        tempObject.setVelX(-tempObject.MAX_X_SPEED);
                        
                    }
                if(!tempObject.onRightWall)
                    if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D)&& tempObject.getVelX() < tempObject.MAX_X_SPEED){
                        
                        tempObject.setVelX(tempObject.MAX_X_SPEED);
                        
                    }
                
                if((key == KeyEvent.VK_DOWN  || key == KeyEvent.VK_S) && tempObject.touching.contains(ObjectId.Platform)){ //bit of a work around. switch between crouch height and regular height
                    
                    tempObject.setY(tempObject.y + 45);
                    
                }
                
                if(key == KeyEvent.VK_SPACE && (!tempObject.isJumping() || tempObject.touching.contains(ObjectId.Rail))){
                    System.out.println("ye");
                    if(tempObject.touching.contains(ObjectId.Rail))
                        tempObject.setY(tempObject.y - tempObject.getHeight());
                    tempObject.setJumping(true);
                    tempObject.setVelY(-13);
                }
                   
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        //Check entire list of objects in the game
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            
            //If this is the player
            if(tempObject.getId() == ObjectId.Player){
                if((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && tempObject.getVelX() > 0){
                    tempObject.setVelX(0);
                    
                }
                if((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && tempObject.getVelX() < 0){
                    tempObject.setVelX(0);
                }
                
                
                
                
            }
        }
    }
    
}
