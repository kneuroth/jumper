/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import com.kneuroth.jumper.window.Game;
import com.kneuroth.jumper.window.Handler;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kelly
 * Any kind of object within the game
 */
public abstract class GameObject {
    
    protected final int MAX_X_SPEED = 10;
    
    protected Handler handler;
    
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
    
    public abstract float getHeight();
    
    public abstract void setHeight(float height);
    
    public abstract float getCrouchHeight();
    
    public abstract void setCrouchHeight(float crouchHeight);
    
    public abstract float getWidth();
    
    public abstract void setWidth(float width);
    
    public List<ObjectId> Collision(){
                
        List<ObjectId> idList;
        idList = new ArrayList<>();
        
        
        for(int i = 0; i < handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Block){
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    y = tempObject.getY()+ 32;//removing this makes slider block
                    velY = 0;
                }
            
                if(getBounds().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    y = tempObject.getY() - getHeight();
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else{
                    falling = true;
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    x = tempObject.getX() - 42;
                    //System.out.println(tempObject.getX());
                }
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    x = tempObject.getX() + 35;
                }
            }
            
            if(tempObject.getId() == ObjectId.Rail){
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    y = tempObject.getY();//removing this makes slider block
                    //falling = false;
                    jumping = false;
                    velY = 0;
                    x += velX/2;
                    
                } 
            }
            
            if(tempObject.getId() == ObjectId.Portal){
                if(getBounds().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    if(tempObject.getY() < Game.HEIGHT)
                        y = tempObject.getY() - Game.HEIGHT + 100;
                }
            }
            
            if(tempObject.getId() == ObjectId.BounceBlock){
                if(getBounds().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    jumping = true;
                    velY = -17;
                }
            }
            if(tempObject.getId() == ObjectId.Platform){
                if(getBounds().intersects(tempObject.getBounds()) && velY >= 0){
                    idList.add(handler.object.get(i).getId());
                    y = tempObject.getY() - getHeight() ;//removing this makes slider block
                    falling = false;
                    jumping = false;
                    velY = 0;
                }
            }
        }
        System.out.println(idList.contains(ObjectId.Platform));
        
        return idList;
    }
    
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
        this.velX = velX;
    }
    public void setVelY(float velY){
        this.velY = velY;
    }
    
    public ObjectId getId(){
        return id;
    }
    
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (getWidth()/2) - (getWidth()/2)/2), (int) ((int)y + (getHeight()/2)), (int)getWidth()/2, (int)getHeight()/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (getWidth()/2) - (getWidth()/2)/2), (int)y, (int)getWidth()/2, (int)getHeight()/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + getWidth() - 5), (int)y + 5, (int)5, (int)getHeight() - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)5, (int)getHeight() - 10);
    }
    
}
