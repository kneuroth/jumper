/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

import com.kneuroth.jumper.window.Game;
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
public class Portal extends GameObject {
    
   private float height = 32, width = 32;
   
   Portal sisterPortal;
    
    
    public Portal(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
    }

    public void setSisterPortal(Portal sisterPortal){
        this.sisterPortal = sisterPortal;
    }
    
    public Portal getSisterPortal(){
        return sisterPortal;
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect((int)x, (int)y, 32, 32);
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
    public float getWidth() {
       return width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }




    
}
