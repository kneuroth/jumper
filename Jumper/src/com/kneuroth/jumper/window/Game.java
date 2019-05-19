/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import com.kneuroth.jumper.window.framework.KeyInput;
import com.kneuroth.jumper.window.framework.ObjectId;
import com.kneuroth.jumper.window.framework.Texture;
import com.kneuroth.jumper.window.framework.objects.Block;
import com.kneuroth.jumper.window.framework.objects.BounceBlock;
import com.kneuroth.jumper.window.framework.objects.Platform;
import com.kneuroth.jumper.window.framework.objects.Player;
import com.kneuroth.jumper.window.framework.objects.Rail;
import com.kneuroth.jumper.window.framework.objects.Box;
import com.kneuroth.jumper.window.framework.objects.Portal;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
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
    static Texture tex;
    
    private BufferedImage level = null;
    
    public static int WIDTH, HEIGHT;
    
    private void init(){
        WIDTH = getWidth();
        HEIGHT = getHeight();
        
        tex = new Texture();
        
        BufferedImageLoader loader = new BufferedImageLoader();
        level = loader.loadImage("/level.png"); //loading level
        
        handler = new Handler();
        
        cam = new Camera(0,0);
        
        LoadImageLevel(level);
        
        //handler.addObject(new Player(100, 450, handler, ObjectId.Player ));
        
        //handler.createLevel();
        
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
        
        
        
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(), getHeight());
        
        g2d.translate(cam.getX(), cam.getY());//anything between here will be affected by camera
        
            handler.render(g);
        
        g2d.translate(-cam.getX(), -cam.getY());
        
        /*---------------------------------*/
        g.dispose();
        bs.show();
        
    }
    
    private void LoadImageLevel(BufferedImage image){
        int w = image.getWidth();
        int h = image.getHeight();
        
        //System.out.println("Width: "  + w + " Height: " + h);
        
        int lastPortalX = 0;
        int lastPortalY = 0;
        
        for(int xx = 0; xx < h; xx++){
            
            for(int yy = 0; yy < w; yy++){
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                
                //Black - Block
                if(red == 0 && green == 0 && blue == 0)handler.addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block));
                
                //Grey - Under block
                if(red == 127 && green == 127 && blue == 127)handler.addObject(new Block(xx * 32, yy * 32, 1, ObjectId.Block));
                
                //Grey - Left wall
                if(red == 100 && green == 100 && blue == 100)handler.addObject(new Block(xx * 32, yy * 32, 2, ObjectId.Block));
                
                //Grey - Right wall
                if(red == 50 && green == 50 && blue == 50)handler.addObject(new Block(xx * 32, yy * 32, 3, ObjectId.Block));
                
                //Grey - Both walls
                if(red == 150 && green == 150 && blue == 150)handler.addObject(new Block(xx * 32, yy * 32, 4, ObjectId.Block));
                
                //Grey - Ceiling
                if(red == 200 && green == 200 && blue == 200)handler.addObject(new Block(xx * 32, yy * 32, 5, ObjectId.Block));
                
                //Big corners
                //Grey - Corner up right
                if(red == 10 && green == 20 && blue == 30)handler.addObject(new Block(xx * 32, yy * 32, 6, ObjectId.Block));
                
                //Grey - Corner up left
                if(red == 20 && green == 30 && blue == 40)handler.addObject(new Block(xx * 32, yy * 32, 7, ObjectId.Block));
                
                //Grey - Corner down left
                if(red == 30 && green == 40 && blue == 50)handler.addObject(new Block(xx * 32, yy * 32, 8, ObjectId.Block));
                
                //Grey - Corner down right
                if(red == 40 && green == 50 && blue == 60)handler.addObject(new Block(xx * 32, yy * 32, 9, ObjectId.Block));
                
                //Small corners
                //Grey - Small corner up right
                if(red == 70 && green == 80 && blue == 90)handler.addObject(new Block(xx * 32, yy * 32, 10, ObjectId.Block));
                
                //Grey - Small corner up left
                if(red == 80 && green == 90 && blue == 100)handler.addObject(new Block(xx * 32, yy * 32, 11, ObjectId.Block));
                
                //Grey - Small corner down left
                if(red == 90 && green == 100 && blue == 110)handler.addObject(new Block(xx * 32, yy * 32, 12, ObjectId.Block));
                
                //Grey - Small corner down right
                if(red == 100 && green == 110 && blue == 120)handler.addObject(new Block(xx * 32, yy * 32, 13, ObjectId.Block));
                
                
                
                //Brown - Platform
                if(red == 185 && green == 122 && blue == 87)handler.addObject(new Platform(xx*32, yy*32, 14, ObjectId.Platform));
                //Purple - Bouncy Block
                if(red == 163 && green == 73 && blue == 164)handler.addObject(new BounceBlock(xx*32, yy*32,16,  ObjectId.BounceBlock));
                //Green - Rail
                if(red == 34 && green == 177 && blue == 76)handler.addObject(new Rail(xx*32, yy*32, 15, ObjectId.Rail));
                //Blue - Player
                if(red == 63 && green == 72 && blue == 204)handler.addObject(new Player(xx * 32, yy*32, handler, ObjectId.Player));
                //Orange - Box
                if(red == 255 && green == 127 && blue == 39)handler.addObject(new Box(xx * 32, yy*32, handler, ObjectId.Box));
                //Light purple - Portal
                /*if(red == 200 && green == 191 && blue == 231){
                    //ensures that only pairs of portals are created, pairing 2 closest portals
                    if(lastPortalX != 0 && lastPortalY != 0){
                        Portal p1 = new Portal(xx * 32, yy * 32, ObjectId.Portal);
                        Portal p2 = new Portal(lastPortalX, lastPortalY, ObjectId.Portal);
                        System.out.println("NEW PAIR");
                        
                        
                        p1.setSisterPortal(p2);
                        p2.setSisterPortal(p1);
                        
                        handler.addObject(p1);
                        handler.addObject(p2);
                        
                        lastPortalX = 0;
                        lastPortalY = 0;
                    }else{
                        lastPortalX = xx * 32;
                        lastPortalY = yy * 32;
                    }
                } */
            }
        }
    }
    
    public static Texture getInstance(){
        return tex;
    }
    
    public static void main(String args[]){
        new Window(1000, 600, "Platformer prototype", new Game());
        
        
    }
}
