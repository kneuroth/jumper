/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

import com.kneuroth.jumper.window.Game;
import com.kneuroth.jumper.window.Handler;
import com.kneuroth.jumper.window.framework.GameObject;
import com.kneuroth.jumper.window.framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Kelly
 */
public class Player extends GameObject{
    
    private float width = 40, height = 40; //48 96
    private float gravity = 0.7f;
    private float frictionToRight = -0.3f;
    private float frictionToLeft = 0.3f;
    
    private final float MAX_SPEED = 10;
    
    private Handler handler;
   
    
    public Player(float x, float y, Handler handler, ObjectId id){
        super(x, y, id);
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
        /*if(velX > 0)
            velX += frictionToRight;
        
        if(velX < 0)
            velX += frictionToLeft;*/
        
        

        System.out.println(velX);
        x += velX;
        y += velY;
        
        /*List<ObjectId> touching = Collision(object);
        if(touching.contains(ObjectId.Rail)){
            x += velX;
        }*/
        Collision(object);
        

        
        
    }
    
    //Takes care of what happens when collision happens
    private List<ObjectId> Collision(LinkedList<GameObject> object){
        
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
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }else{
                    falling = true;
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    idList.add(handler.object.get(i).getId());
                    x = tempObject.getX() - 42;
                    System.out.println(tempObject.getX());
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
                    falling = false;
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
        }
        
        return idList;
    }

    @Override
    public void render(Graphics g) {
        
        
        
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, (int)width, (int)height);
        
        /*Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
        */
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (width/2) - (width/2)/2), (int) ((int)y + (height/2)), (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (width/2) - (width/2)/2), (int)y, (int)width/2, (int)height/2);
    }
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + width - 5), (int)y + 5, (int)5, (int)height - 10);
    }
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)5, (int)height - 10);
    }

    
    
}
