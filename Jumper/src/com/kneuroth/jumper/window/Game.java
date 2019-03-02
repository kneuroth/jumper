/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import com.kneuroth.jumper.window.framework.KeyInput;
import com.kneuroth.jumper.window.framework.ObjectId;
import com.kneuroth.jumper.window.framework.objects.Block;
import com.kneuroth.jumper.window.framework.objects.Player;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import static java.lang.Math.random;

/**
 *
 * @author Kelly
 * Any time 'this' is used it refers to canvas
 */
public class Game extends Canvas implements Runnable {
    
    private boolean running = false;
    private Thread thread;
    
    Handler handler;
    Camera cam;
    
    public static int WIDTH, HEIGHT;
    
    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        handler = new Handler();
        
        cam = new Camera(0,0);
        
        handler.addObject(new Player(100, 450, handler, ObjectId.Player ));
        
        handler.createLevel();
        
        this.addKeyListener(new KeyInput(handler));
        
    }
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run(){
        init();
        this.requestFocus();
       //standard gameloop running at 60 fps 
       long lastTime = System.nanoTime();
       double amountOfTicks = 60.0;
       double ns = 1000000000 / amountOfTicks;
       double delta = 0;
       long timer = System.currentTimeMillis();
       int updates = 0;
       int frames = 0;
       while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    
    //updates ALL of the objects because of handler
    private void tick(){
        handler.tick();
        //tick in camera only for the player game object in handler
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ObjectId.Player){
                cam.tick(handler.object.get(i));
            }
        }
    }
    
    //draw graphics
    
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            //triple buffering loads 3 next screens in advance
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D)g;
        /*-----------------DRAW HERE----------------*/
        
        
        
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(), getHeight());
        
        g2d.translate(cam.getX(), cam.getY());//anything between here will be affected by camera
        
            handler.render(g);
        
        g2d.translate(-cam.getX(), -cam.getY());
        
        /*---------------------------------*/
        g.dispose();
        bs.show();
        
    }
    
    public static void main(String args[]){
        new Window(1000, 600, "Platformer prototype", new Game());
        
        
    }
}
