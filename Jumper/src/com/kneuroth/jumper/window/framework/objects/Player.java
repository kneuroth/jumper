/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework.objects;

import com.kneuroth.jumper.window.Animation;
import com.kneuroth.jumper.window.Game;
import com.kneuroth.jumper.window.Handler;
import com.kneuroth.jumper.window.framework.GameObject;
import com.kneuroth.jumper.window.framework.ObjectId;
import com.kneuroth.jumper.window.framework.Texture;
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
    
    Texture tex = Game.getInstance();
    //40 , 60
    private float width = 40, height = 60, crouchHeight = height/2; //48 96
    private float gravity = 0.7f;
    
    private Animation playerWalkRight;
    private Animation playerWalkLeft;
    private Animation playerClimbRight;
    private Animation playerClimbLeft;
    
    private final float MAX_SPEED = 10;
    
    List<ObjectId> touching;
    
    int tempCount = 0;
   
    
    public Player(float x, float y, Handler handler, ObjectId id){
        super(x, y, id);
        super.handler = handler;
        
        playerWalkRight = new Animation(8, tex.player[1], tex.player[2]);
        playerWalkLeft = new Animation(8, tex.player[3], tex.player[4]);
        playerClimbRight = new Animation(8, tex.player[6], tex.player[7]);
        playerClimbLeft = new Animation(8, tex.player[8], tex.player[9]);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        
       
        if(velX > MAX_X_SPEED * 1.5){
            velX--;
        }
        if(velX < MAX_X_SPEED * -1.5){
            velX++;
        }
        
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
        
        if(onWall == true){
            jumping = false;
            falling = false;
            onWall = false;
        }
        
        /*List<ObjectId> touching = Collision(object);
        if(touching.contains(ObjectId.Rail)){
            x += velX;
        }*/
        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
        playerClimbRight.runAnimation();
        playerClimbLeft.runAnimation();
        touching = Collision();
        //System.out.println(x);
           
        
    }
    
    @Override
    public void render(Graphics g) {
        //System.out.println(touching.contains(ObjectId.Rail));
        if(velX > 0){
            if(touching.contains(ObjectId.Rail))
                playerClimbRight.drawAnimation(g, (int)x, (int)y);
            else
                playerWalkRight.drawAnimation(g, (int)x, (int)y);
        }
        else if(velX < 0){
            if(touching.contains(ObjectId.Rail))
                playerClimbLeft.drawAnimation(g, (int)x, (int)y);
            else
                playerWalkLeft.drawAnimation(g, (int)x, (int)y);
        }
        else{
            if(tempCount > 0){
                if(touching.contains(ObjectId.Rail))
                    g.drawImage(tex.player[5], (int)x, (int)y, null);
                else
                    g.drawImage(tex.player[0], (int)x, (int)y, null);
            }else{
                tempCount = 1;
            }
        }
        
        
        /*Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
        */
    }
    
    

    /*@Override
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
    }*/

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

    @Override
    public float getCrouchHeight() {
        return crouchHeight;
    }

    @Override
    public void setCrouchHeight(float crouchHeight) {
        this.crouchHeight = crouchHeight;
    }
    
        @Override
    public Rectangle getBounds() {
        return new Rectangle((int) ((int)x + (getWidth()/2) - (getWidth()/2)/2), (int) ((int)y + (getHeight()/2)), (int)getWidth()/2, (int)getHeight()/2);
    }
    
    @Override
    public Rectangle getBoundsTop() {
        return new Rectangle((int) ((int)x + (getWidth()/2) - (getWidth()/2)/2), (int)y, (int)getWidth()/2, (int)getHeight()/2);
    }
    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int)x + getWidth() -10), (int)y + 5, (int)10, (int)getHeight() - 10);
    }
    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)y + 5, (int)10, (int)getHeight() - 10);
    }

    
    
}
