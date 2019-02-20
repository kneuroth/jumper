/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window;

import java.awt.Canvas;

/**
 *
 * @author Kelly
 */
public class Game extends Canvas implements Runnable {
    
    private boolean running = false;
    private Thread thread;
    
    public synchronized void start(){
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run(){
       System.out.println("Thread began");
    }
    
    public static void main(String args[]){
        new Window(800, 500, "Platformer prototype", new Game());
            
        
    }
}
