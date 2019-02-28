/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

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
public class Rail extends GameObject{
    
    private float height = 32, width = 16;
    
    public Rail(float x, float y, ObjectId id) {
        super(x, y, id);
    }
    
    @Override
    public void tick(LinkedList<GameObject> object) {
       
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
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
    public float getWidth() {
        return width;
    }


    
}
