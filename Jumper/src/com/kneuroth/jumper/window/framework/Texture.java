/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kneuroth.jumper.window.framework;

import com.kneuroth.jumper.window.BufferedImageLoader;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kelly
 */
public class Texture {
    
    SpriteSheet bs, ps;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    
    public BufferedImage[] block = new BufferedImage[5];
    
    public Texture(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("/block_sheet.png");
            player_sheet = loader.loadImage("/player_sheet.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        
        getTextures();
    }
    
    private void getTextures(){
        block[0] = bs.grabImage(1,1,32,32);//Floor
        block[1] = bs.grabImage(2, 1, 32, 32);//middle
        block[2] = bs.grabImage(3, 1, 32, 32);//Left wall
        block[3] = bs.grabImage(4, 1, 32, 32); //Right wall
        block[4] = bs.grabImage(5, 1, 32, 32); //Both wall
    }
    
    
    
}
