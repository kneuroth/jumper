/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import com.kneuroth.jumper.window.Game;
import com.kneuroth.jumper.window.Handler;
import com.kneuroth.jumper.window.framework.objects.Portal;
import java.awt.Graphics;
import java.awt.Rectangle;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kelly
 * Any kind of object within the game
 */
public abstract class GameObject {
    
    protected final int MAX_X_SPEED = 9;
    
    protected Handler handler;
    
    protected float x,y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    
    protected boolean falling = true;
    protected boolean jumping = false;
    
    protected boolean onWall = false;
    
    
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
    
    public List<ObjectId> Collision(){
                
        List<ObjectId> idList;
        idList = new ArrayList<>();
        
        
        for(int i = 0; i < handler.object.size(); i++){
            
            GameObject tempObject = handler.object.get(i);
            
            if(this.getId() == ObjectId.Player){
            
                if( null != tempObject.getId())switch (tempObject.getId()) {
                        case Block:
                            if(getBoundsTop().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());
                                y = tempObject.getY()+ 32;//removing this makes slider block
                                velY = 0;
                            }       if(getBounds().intersects(tempObject.getBounds())){
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
                                x = tempObject.getX() - 41;

                                velX = 0;
                      
                                onWall = true;
                            }       
                            if(getBoundsLeft().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());

                                x = tempObject.getX() + 33;

                                velX = 0;
                         
                                onWall = true;
                            }       break;
                        case Rail:
                            if(getBoundsTop().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());
                                y = tempObject.getY();
                                falling = false;
                                jumping = false;
                                velY = 0;
                                //x += velX/2;
                                velX += velX/20;

                            }       break;
                        case Portal:
                            if(getBounds().intersects(tempObject.getBounds())){
                                
                                Portal tempPortal = (Portal)tempObject;
                                
                                idList.add(handler.object.get(i).getId());
                                if(velY < 0){
                                    y = tempPortal.getSisterPortal().getY() - tempPortal.getHeight() - getHeight()/3;
                                }else{
                                    y = tempPortal.getSisterPortal().getY() + 2;
                                }
                                x = tempPortal.getSisterPortal().getX();
                                
                                //System.out.println("Go to: " + x + " and " + y);
                                
                            }       break;
                        case BounceBlock:
                            if(getBounds().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());
                                jumping = true;
                                if(velY > -10){
                                    velY = -1.5f * abs(velY);
                                }
                            }       break;
                        case Platform:
                            if(getBounds().intersects(tempObject.getBounds()) && velY >= 0){
                                idList.add(handler.object.get(i).getId());
                                y = tempObject.getY() - getHeight() ;//removing this makes slider block
                                falling = false;
                                jumping = false;
                                velY = 0;
                            }       break;
                        case Box:
                            if(getBounds().intersects(tempObject.getBoundsTop()) && velY >= 0){
                                idList.add(handler.object.get(i).getId());
                                y = tempObject.getY() - getHeight() ;//removing this makes slider block
                                falling = false;
                                jumping = false;
                                velY = 0;
                            }       break;
                            
                        default:
                            break;
                    }
            }
            
            if(this.getId() == ObjectId.Box){
                
                if( null != tempObject.getId())switch (tempObject.getId()) {
                        case Block:
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

                                x = tempObject.getX() - 31;

                                velX = 0;
                                onWall = true;
                            }       
                            if(getBoundsLeft().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());
                                x = tempObject.getX() + 33;

                                velX = 0;
                                onWall = true;
                            }       break;
                        case Portal:
                            if(getBounds().intersects(tempObject.getBounds())){
                                
                                Portal tempPortal = (Portal)tempObject;
                                
                                idList.add(handler.object.get(i).getId());
                                if(velY < 0){
                                    y = tempPortal.getSisterPortal().getY() - tempPortal.getHeight() - getHeight()/3;
                                }else{
                                    y = tempPortal.getSisterPortal().getY() + 2;
                                }
                                x = tempPortal.getSisterPortal().getX();
                                
                                //System.out.println("Go to: " + x + " and " + y);
                                
                            }       break;
                        case BounceBlock:
                            if(getBounds().intersects(tempObject.getBounds())){
                                idList.add(handler.object.get(i).getId());
                                jumping = true;
                                velY = -13;
                            }       break;
                        case Platform:
                            if(getBounds().intersects(tempObject.getBounds()) && velY >= 0){
                                idList.add(handler.object.get(i).getId());
                                y = tempObject.getY() - getHeight() ;//removing this makes slider block
                                falling = false;
                                jumping = false;
                                velY = 0;
                            }       break;
                        case Player:
                            if(getBoundsLeft().intersects(tempObject.getBoundsRight()) && tempObject.getVelX() > 0){
                                velX = tempObject.getVelX();
                            }
                            else if(getBoundsRight().intersects(tempObject.getBoundsLeft()) && tempObject.getVelX() < 0){
                                velX = tempObject.getVelX();
                            }       break;
                        case Box:
                        if(getBoundsRight().intersects(tempObject.getBoundsLeft())){
                            velX = tempObject.getVelX();
                        }
                        else if(getBoundsLeft().intersects(tempObject.getBounds())){
                            velX = tempObject.getVelX();
                        }
                        else if(getBounds().intersects(tempObject.getBounds()) && velY >= 0){
                            idList.add(handler.object.get(i).getId());
                            y = tempObject.getY() - getHeight() ;//removing this makes slider block
                            falling = false;
                            jumping = false;
                            velY = 0;
                        }       break;
                        default:
                            break;
                    }
            
            }
        }
        
        
        return idList;
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
        //if(velX % MAX_X_SPEED != 0)this.velX = 0;
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
        return new Rectangle((int) ((int)x + getWidth() -10), (int)y + 5, (int)10, (int)getHeight() - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)10, (int)getHeight() - 10);
    }
    
}
