/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import com.kneuroth.jumper.window.framework.GameObject;
import com.kneuroth.jumper.window.framework.ObjectId;
import com.kneuroth.jumper.window.framework.objects.Block;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Kelly
 */
public class Handler {
    
    public LinkedList<GameObject> object = new LinkedList<GameObject>();
    
    private GameObject tempObject;
    
    public void tick(){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            
            tempObject.tick(object);
        }
    }
    
    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            tempObject = object.get(i);
            
            tempObject.render(g);
        }
    }
    
    public void addObject(GameObject object){
        this.object.add(object);
    }
    
    public void removeObject(GameObject object){
        this.object.remove(object);
    }
    
    public void createLevel(){
        for(int xx = 0; xx < Game.WIDTH + 32; xx+=32){
            addObject(new Block( xx, Game.HEIGHT - 32, ObjectId.Block));
        }
    }
    
}
