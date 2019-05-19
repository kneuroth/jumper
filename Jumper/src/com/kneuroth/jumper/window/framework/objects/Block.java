/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

import com.kneuroth.jumper.window.Game;
import com.kneuroth.jumper.window.framework.GameObject;
import com.kneuroth.jumper.window.framework.ObjectId;
import com.kneuroth.jumper.window.framework.Texture;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kelly
 */
public class Block extends GameObject{
    
    Texture tex = Game.getInstance();
    
    private float height = 32, width = 32, crouchHeight = height/2;
    private int type;
    
    public Block(float x, float y, int type, ObjectId id) {
        super(x, y, id);
        this.type = type;
    }
 
    @Override
    public void tick(LinkedList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(tex.block[type], (int)x, (int)y, null);
    }
    
    @Override
    public Rectangle getBounds(){
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
    

