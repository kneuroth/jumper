/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import com.kneuroth.jumper.window.framework.GameObject;
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
    
}
