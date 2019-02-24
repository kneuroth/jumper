/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Kelly
 * Any kind of object within the game
 */
public abstract class GameObject {
    
    protected final int MAX_X_SPEED = 7;
    
    protected float x,y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    
    protected boolean falling = true;
    protected boolean jumping = false;
    
    public GameObject(float x, float y, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    
    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();
    
    public boolean isFalling(){
        return falling;
    }
    public void setFalling(boolean f){
        this.falling = f;
    }
    public boolean isJumping(){
        return jumping;
    }
    public void setJumping(boolean j){
        this.jumping = j;
    }
    
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    
    public float getVelX(){
        return velX;
    }
    public float getVelY(){
        return velY;
    }
    public void setVelX(float velX){
        this.velX += velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    
    public ObjectId getId(){
        return id;
    }
    
}
