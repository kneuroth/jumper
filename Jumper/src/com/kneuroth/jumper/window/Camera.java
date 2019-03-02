/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import com.kneuroth.jumper.window.framework.GameObject;

/**
 *
 * @author Kelly
 */
public class Camera {
    
    private float x, y;
    

    
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }
    
 

 
    
    public void tick(GameObject player){
         x = -player.getX() + Game.WIDTH/2 + player.getVelX();
         y = -player.getY() + Game.HEIGHT/2 + player.getVelY();
         
        
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    
    
}
