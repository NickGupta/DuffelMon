/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duffelmon;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author csstudent
 */

public class MonDisplay extends Actor{
    
    private Mon mon;
    private float x;
    private float y;
    private Texture monSprite;
    
    public MonDisplay(Mon m, float xCord, float yCord){
        mon = m;
        x = xCord;
        y = yCord;
        monSprite = m.getSprite();
    }
    
    public Mon getMon() {
        return mon;
    }
    
    public void setMon(Mon m) {
        mon = m;
        monSprite = m.getSprite();
    }
    
    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(monSprite, x, y);
    }
    
}
