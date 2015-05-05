/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 *
 * @author csstudent
 */

public class MonSprite {
    
    Sprite frontSprite;
    Sprite backSprite;
    
    public MonSprite(Sprite f, Sprite b) {
        frontSprite = f;
        backSprite = b;
    }
    
    public Sprite getFrontSprite() {
        return frontSprite;
    }
    
    public Sprite getBackSprite() {
        return backSprite;
    }
}
