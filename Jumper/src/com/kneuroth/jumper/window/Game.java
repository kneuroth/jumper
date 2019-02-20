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
    
    @Override
    public void run(){
        
    }
    
    public static void main(String args[]){
        new Window(800, 500, "Platformer prototype", new Game());
            
        
    }
}
