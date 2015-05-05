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

public class Sprite {
    
    Texture backView;
    Texture frontView;
    
    public void Sprite(Texture Front, Texture Back) {
        frontView = Front;
        backView = Back;
    }
    
    public Texture getBack() {
        return backView;
    }
    
    public Texture getFront() {
        return frontView;
    }
    
}
