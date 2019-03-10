/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

import com.kneuroth.jumper.window.Handler;
import com.kneuroth.jumper.window.framework.GameObject;
import com.kneuroth.jumper.window.framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Kelly
 */
public class Box extends GameObject {
    
    
    private float height = 32, width = 32, crouchHeight = height/2;
    private float gravity = 0.7f;
    
    private final float MAX_SPEED = 10;

    public Box(float x, float y, Handler handler, ObjectId id) {
        super(x, y,  id);
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
        
        
        //Gravity will affect it if falling or jumping
        if(falling || jumping){
            velY += gravity;
            
            //Reaching terminal 
            if(velY > MAX_SPEED){
                velY = MAX_SPEED;
            }
        }
        
        
        x += velX;
        y += velY;
        
        velX = 0;
        
        Collision();
        
        
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.drawRect((int)x, (int)y, (int)height, (int)width);
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)height, (int)width);
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getCrouchHeight() {
        return crouchHeight;
    }

    @Override
    public void setCrouchHeight(float crouchHeight) {
        this.crouchHeight = crouchHeight;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
        
    }
    
}
