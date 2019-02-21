/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Kelly
 * Any kind of object within the game
 */
public abstract class GameObject {
    
    protected float x,y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    
    public GameObject(float x, float y, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<GameObject> object);
    
    public abstract void render(Graphics g);
    
    public abstract float getX();
    public abstract float getY();
    public abstract void setX(float x);
    public abstract void setY(float y);
    
    public abstract float getVelX();
    public abstract float getVelY();
    public abstract void setVelX(float x);
    public abstract void setVelY(float y);
    
    public abstract ObjectId getId();
    
}
