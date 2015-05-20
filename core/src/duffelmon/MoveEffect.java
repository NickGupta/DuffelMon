/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;


/**
 *
 * @author csstudent
 */
public class MoveEffect extends GameObject{
    Texture texture; 
    private static HashMap<String, Texture> moveTextureMap = new HashMap<String,Texture>();
    
    
    public MoveEffect(float x, float y){
      
        setX(x);
        setY(y);
        
    }
    
    public void addMoveTexture(Texture t, String s){
        moveTextureMap.put(s, t);
    }
    
    }
    
    
    

