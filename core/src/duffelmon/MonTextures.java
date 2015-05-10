/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author csstudent
 */

public class MonTextures {
    
    Texture frontTexture;
    Texture backTexture;
    
    public MonTextures(Texture f, Texture b) {
        frontTexture = f;
        backTexture = b;
    }
    
    public Texture getFrontTexture() {
        return frontTexture;
    }
    
    public Texture getBackTexture() {
        return backTexture;
    }
    
}
