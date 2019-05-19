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
    
    public BufferedImage[] block = new BufferedImage[15];
    public BufferedImage[] player = new BufferedImage[10];
    
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
        /*
        [floor]   [middle]  [left wall] [right wall] [ceiling]Flats
        [Upright] [upleft] [downleft]  [downright]           Outside corners
        
        */
        block[0] = bs.grabImage(1, 1, 32, 32);//Floor
        block[1] = bs.grabImage(2, 1, 32, 32);//middle
        block[2] = bs.grabImage(3, 1, 32, 32);//Left wall
        block[3] = bs.grabImage(4, 1, 32, 32); //Right wall
        block[4] = bs.grabImage(5, 1, 32, 32); //Both wall
        block[5] = bs.grabImage(6, 1, 32, 32); //Ceiling
        
        block[6] = bs.grabImage(7, 1, 32, 32);//corner up right
        block[7] = bs.grabImage(8, 1, 32, 32);//corner up left
        block[8] = bs.grabImage(9, 1, 32, 32);//corner down left
        block[9] = bs.grabImage(10, 1, 32, 32);//corner down right
        
        block[10] = bs.grabImage(11, 1, 32, 32);//corner up right
        block[11] = bs.grabImage(12, 1, 32, 32);//corner up left
        block[12] = bs.grabImage(13, 1, 32, 32);//corner down left
        block[13] = bs.grabImage(14, 1, 32, 32);//corner down right
        
        player[0] = ps.grabImage(1, 1, 40, 60); //idle
        
        player[1] = ps.grabImage(2, 1, 40, 60);
        player[2] = ps.grabImage(3, 1, 40, 60);
        player[3] = ps.grabImage(4, 1, 40, 60);
        player[4] = ps.grabImage(5, 1, 40, 60);
        
        player[5] = ps.grabImage(1, 2, 40, 60);//idle hang
        
        player[6] = ps.grabImage(2, 2, 40, 60);
        player[7] = ps.grabImage(3, 2, 40, 60);
        player[8] = ps.grabImage(4, 2, 40, 60);
        player[9] = ps.grabImage(5, 2, 40, 60);
        
        
        
    }
    
    
    
}
